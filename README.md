# MergeSort
Java app that performs MergeSort on a file that contains positive integer numbers. 
The input file is given through args[0] and read from the main() function. 
The algorithm prints the total cost of inversions. For an array A[1, ..., N], an inversion occurs if A[i] < a[j], while i < j.
The cost for each inversion is 3, but if A[i] = A[j] + 1, the cost is 2 instead. 
The algorithm has a worst case time complexity of O[nlogn)].
