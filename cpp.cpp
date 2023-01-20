using namespace std;

#define ARRAYSIZE(a) (sizeof(a)) / (sizeof(a[0]))
static int total_nodes;

// prints subset found
void printSubset(int A[], int size)
{
    for (int i = 0; i < size; i++)
    {
        cout << " " << A[i];
    }
    cout << "\n";
}

// qsort compare function
int comparator(const void *pLhs, const void *pRhs)
{
    int *lhs = (int *)pLhs;
    int *rhs = (int *)pRhs;
    return *lhs > *rhs;
}

// inputs
// s		 - set vector
// t		 - tuplet vector
// s_size	 - set size
// t_size	 - tuplet size so far
// sum		 - sum so far
// ite		 - nodes count
// target_sum - sum to be found
void subset_sum(int s[], int t[],
                int s_size, int t_size,
                int sum, int ite,
                int const target_sum)
{
    total_nodes++;

    if (target_sum == sum)
    {
        // We found sum
        printSubset(t, t_size);

        // constraint check
        if (ite + 1 < s_size && sum - s[ite] + s[ite + 1] <= target_sum)
        {

            // Exclude previous added item and consider next candidate
            subset_sum(s, t, s_size, t_size - 1, sum - s[ite], ite + 1, target_sum);
        }
        return;
    }
    else
    {

        // constraint check
        if (ite < s_size && sum + s[ite] <= target_sum)
        {

            // generate nodes along the breadth
            for (int i = ite; i < s_size; i++)
            {
                t[t_size] = s[i];
                if (sum + s[i] <= target_sum)
                {

                    // consider next level node (along depth)
                    subset_sum(s, t, s_size, t_size + 1, sum + s[i], i + 1, target_sum);
                }
            }
        }
    }
}

// Wrapper that prints subsets that sum to target_sum
void generateSubsets(int s[], int size, int target_sum)
{
    int *tuplet_vector = (int *)malloc(size * sizeof(int));
    int total = 0;

    // sort the set
    qsort(s, size, sizeof(int), &comparator);
    for (int i = 0; i < size; i++)
    {
        total += s[i];
    }
    if (s[0] <= target_sum && total >= target_sum)
    {
        subset_sum(s, tuplet_vector, size, 0, 0, 0, target_sum);
    }
    free(tuplet_vector);
}

// Driver code
int main()
{
    int weights[] = {15, 22, 14, 26, 32, 9, 16, 8};
    int target = 53;
    int size = ARRAYSIZE(weights);
    generateSubsets(weights, size, target);
    cout << "Nodes generated " << total_nodes;
    return 0;
}

// This code is contributed by shivanisinghss2110

/* A Naive C++ recursive implementation
of LIS problem */
using namespace std;

int _lis(int arr[], int n, int *max_ref)
{
    /* Base case */
    if (n == 1)
        return 1;
    int res, max_ending_here = 1;

    for (int i = 1; i < n; i++)
    {
        res = _lis(arr, i, max_ref);
        if (arr[i - 1] < arr[n - 1] && res + 1 > max_ending_here)
            max_ending_here = res + 1;
    }

    // Compare max_ending_here with the overall
    // max. And update the overall max if needed
    if (*max_ref < max_ending_here)
        *max_ref = max_ending_here;

    // Return length of LIS ending with arr[n-1]
    return max_ending_here;
}

// The wrapper function for _lis()
int lis(int arr[], int n)
{
    // The max variable holds the result
    int max = 1;

    // The function _lis() stores its result in max
    _lis(arr, n, &max);

    // returns max
    return max;
}

/* Driver program to test above function */
int main()
{
    int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
    int n = sizeof(arr) / sizeof(arr[0]);
    cout << "Length of lis is " << lis(arr, n);
    return 0;
}
// This code is contributed by shivanisinghss2110

/* Dynamic Programming C++ implementation
of LIS problem */
using namespace std;

/* lis() returns the length of the longest
increasing subsequence in arr[] of size n */
int lis(int arr[], int n)
{
    int lis[n];

    lis[0] = 1;

    /* Compute optimized LIS values in
    bottom up manner */
    for (int i = 1; i < n; i++)
    {
        lis[i] = 1;
        for (int j = 0; j < i; j++)
            if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                lis[i] = lis[j] + 1;
    }

    // Return maximum value in lis[]
    return *max_element(lis, lis + n);
}

/* Driver program to test above function */
int main()
{
    int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
    int n = sizeof(arr) / sizeof(arr[0]);
    printf("Length of lis is %d\n", lis(arr, n));

    return 0;
}

/* Dynamic Programming implementation of MCP problem */
#define R 3
#define C 3
using namespace std;

int min(int x, int y, int z);

int minCost(int cost[R][C], int m, int n)
{
    int i, j;

    // Instead of following line, we can use int
    // tc[m+1][n+1] or dynamically allocate memory to save
    // space. The following line is used to keep the program
    // simple and make it working on all compilers.
    int tc[R][C];

    tc[0][0] = cost[0][0];

    /* Initialize first column of total cost(tc) array */
    for (i = 1; i <= m; i++)
        tc[i][0] = tc[i - 1][0] + cost[i][0];

    /* Initialize first row of tc array */
    for (j = 1; j <= n; j++)
        tc[0][j] = tc[0][j - 1] + cost[0][j];

    /* Construct rest of the tc array */
    for (i = 1; i <= m; i++)
        for (j = 1; j <= n; j++)
            tc[i][j] = min(tc[i - 1][j - 1], tc[i - 1][j],
                           tc[i][j - 1]) +
                       cost[i][j];

    return tc[m][n];
}

/* A utility function that returns minimum of 3 integers */
int min(int x, int y, int z)
{
    if (x < y)
        return (x < z) ? x : z;
    else
        return (y < z) ? y : z;
}

/* Driver code*/
int main()
{
    int cost[R][C] = {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
    cout << " " << minCost(cost, 2, 2);
    return 0;
}

// This code is contributed by shivanisinghss2110
