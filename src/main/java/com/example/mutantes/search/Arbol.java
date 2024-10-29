package com.example.mutantes.search;

import java.io.Serializable;

public class Arbol {
    private Nodo root;

    public Arbol() {
        root = new Nodo();
    }

    // Método para insertar una palabra en el Trie
    public void insert(String word) {
        Nodo node = root;
        for (char ch : word.toCharArray()) {
            node.hijo.putIfAbsent(ch, new Nodo());
            node = node.hijo.get(ch);
        }
        node.isEndOfWord = true;
    }

    // Método para buscar una palabra en el Trie
    public boolean search(String word) {
        Nodo node = root;
        for (char ch : word.toCharArray()) {
            if (!node.hijo.containsKey(ch)) {
                return false;
            }
            node = node.hijo.get(ch);
        }
        return node.isEndOfWord;
    }

    // Método para obtener el nodo raíz
    public Nodo getRoot() {
        return root;
    }
}