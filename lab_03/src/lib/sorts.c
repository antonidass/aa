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


double count_time_choice_sort(int array[], int n) 
{
    clock_t start, end;    

    start = clock();
    choice_sort(array, n);
    end = clock();

    return  (double)(end - start) / CLOCKS_PER_SEC;
}

double count_time_quick_sort(int array[], int n) 
{
    clock_t start, end;    

    start = clock();
    quick_sort(array, 0, n);
    end = clock();

    return  (double)(end - start) / CLOCKS_PER_SEC;
}



int *generate_array(int size)
{
    int *arr = (int *) malloc(sizeof(int) * size);
    for (int i = 0; i < size; i++)
    {
        arr[i] = rand() % 10000;
    }
    return arr;
}


void write_file(FILE *file, double times[], int n)
{
    for (int i = 0; i <= n - 1; i++)
    {
        fprintf(file, "%lf,%d\n", times[i], i * 10);
    }
}

void count_times(FILE *file, double (*fun)(int *, int))
{
    double times[100]; 
    for (int i = 1, j = 0; i < 1000; i += 10, j++)
    {
        int *arr = generate_array(i);
        double time = fun(arr, i);
        times[j] = time;
        free(arr);
    }  
    write_file(file, times, 100);
}




int main()
{
    FILE *file = fopen("quick.txt", "w");
    count_times(file, count_time_quick_sort);
    fclose(file);

    FILE *file1 = fopen("choice.txt", "w");
    count_times(file1, count_time_choice_sort);
    fclose(file1);

    FILE *file2 = fopen("insert.txt", "w");
    count_times(file2, count_time_insertion_sort);
    fclose(file2);

    printf("Input size of array : ");
    int n;
    scanf("%d", &n);

    int *arr = input_array(n);

    printf("Choice sort : \n1 - quick\n2 - insertion\n3 - choice\n");
    int choice;
    scanf("%d", &choice);

    if (choice == 1) {
        quick_sort(arr, 0, n - 1);
    } else if (choice == 2) {
        insertion_sort(n, arr);
    } else {
        choice_sort(arr, n);
    }

    print_array(arr, &n);
    free(arr);

    return 0;
}