#include <iostream>
#include <omp.h>
 
using namespace std;
 
#include <stdio.h>
 
 
int isPrime(long num) {
    long k = 2;
    int flag = 0;
 
    while (k * k <= num) {
        if (num % k == 0) {
            flag = 1;
            break;
        }
        k++;
    }
 
    if (flag == 0) {
        return 1;
    }
 
    return 0;
}
 
 
int main()
{
    long count = 0;
    int n;
    long array[2000];g
 
    for (int i = 0; i < n; i++) {
        cin >> array[i];
    }
 
 
int i, sum = 0;
#pragma omp parallel shared(a) reduction (+: i, sum) num_threads(4)
  { 
    #pragma omp for
    for (i=0;i<n;i++) {
      sum += isPrime(array[i]);
    }
  }
 
  cout << sum;
  return 0;
}