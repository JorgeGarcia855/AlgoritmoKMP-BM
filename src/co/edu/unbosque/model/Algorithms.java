package co.edu.unbosque.model;

import java.util.ArrayList;

public class Algorithms {
    private static final ArrayList<Integer> indexesKMP = new ArrayList<>();
    private static final ArrayList<Integer> indexesBM = new ArrayList<>();
    private static final int NO_OF_CHARS = 256;

    public static void KMP(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int[] lps = new int[M];
        int j = 0;

        computeLPS(pat, M, lps);
        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                indexesKMP.add(i - j);
                j = lps[j - 1];
            }
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) j = lps[j - 1];
                else i = i + 1;
            }
        }
    }
    private static void computeLPS(String pat, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) len = lps[len - 1];
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    private static void badCharHeuristic(char[] str, int size, int[] badchar) {
        for (int i = 0; i < NO_OF_CHARS; i++) badchar[i] = -1;
        for (int i = 0; i < size; i++) badchar[str[i]] = i;
    }

    public static void BM(char[] txt, char[] pat) {
        int m = pat.length;
        int n = txt.length;
        int[] badchar = new int[NO_OF_CHARS];

        badCharHeuristic(pat, m, badchar);
        int s = 0;
        while(s <= (n - m)) {
            int j = m-1;
            while(j >= 0 && pat[j] == txt[s+j]) j--;
            if (j < 0 && pat[0] != '\0') {
                indexesBM.add(s);
                s += (s+m < n)? m-badchar[txt[s+m]] : 1;
            }
            else s += Math.max(1, j - badchar[txt[s+j]]);
        }
    }

    public static ArrayList<Integer> getIndexesKMP() {
        return indexesKMP;
    }

    public static ArrayList<Integer> getIndexesBM() {
        return indexesBM;
    }
}
