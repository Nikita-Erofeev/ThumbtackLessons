package net.thumbtack.school.introduction;

public class FirstSteps {
    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        if (x == y)
            return true;
        return false;
    }

    public boolean isGreater(int x, int y) {
        if (x > y)
            return true;
        return false;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        if ((x >= xLeft & x <= xRight) & (y >= yTop & y <= yBottom))
            return true;
        return false;
    }

    public int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public int mul(int[] array) {
        int mul;
        if (array.length != 0)
            mul = array[0];
        else
            return 0;
        for (int i = 1; i < array.length; i++) {
            mul *= array[i];
        }
        return mul;
    }

    public int min(int[] array) {
        int min;
        if (array.length != 0)
            min = array[0];
        else
            return Integer.MAX_VALUE;
        for (int i = 1; i < array.length; i++) {
            if (min > array[i])
                min = array[i];
        }
        return min;
    }

    public int max(int[] array) {
        int max;
        if (array.length != 0)
            max = array[0];
        else
            return Integer.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        return max;
    }

    public double average(int[] array) {
        double sum = 0;
        if (array.length == 0)
            return sum;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    public boolean isSortedDescendant(int[] array) {
        if (array.length == 0)
            return true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1])
                return false;
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }

    public void reverse(int[] array) {
        int t, mid;
        mid = array.length / 2;
        for (int i = 0; i < mid; i++) {
            t = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = t;
        }
    }

    public boolean isPalindrome(int[] array) {
        if (array.length == 0)
            return true;
        int mid = array.length / 2;
        for (int i = 0; i < mid; i++) {
            if (array[i] != array[array.length - 1 - i])
                return false;
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (max < matrix[i][j])
                    max = matrix[i][j];
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (max < matrix[i][i])
                max = matrix[i][i];
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] <= matrix[i][j + 1])
                    return false;
            }
        }
        return true;
    }

}
