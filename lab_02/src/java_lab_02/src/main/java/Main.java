import java.util.Arrays;

public class Main {
    public static Double[][] standartMult(Double[][] matrixA, Double[][] matrixB) {
        int n = matrixA.length;
        int m = matrixB[0].length;
        int q = matrixB.length;

        Double[][] matrixC = new Double[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrixC[i], 0.0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < q; k++) {
                    matrixC[i][j] += (matrixA[i][k] * matrixB[k][j]);
                }
            }
        }

        return matrixC;
    }

    public static Double[][] vinogradMult(Double[][] matrixA, Double[][] matrixB) {
        int n = matrixA.length;
        int m = matrixB[0].length;
        int q = matrixB.length;

        Double[][] matrixC = new Double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixC[i][j] = 0.0;
            }
        }

        Double[] tempA = new Double[n];
        Arrays.fill(tempA, 0.0);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < q; j += 2) {
                tempA[i] += matrixA[i][j - 1] * matrixA[i][j];
            }
        }

        Double[] tempB = new Double[m];
        Arrays.fill(tempB, 0.0);
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < q; j += 2) {
                tempB[i] += matrixB[j - 1][i] * matrixB[j][i];
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixC[i][j] -= (tempA[i] + tempB[j]);
                for (int k = 1; k < q; k += 2) {
                    matrixC[i][j] += (matrixA[i][k - 1] + matrixB[k][j]) * (matrixA[i][k] + matrixB[k - 1][j]);
                }
            }
        }

        if (q % 2 == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrixC[i][j] += matrixA[i][q - 1] * matrixB[q - 1][j];
                }
            }
        }

        return matrixC;
    }


    public static void main(String[] args) {
        System.out.println("Standart:");
        Double[][] temp = {
                {1.0, 1.0},
                {5.0, 1.0}
        };

        Double[][] temp2 = {
                {1.0, 1.0},
                {5.0, 1.0}
        };

        Double[][] res = standartMult(temp, temp2);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nVinograd:");
        Double[][] res2 = vinogradMult(temp, temp2);

        for (int i = 0; i < res2.length; i++) {
            for (int j = 0; j < res2[0].length; j++) {
                System.out.print(res2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
