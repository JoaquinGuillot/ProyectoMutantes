package com.example.mutantes.search;

public class Arbol {
    private final Nodo raiz;
    private String[] adn;
    public Arbol() {
        raiz = new Nodo();
    }

    public void insert(String[] adn) {
        this.adn = adn;
    }

    public void insertar(String palabra) {
        palabra = palabra.toUpperCase(); // Convertir a mayúsculas
        Nodo nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int indice = charAIndice(c);
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new Nodo();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esFinDePalabra = true;
    }

    public boolean buscar(String palabra) {
        palabra = palabra.toUpperCase(); // Convertir a mayúsculas
        Nodo nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int indice = charAIndice(c);
            if (nodo.hijos[indice] == null) {
                return false;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo.esFinDePalabra;
    }

    private int charAIndice(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: throw new IllegalArgumentException("Carácter inválido: " + c);
        }
    }

}
