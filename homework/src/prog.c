#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include<stdio.h>
#include<math.h>


int main(int argc, char *argv[]) {
   
int n = 5;                                  // (1)
int *array = malloc(sizeof(int) * n);       // (2)
for (int i = 0; i < n; i++)                 // (3)
        array[i] = rand() % 10;             // (4)

int i, j, step;                             // (5)
int tmp;                                    // (6)  
for (step = n / 2; step > 0; step /= 2)     // (7)
    for (i = step; i < n; i++)              // (8)
    {   
        tmp = array[i];                      // (9)
        for (j = i; j >= step; j -= step)   // (10)
        {
            if (tmp < array[j - step])       // (11)
                array[j] = array[j - step];   // (12)
            else                           
                break;                      // (13)
        }
        array[j] = tmp;                      // (14)
    }

for (int i = 0; i < n; i++) {               // (15)
    printf("%d ", array[i]);                // (16)
}
free(array);                               // (17)
    return 0;
}