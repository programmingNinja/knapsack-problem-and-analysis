
import java.lang.reflect.Array;
import java.util.Random;

/*
 * Rohan D. Shah 
 * IDE: NetBeans 7.4
 * Programming language = JAVA
 * Solution as a caching recursive algorithm.
 *
 * Description:
 * This program implements knapsack problem using recursion. It DOES STORE the already calculated values.
 * To calculate the execution time, I have used profiling tool in the IDE. It starts and stops the stop watch
 * at defined points. I have defined the start point at 132 and end point at 194
 * The program maximizes the value in the knapsack and also tells which items will give the maximized value.
 * the logic of the problem is that there are two choices which the user can make - either take the item or not.
 * The entire program and recursion depends on it. The sizes and values of the items are assigned using
 * uniform random distribution between 1 and 1000 inclusive.
 *
 * --------------------------------------------------------------
 * Logic for storing the item which gives the optimal solution:
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

public class cachingKnapsack 
{
    // Total number of items
    static int noOfItems = 3;
    
    // Capacity of the knapsack
    static int capacity = noOfItems*10;
    
    // Min size for empirical analysis(wide = 1 and narrow = capacity/20)
    static int minSize = 1;
    //static int minSize = capacity/20;
    
    // To compute the items picked. 
    // Size is capacity+1 because the index is the capacity left
    // has the value of the item if that item's value is computed and '0' if not computed
    static int seen[][]= new int[capacity+1][capacity+1];
    
   // Stores the values of the items
    static int[] value = new int[noOfItems];    
    
    // Stores the size/weight of the items
    static int[] size = new int[noOfItems];  
    
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
        
        // CACHING: if value already computed then return that value
        if (seen[index][capacity]!=0)
        return seen[index][capacity];
        
        // for the first item
        if(index==0)
        {
            // capacity of the sack will not be exceeded if item0 picked
            if(size[0] <= capacity)
            {
                // current item picked and value stored
                keep[index][capacity] = 1;
                seen[index][capacity] = value[0];
                return value[0];
            }
            else
            {
                // current item not picked as its size exceeds the capacity of the sack
                // hence value not stored
                keep[index][capacity] = 0;
                seen[index][capacity] = 0;
                return 0;
            }
        }
        
        // if i pick the item the size of the item will either be equal to the capacity
        // or less than that. Hence i will pick it up
        // picking up increases value but reduces the capacity of the sack
        if(size[index] <= capacity)
            take = knapsack(index-1, capacity - size[index]) + value[index];
           
        // Choice 2 - of not picking it up.
            dontTake = knapsack(index-1 , capacity);
        
            // optimal solution, Maximized value stored
        seen[index][capacity] = Math.max(take, dontTake);
        
        // if the item which i am picking gives the optimal solution then
        // i will store it
        if( take>dontTake)
        {
            keep[index][capacity] = 1;
        }
        else
        {
            keep[index][capacity] = 0;
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
        // Fill in size and values of items with random numbers between 1 and 1000 inclusive
        for(int i=0 ; i<noOfItems ; i++)
        {
            forDistribution = 0;
            // randomGenerator() gives random number from minsize to specified value(capacity), 
            // but we don't want 0 to be our size and value
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
            System.out.println("item" +(i+1) +" "+ size[i]); 
        
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        // printing the values of items
        System.out.println("The value array");
        System.out.println("---------------------------");
        for(int i=0 ; i < noOfItems ; i++)
            System.out.println("item" +(i+1) +" "+ value[i]);
        
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("the max value is");
        System.out.println(knapsack(noOfItems-1,capacity));
        
        /* we will get the items which gives the optimal solution by this block
         * They are present at the position 
         * [the item number picked][capacity left after picking up the previous item]
         */
        System.out.println("items kept");
        System.out.println("---------------------------");
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
