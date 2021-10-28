import java.util.ArrayList;

public class Levenshtein {
    public static int getIntBoolean(char first, char second) {
        if (first == second) {
            return 0;
        }
        return 1;
    }

    public static int Levenshtein(String first, String second) {
        int n = first.length();
        int m = second.length();

        int[][] matrix = new int[n + 1][m + 1];
        matrix[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            matrix[i][0] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            matrix[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                matrix[i][j] = Math.min(
                    Math.min(
                            matrix[i - 1][j - 1] + getIntBoolean(first.charAt(i - 1),
                            second.charAt(j -1)), matrix[i - 1][j] + 1),
                    matrix[i][j - 1] + 1
                );
            }
        }

        return matrix[n][m];
    }

    public static int LevenshteinRecursion(String first, String second) {
        if (first == "" || second == "") {
            return Math.abs(first.length() - second.length());
        }

        int temp = (first.charAt(first.length() - 1) == second.charAt(second.length() - 1)) ? 0 : 1;
        return Math.min(
                Math.min(
                        LevenshteinRecursion(first, second.substring(0, second.length() - 1)) + 1,
                        LevenshteinRecursion(first.substring(0, first.length() - 1), second) + 1),
                LevenshteinRecursion(first.substring(0, first.length() - 1), second.substring(0, second.length() - 1)) + temp
        );
    }


    public static int DamerauLevenshteinRecursion(String first, String second) {
        if (first == "" || second == "") {
            return Math.abs(first.length() - second.length());
        }
        int temp = (first.charAt(first.length() - 1) == second.charAt(second.length() - 1)) ? 0 : 1;

        int result = Math.min(
                DamerauLevenshteinRecursion(first, second.substring(0, second.length() - 1)) + 1,
                Math.min(
                        DamerauLevenshteinRecursion(first.substring(0, first.length() - 1), second) + 1,
                        DamerauLevenshteinRecursion(first.substring(0, first.length() - 1), second.substring(0, second.length() - 1)) + temp
                )
        );

        if (first.length() > 1 && second.length() > 1 &&
            first.charAt(first.length() - 1) == second.charAt(second.length() - 2) &&
            first.charAt(first.length() - 2) == second.charAt(second.length() - 1) ) {
                result = Math.min(
                        result,
                        DamerauLevenshteinRecursion(first.substring(0, first.length() - 2),
                                second.substring(0, second.length() - 2)) + 1);
            }

        return result;
    }


    public static int DamerauLevenshtein(String first, String second) {
        int n = first.length();
        int m = second.length();

        int[][] matrix = new int[n + 1][m + 1];
        matrix[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            matrix[i][0] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            matrix[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                matrix[i][j] = Math.min(
                        Math.min(
                                matrix[i - 1][j - 1] + getIntBoolean(first.charAt(i - 1), second.charAt(j -1)),
                                matrix[i - 1][j] + 1),
                        matrix[i][j - 1] + 1
                );
                if (i > 1 && j > 1 && first.charAt(i - 1) == second.charAt(j - 2) &&
                    first.charAt(i - 2) == second.charAt(j - 1)) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + 1);
                }
            }
        }

        return matrix[n][m];
    }
}
