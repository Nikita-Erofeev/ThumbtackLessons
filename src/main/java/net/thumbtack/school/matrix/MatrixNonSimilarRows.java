package net.thumbtack.school.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MatrixNonSimilarRows {
    private int rows;
    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    private static boolean isSimilarRow(int[] first, int[] second) {
        Set<Integer> firstTree = new TreeSet<>();
        Set<Integer> secondTree = new TreeSet<>();
        for (int t : first) {
            firstTree.add(t);
        }
        for (int t : second) {
            secondTree.add(t);
        }
        return firstTree.containsAll(secondTree) && secondTree.containsAll(firstTree);
    }

    public List<int[]> getNonSimilarRows() {
        List<int[]> result = new ArrayList<>();
        int[][] copyMatrix = matrix.clone();
        boolean forNull = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < copyMatrix.length; j++) {
                if (i != j && copyMatrix[j] != null && isSimilarRow(matrix[i], copyMatrix[j])) {
                    if (j < i) {
                        copyMatrix[j] = matrix[i];
                    } else {
                        copyMatrix[j] = null;
                    }
                }
            }
        }
        for (int[] item : matrix) {
            if (item == null) {
                forNull = true;
            }
        }
        for (int[] item : copyMatrix) {
            if (forNull) {
                result.add(null);
                forNull = false;
            }
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }
}
