knapsack-problem-and-analysis
=============================

This project has programming implementation of the knapsack problem as "recursive approach", "Caching approach", "Dynamic programming approach" and "linear scanning approach". It also has empirical analysis of all of the approach

==============================================
ReadMe File
==============================================

The java program files are named as dynamicProgKnapsack, recursiveKnapsack, cachingKnapsack, LinearSpaceKnapsack for 
dynamic programming approach, recursive approach, caching approach and solving knapsack problem in linear spacerespectively. The execution time is calculated by the Profiling tool provided by the NetBeans IDE 7.4. 
These values are plotted in the graph. You can change the number of items in all the programs. In caching and dynamic program you can change for narrow and wide size and value distribution by removing comments on one line and
adding a comment on the previous line. The code is easily customizable.

=====================================================================================================
Reading Graphs:
The name of the file is "empirical analysis1" and "empirical analysis2".
=====================================================================================================

----------------
Recursive graph:(empirical analysis1)
----------------
This graph is made in MS Excel. The x-axis is the "no of items" and y-axis is the "execution time in 
milliseconds". The y-axis is of the logarithmic form, x-axis is regular and the curve fitted to the graph is of linear form.
Since it is in the linear form, the slope is readily obtained from the equation y=mx+c, where m is the 
slope. This graph can be found in the sheet 1 of empirical analysis file.

--------------------------
Caching approach analysis: (empirical analysis1)
--------------------------
This graph is made in MS Excel and can be found in sheet 2 of the file. x-axis contains the 
"log(No of items)" values and y-axis has the "log(execution time in milliseconds)" values. There is no
curve fitting and slope is calculated from the last two points. This experiment is done for two types of
inputs - wide and narrow.

--------------------------
Dynamic Approach Analysis: (empirical analysis1)
--------------------------
This graph is made in MS Excel and can be found in sheet 3 of the file. x-axis contains the 
"log(No of items)" values and y-axis has the "log(execution time in milliseconds)" values. There is no
curve fitting and slope is calculated from the last two points. This experiment is done for two types of
inputs - wide and narrow.

-----------------------------------------
Divide and conquer Linear space knapsack: (empirical analysis2)
-----------------------------------------
This graph is made in MS Excel and can be found in sheet 1 of the file. x-axis contains the 
"log(No of items)" values and y-axis has the "log(execution time in milliseconds)" values. There is
curve fitting and slope is calculated from the equation of the trendline.
