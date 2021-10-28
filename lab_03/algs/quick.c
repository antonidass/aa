#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include <time.h>

void print_array(const int *arr, const int *n)
{
    for (int i = 0; i < *n; ++i) {
        printf("%d ", *(arr + i));
    }
    printf("\n");
}

int* input_array(int n)
{
    int *arr = malloc(sizeof(int) * n);

    printf("\nInput array: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", arr + i);
    }
    return arr;
}


void quick_sort(int *array, int left, int right)
{
    int pivot = array[left];
    int l_hold = left;
    int r_hold = right;


    while (left < right)
    {
        while ((array[right] >= pivot) && (left < right))    
            right--;

        if (left != right)
        {
            array[left] = array[right];
            left++;
        }

        while ((array[left] <= pivot) && (left < right))
            left++;

        if (left != right)
        {
            array[right] = array[left];
            right--;
        }
    }
        
    array[left] = pivot;
    pivot = left;
    left = l_hold;
    right = r_hold;

    if (left < pivot)
        quick_sort(array, left, pivot - 1);
    if (right > pivot) 
        quick_sort(array, pivot + 1, right);
}


void insertion_sort(int n, int array[])
{
    int new_elem, location;

    for (int i = 1; i < n; i++)
    {
        new_elem = array[i];
        location = i - 1;
        while(location >= 0 && array[location] > new_elem)
        {
            array[location+1] = array[location];
            location = location - 1;
        }
        array[location+1] = new_elem;
    }
}

void choice_sort(int *arr, int n) 
{
    int min_pos, tmp;
    for (int i = 0; i < n; i++)
    {
        min_pos = i;
        for (int j = i + 1; j < n; j++)
            if (arr[min_pos] > arr[j])
                min_pos = j;

        tmp = arr[min_pos];
        arr[min_pos] = arr[i];
        arr[i] = tmp;
    }
}


double count_time_insertion_sort(int array[], int n) 
{
    clock_t start, end;    
    
    start = clock();
    insertion_sort(n, array);
    end = clock();

    return  (double)(end - start) / CLOCKS_PER_SEC;
}



int main()
{
    // int n;
    // printf("Input size of array: ");
    // scanf("%d", &n);

    // int *arr = input_array(n);


    int arr[] = {4,2,2,1,5,7,1,5,1};
    int n = 9;

    double time = 0;
    for (int i = 0; i < 10000; i++) 
    {
        int arr[] = {4,2,2,1,5,7,1,5,1};
        int n = 9;
        time += count_time_insertion_sort(arr, n);
    }

    printf("%lf", time);



    print_array(arr, &n);


    // free(arr);

    return 0;
}