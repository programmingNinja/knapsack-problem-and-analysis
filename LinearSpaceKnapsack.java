
import java.util.*;

/**
 *
 * @author Rohan D. Shah
 *
 * Description:
 * This program solves the knapsack problem using dynamic programming. The way it stores the calculated value is different
 * than normal knapsack problem. Since to calculate a value we need the current row and the previous row only, we don't 
 * store other row values and discard them. This approach takes a little more execution time but saves a lot of space and we 
 * can go for more number of items which was not possible in previous knapsack problem dynamic programming implementation.
 * 
 * Also we use a flavor of divide and conquer in this implementation. We divide the knapsack problem into two parts of equal
 * number of rows and run the algorithm on both of the separate problems. We find an intersection between two problems, which
 * gives the optimal solution. We note down the index that gives the optimal solution and then divide the problem into two parts
 * which will range from capacity 0 to the capacity of the new index that we found out. So we don't need to calculate for the
 * values beyond that index. 
 * We continue this till we reach a single element/item and see if that is less than the capacity, if yes we store it and use
 * it for the solution.
 */

public class LinearSpaceKnapsack
{
    
     // Total number of items
    static int noOfItems = 16;
    
    // Stores the middle row, used for divide and conquer
    // Dividing parameter
    static int mid ;
   
    // Capacity of the knapsack
    static int capacity = noOfItems*10;
    
    // The index of the item that gives the optimal/best solution for a knapsack problem
    static int bestValueIndex;
    
    // The optimal/best solution for a knapsack problem
    static int bestValue=0;
   
    // For calculating the best value
    static int prevBestValue=0;
    
    
    static int count=0;

    // Size is capacity+1 because the index is the capacity left
    // Storing only twp rows to save space, for left knapsack problem
    static int seenLeft[][]= new int[2][capacity+1];
    
    // Size is capacity+1 because the index is the capacity left
    // Storing only twp rows to save space, for right knapsack problem
    static int[][] seenRight = new int[2][capacity+1];
    
    // We are concerned with just the last row of the seenRight array. It stores that.
    static int [] rightCol = new int[capacity+1];
    
    // We are concerned with just the last row of the seenLeft array. It stores that.
    static int [] leftCol = new int[capacity+1];
    
    // The value of the seen array when there are no items
    static int [] initialZero = new int[capacity+1];
    //static int[][] keep = new int[capacity+1][capacity+1];
  
   // Stores the values of the items
    static int[] value = new int[noOfItems+1];    
    
    // Stores the size/weight of the items
    static int[] size = new int[noOfItems+1]; 
        
    static boolean[] KnapUse = new boolean[noOfItems+1];
    
    // Computes the SeenRight and seenLeft arrays and returns the final rows of both of that arrays.
    public static int[] computeFinalCol(int low, int high, int [][] seen, int cap)
    {
        // calculating the first row of seenRight and SeenLeft arrays from intial zero
        for(int w=0 ; w<=cap ; w++)
        {
            if(size[low] <= w && ((value[low]+initialZero[w - size[low]]) > initialZero[w]))
            {                    
                seen[0][w] = value[low]+initialZero[w - size[low]]; // sum of values stored                                         
            }
            else
            {
                seen[0][w] = initialZero[w];
            }
        }
        
        // Calculating succeeding rows of seenRight and seenLeft from the previous row
        // The modulo division facilitates the usage of only two rows.
        // If both rows are filled, the 1st is calulcated from the 2nd and stored in 1st.
        for(int i=low+1 ; i<=high ; i++)
        {
            for(int w=0 ; w<=cap ; w++)
            {
                 if(size[i] <= w && ((value[i]+seen[(i)%2][w - size[i]]) > seen[(i)%2][w]))
                {                    
                    seen[(i-1)%2][w] = value[i]+seen[(i)%2][w - size[i]]; // sum of values stored                    
                }
                else
                {
                    seen[(i-1)%2][w] = seen[(i)%2][w];
                }
            }
        }
        // if there is only one row, it will always be the 1st row of seenRight or seenLeft array that will have the updated values
        if(low==high)
        return seen[0]  ;      
        else 
        return seen[1];
    }
    static void knapsack(int lowIndex, int highIndex, int capacity)
    {
        if(lowIndex == highIndex )
        {   
            KnapUse[lowIndex]=(size[lowIndex] <= capacity);
             return;
        }
       else{
       mid=((lowIndex)+highIndex)/2;       
                         
       leftCol=computeFinalCol(lowIndex, mid, seenLeft, capacity);
       rightCol=computeFinalCol(mid+1, highIndex, seenRight, capacity);
       
       // Mimicking the argMax function of MATLAB
       for(int i=0 ; i<=capacity; i++)
       {              
          prevBestValue = (leftCol[i]+rightCol[capacity-i]);
          if(prevBestValue>bestValue)
           {
               bestValue = prevBestValue;
                                
               // The intersection which gives the optimal solution
               // The basis for dividing the problem.
               // find the crossing point, loop over 0<=i<=capacity
               bestValueIndex=i;
           }          
       }
       
       // solve the two smaller problems
       // Dividing the knapsack problem and conquering it.       
       // The problem before the best value index
       
       knapsack(lowIndex, mid, bestValueIndex);
       
       // The problem after the best value index.
       knapsack(mid+1, highIndex,capacity-bestValueIndex);
    }
           
    }
    public static void main(String args[])
    {
       
        
        for(int c=0;c<=capacity;c++)
            initialZero[c]=0;
        // store the random number
        int forDistribution;        
        //assign sizes and values randomly
        Random randomGenerator = new Random();
        // Fill in size and values of items with random numbers between 1 and 1000 inclusive
        // Eliminating the one-off problem
        size[0]=0;
        value[0]=0;
        for(int i=1 ; i<=noOfItems ; i++)
        {
            forDistribution = 0;
            // randomGenerator() gives random number from 0 to specified value, 
            // but we don't want 0 to be our size and value
            while(forDistribution == 0)// so that value or size is not zero
            forDistribution = randomGenerator.nextInt(capacity/10);
            
            size[i] = forDistribution;
            
            forDistribution = 0;
            while(forDistribution == 0)
            forDistribution = randomGenerator.nextInt(capacity/10);
            
            value[i] = forDistribution;
        }
        
        System.out.println("---------------------------");
        // printing the sizes of items
        System.out.println("The size array");
        System.out.println("---------------------------");
        for(int i=1 ; i <= noOfItems ; i++)
            System.out.println("item" +(i) +" "+ size[i]); 
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        // printing the values of items
        System.out.println("The value array");
        System.out.println("---------------------------");
        for(int i=1 ; i <= noOfItems ; i++)
            System.out.println("item" +(i) +" "+ value[i]);
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        
       
        knapsack(1,noOfItems,capacity);
        int result = bestValue;   
        System.out.println("the max value is");
        System.out.println(result);
       
        for(int a=0 ; a<=noOfItems ; a++)
        {
            if(KnapUse[a])
            System.out.print("Item"+a+" ");
        }
        System.out.println("---------------------------");       
       
    }
}

    

