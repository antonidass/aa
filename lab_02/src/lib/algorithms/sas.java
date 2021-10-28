public static Double [][] standartMult(Double[][] matrixA, Double[][] matrixB) {
        int n = matrixA.length;
        int m = matrixB[0].length;
        int q = matrixB.length;

        Double[][] matrixC = new Double[n][m];
        for (int i = 0; i < n; i++) {
        Arrays.fill(matrixC[i], 0.0);
        }

        for (int i = 0; i < n; i++) {
        for (int j  = 0; j < m; j++) {
        for (int k = 0; k < q; k++) {
        matrixC[i][j] += (matrixA[i][k] * matrixB[k][j]);
        }
        }
        }

        return matrixC;
        }