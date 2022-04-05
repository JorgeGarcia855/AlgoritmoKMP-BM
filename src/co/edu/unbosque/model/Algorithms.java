package co.edu.unbosque.model;

import java.util.ArrayList;

/**
 * Esta clase contiene los algoritmos para buscar patrones de texto
 * y las listas que permiten acceder a los índices de cada patron
 * encontrado.
 * @author Jorge García
 * @author Oscar Florez
 * @version 1.0
 */
public class Algorithms {
    /**
     * Atributo que almacena los índices del algoritmo KMP.
     */
    private static final ArrayList<Integer> indexesKMP = new ArrayList<>();
    /**
     * Atributo que almacena los índices del algoritmo BM.
     */
    private static final ArrayList<Integer> indexesBM = new ArrayList<>();
    /**
     * Atributo que establece la cantidad de caracteres malos del algoritmo BM.
     */
    private static final int NO_OF_CHARS = 256;

    /**
     * El algoritmo Knuth-Morris-Pratt, modificado para que pueda
     * guardar los índices en un ArrayList.
     * @param pat el patron a buscar.
     * @param txt el texto en donde se va a encontrar el patron.
     * @author Jorge García
     * @author Oscar Florez
     */
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

    /**
     * Calcula el "sufijo de prefijo más alto" o Lowest Prefix Suffix,
     * para luego pre-procesar el patron con el resultado más cercano.
     * @param pat el patron que se está usando.
     * @param M la longitud del patron.
     * @param lps el LPS a calcular.
     * @author Jorge García
     * @author Oscar Florez
     */
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

    /**
     * Este método obtiene el "mal carácter heurístico" o Bad Character Heuristic.
     * Consiste en que si el carácter en el texto no encaja con el carácter en el
     * patron, se va a mover el patron hasta que encaje o el patron se mueve más alla
     * del carácter malo.
     * @param str el patron que se está usando.
     * @param size la longitud del patron.
     * @param badchar el arreglo que contiene las posiciones de los caracteres malos.
     * @author Jorge García
     * @author Oscar Florez
     */
    private static void badCharHeuristic(char[] str, int size, int[] badchar) {
        for (int i = 0; i < NO_OF_CHARS; i++) badchar[i] = -1;
        for (int i = 0; i < size; i++) badchar[str[i]] = i;
    }

    /**
     * El algoritmo Boyer-Moore, modificado para que
     * almacene los índices en un ArrayList.
     * @param txt el texto en donde se va a encontrar el patron.
     * @param pat el patron a buscar.
     * @author Jorge García
     * @author Oscar Florez
     */
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

    /**
     * Permite acceder a los índices del algoritmo KMP
     * en otras clases.
     * @return los índices del algoritmo KMP.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static ArrayList<Integer> getIndexesKMP() {
        return indexesKMP;
    }

    /**
     * Permite acceder a los índices del algoritmo BM
     * en otras clases.
     * @return los índices del algoritmo BM.
     * @author Jorge García
     * @author Oscar Florez
     */
    public static ArrayList<Integer> getIndexesBM() {
        return indexesBM;
    }
}
