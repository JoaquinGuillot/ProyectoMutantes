package com.example.mutantes.search;

import java.util.*;

public class PatternSearch {

    private static Map<Character, List<int[]>> searchPatternsInMatrix(char[][] matrix) {
        Map<Character, List<int[]>> matches = new HashMap<>();

        // Definir letras a buscar
        char[] targetChars = {'a', 't', 'g', 'c'};

        // Definir direcciones: (deltaRow, deltaCol)
        int[][] directions = {
                {0, 1},   // Horizontal derecha
                {1, 0},   // Vertical abajo
                {1, 1},   // Diagonal abajo derecha
                {1, -1},  // Diagonal abajo izquierda
        };

        // Buscar en la matriz en diferentes direcciones
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                for (char targetChar : targetChars) {
                    for (int[] dir : directions) {
                        searchFromCell(row, col, dir[0], dir[1], matrix, targetChar, matches);
                    }
                }
            }
        }

        return matches;
    }

    private static void searchFromCell(int row, int col, int deltaRow, int deltaCol, char[][] matrix, char targetChar, Map<Character, List<int[]>> matches) {
        int count = 0;
        int r = row, c = col;

        // Continuar mientras esté dentro de los límites de la matriz
        while (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length) {
            if (matrix[r][c] == targetChar) {
                count++;
            } else {
                count = 0; // Reiniciar el conteo si no coincide
            }

            // Si se encuentra el patrón de al menos 4 repeticiones
            if (count >= 4) {
                matches.putIfAbsent(targetChar, new ArrayList<>());
                matches.get(targetChar).add(new int[]{r, c}); // Guardar la posición
            }

            // Moverse en la dirección actual
            r += deltaRow;
            c += deltaCol;
        }
    }
}