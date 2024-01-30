# PERCOLATION PROBLEM

The following is a solution to the Percolation problem given a system of size N*N

The solution follows the following problem solvind criteria:

1. create the system using a 2D array
2. choose a probability
3. iterate through each site and open the site with respect to the probability
4. connect the open sites
5. connection only happens if the neighbouring sites of an open site are open
6. iterate the first and last row while checking whether there is a connection


To solve this problem, another array parent is needed to keep track of connected sites.
The Quick union algorithm is essential in:

1. connecting open sites
2. finding whether there is a connection between the top row and bottom row

if there is a connection betweem the top row and bottom row the system percolates.


# Note

Notice that the higher the probability the more likely the system will percolate and vice versa.