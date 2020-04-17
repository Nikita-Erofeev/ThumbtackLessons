package net.thumbtack.school.base;

import java.util.stream.IntStream;

public class StringOperations {
    public static int getSummaryLength(String[] strings) {
        int lenght = 0;
        for (String string : strings) {
            lenght += string.length();
        }
        return lenght;
    }

    public static String getFirstAndLastLetterString(String string) {
        return string.substring(0, 1) + string.substring(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        if (string1.charAt(index) == (string2.charAt(index))) {
            return true;
        }
        return false;
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        int pos1 = string1.indexOf(character);
        int pos2 = string2.indexOf(character);
        if (pos1 == pos2) {
            return true;
        }
        return false;
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        int pos1 = string1.lastIndexOf(character);
        int pos2 = string2.lastIndexOf(character);
        if (pos1 == pos2) {
            return true;
        }
        return false;
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        int pos1 = string1.indexOf(str);
        int pos2 = string2.indexOf(str);
        if (pos1 == pos2) {
            return true;
        }
        return false;
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        int pos1 = string1.lastIndexOf(str);
        int pos2 = string2.lastIndexOf(str);
        if (pos1 == pos2) {
            return true;
        }
        return false;
    }

    public static boolean isEqual(String string1, String string2) {
        if (string1.equals(string2)) {
            return true;
        }
        return false;
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        if (string1.equalsIgnoreCase(string2)) {
            return true;
        }
        return false;
    }

    public static boolean isLess(String string1, String string2) {
        if (string1.compareTo(string2) < 0) {
            return true;
        }
        return false;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        if (string1.compareToIgnoreCase(string2) < 0) {
            return true;
        }
        return false;
    }

    public static String concat(String string1, String string2) {
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        if (string1.startsWith(prefix) & string2.startsWith(prefix)) {
            return true;
        }
        return false;
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        if (string1.endsWith(suffix) & string2.endsWith(suffix)) {
            return true;
        }
        return false;
    }

    public static String getCommonPrefix(String string1, String string2) {
        StringBuilder sb = new StringBuilder();
        int[] c1 = string1.chars().toArray();
        int[] c2 = string2.chars().toArray();
        for (int i = 0; i < c1.length && i < c2.length && c1[i] == c2[i]; i++) {
            sb.append((char) c1[i]);
        }
        return sb.toString();
    }

    public static String reverse(String string) {
        return new StringBuffer(string).reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        int[] c1 = string.chars().toArray();
        for (int i = 0; i < string.length() / 2; i++) {
            if (c1[i] != c1[string.length() - 1 - i])
                return false;
        }
        return true;
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        String string2 = new StringBuffer(string).reverse().toString();
        if (string.equalsIgnoreCase(string2)) {
            return true;
        }
        return false;
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String result = "";
        for (String string : strings) {
            String string2 = new StringBuffer(string).reverse().toString();
            if (string.equalsIgnoreCase(string2) && string.length() >= result.length()) {
                result = string;
            }
        }
        return result;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        try {
            if (string1.substring(index, index + length).equals(string2.substring(index, index + length))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
        if (string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2))) {
            return true;
        }
        return false;
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        if (string1.replaceAll(replaceInStr1, replaceByInStr1).equals(string2.replaceAll(replaceInStr2, replaceByInStr2))) {
            return true;
        }
        return false;
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String s = string.replaceAll("\\s", "");
        String s2 = new StringBuffer(s).reverse().toString();
        if (s.equalsIgnoreCase(s2)) {
            return true;
        }
        return false;
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        if (string1.trim().equals(string2.trim())) {
            return true;
        }
        return false;
    }

    public static String makeCsvStringFromInts(int[] array) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            s.append(array[i]);
            if (i + 1 < array.length) {
                s.append(",");
            }
        }
        return s.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        StringBuilder s = new StringBuilder();
        String t;
        for (int i = 0; i < array.length; i++) {
            t = String.format("%.2f", array[i]);
            s.append(t);
            if (i + 1 < array.length) {
                s.append(",");
            }
        }
        return s.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            s.append(array[i]);
            if (i + 1 < array.length) {
                s.append(",");
            }
        }
        return s;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        StringBuilder s = new StringBuilder();
        String t;
        for (int i = 0; i < array.length; i++) {
            t = String.format("%.2f", array[i]);
            s.append(t);
            if (i + 1 < array.length) {
                s.append(",");
            }
        }
        return s;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder sb = new StringBuilder().append(string);
        int i = 0;
        for (int position : positions) {
            sb.deleteCharAt(position - i);
            i++;
        }
        return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder sb = new StringBuilder().append(string);
        int j = 0;
        for (int i = 0; i < positions.length; i++) {
            sb.insert(positions[i] + j, characters[i]);
            j++;
        }
        return sb;
    }
}
