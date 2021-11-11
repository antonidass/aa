public class Prime {
    public static int isPrime(long num) {
        long k = 2;
        int flag = 1;

        while (k * k <= num) {
            if (num % k == 0) {
                flag = 0;
                break;
            }
            k++;
        }

        return flag;
    }
}
