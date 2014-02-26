
import java.lang.reflect.Array;
import java.util.Random;

/*
 * Rohan D. Shah 
 * IDE: NetBeans 7.4
 * Programming language = JAVA
 * Solution as a dynamic programming algorithm.
 *
 * Description:
 * This program implements knapsack problem using dynamic programming. It computes the base cases first and then other cases
 * are stored based on them and stored in the array apriori.
 * To calculate the execution time, I have used profiling tool in the IDE. It starts and stops the stop watch
 * at defined points. I have defined the start point at 119 and end point at 183
 * The program maximizes the value in the knapsack and also tells which items will give the maximized value.
 * the logic of the problem is that there are two choices which the user can make - either take the item or not.
 * The entire program and iterations depends on it. The sizes and values of the items are assigned using
 * uniform random distribution between 1 and 1000 inclusive.
 *
 * --------------------------------------------------------------
 * Logic for storing the item which gives the optimal solution:- Trace back routine
 * ---------------------------------------------------------------
 * We need to start with the value in the bottom-right. That is the decision 
 * of the last item (i.e., the first one we considered) with the sack completely empty 
 * (i.e, maximum size available). If that number is 1 in the keep[][] it means we pick that item in the optimal 
 * solution, if not, then we don't pick it. 
 * Now we proceed to the next item, which will be the row above, and the column will be the total weight 
 * (i.e., capacity) minus the weight of the item we just picked (i.e., size[item number]). 
 */
/**
 *
 * @author Rohan D. Shah
 */

public class dynamicProgKnapsack 
{
    // Total number of items
    static int noOfItems = 32;
    
    // Capacity of the knapsack
    static int capacity = 1000;
    
    // Min size for empirical analysis(wide = 1 and narrow = capacity/20)
     static int minSize = 1;
    //static int minSize = capacity/20;
    
    // To compute the items picked. 
    // Size is capacity+1 because the index is the capacity left
    // has the value of the item if that item's value is computed and '0' if not computed
    static int seen[][]= new int[capacity+1][capacity+1];
    
   // Stores the values of the items
    static int[] value = new int[noOfItems];   // {9,9,5,5,1}
    
    // Stores the size/weight of the items
    static int[] size = new int[noOfItems]; //{4,3,3,1,5}; 
    
    // Stores the items which is to picked for maximized value
    // has the value '1' if that item picked and '0' if not picked
    static int[][] keep = new int[capacity+1][capacity+1];
    
/*===========================================================================================
 Method which computes the optimal value and items to be picked 
 We start with the last element and climb up to the first item.
 ===========================================================================================*/
    static int knapsack(int index, int capacity)
    {
        // Sum if the items picked/taken and not picked/not taken
        int take = 0, dontTake = 0;
        
        // base case
        // if there are no items hence those values will be obviously zero
        for(int w = 0 ; w <= capacity ; w++)
        {
            seen[0][w] = 0;
        }
        
        // base case
        // if there is no capacity in the knapsack hence those values will pbviously be zero
        for(int i = 1 ; i <= index ; i++)
        {
            seen[i][0] = 0;
        }
        
        // iteration begins here. Further values are calculated using the base cases
        // these values are stored in seen and objects kept is tracked by keep[][]
        for(int i = 1 ; i <= index ; i++)
        {
            for(int w = 1 ; w <= capacity ; w++)
            {
                // there is space in the sack and the total value of the items picked is less the value of
                // the items not picked 
                if(size[i-1] <= w && ((value[i-1]+seen[i-1][w - size[i-1]]) > seen[i-1][w]))
                {                    
                    seen[i][w] = value[i-1]+seen[i-1][w - size[i-1]]; // sum of values stored
                    keep[i-1][w] = 1;  // picking hence tracking                               
                }
                else
                {
                    seen[i][w] = seen[i-1][w];
                    keep[i-1][w] = 0; // not picking
                }
                
            }
        }
        
        for(int i = 1 ; i <= index ; i++)
        {
            for(int w = 1 ; w <= capacity ; w++)
            {
                System.out.print(seen[i][w]+" ");
            }
            System.out.println();
        }
        // returning the optimal solution
        
        return seen[index][capacity];
        
    }
//===================================================================================================         
    @SuppressWarnings("empty-statement")
    public static void main(String args[])
    {    
        // store the random number
        int forDistribution;
        
        //assign sizes and values randomly
        Random randomGenerator = new Random();
        // Fill in size and values of items with random numbers between minSize and capacity inclusive
        for(int i=0 ; i<noOfItems ; i++)
        {
            forDistribution = 0;
            // randomGenerator() gives random number from minsize to specified value(capacity), 
            // but we don't want 0 to be our size and value
            
            // for narrow minsize = capacity/20
            while(forDistribution < minSize)// so that value or size is not zero
            forDistribution = randomGenerator.nextInt(capacity);
            
            size[i] = forDistribution;
            
            forDistribution = 0;
            while(forDistribution < minSize)
            forDistribution = randomGenerator.nextInt(capacity);
            
            value[i] = forDistribution;
        }
        
        System.out.println("---------------------------");
        // printing the sizes of items
        System.out.println("The size array");
        System.out.println("---------------------------");
        for(int i=0 ; i < noOfItems ; i++)
            System.out.print( size[i]+","); 
        
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        // printing the values of items
        System.out.println("The value array");
        System.out.println("---------------------------");
        for(int i=0 ; i < noOfItems ; i++)
            System.out.print( value[i]+",");
        
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("the max value is");
        System.out.println(knapsack(noOfItems,capacity));
        
        /* we will get the items which gives the optimal solution by this block
         * They are present at the position 
         * [the item number picked][capacity left after picking up the previous item]
         */
  
        System.out.println("items kept");
        System.out.println("---------------------------");
        // We are tracing back here
        while(noOfItems >= 0)
        {
            if(keep[noOfItems][capacity] == 1)
            {
                System.out.println("item"+(noOfItems+1));
                // item picked hence capacity reduced
                capacity-=size[noOfItems];
                // moving to next item, one row above
                noOfItems--;                
            }
            else
            {
                noOfItems--;
            }
        }   
        System.out.println("---------------------------");
    }
}
