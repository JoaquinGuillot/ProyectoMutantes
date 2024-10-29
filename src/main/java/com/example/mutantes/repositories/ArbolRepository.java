package com.example.mutantes.repositories;

import com.example.mutantes.search.Arbol;

public class ArbolRepository {

        private final Arbol arbol;

        public ArbolRepository() {
            this.arbol = new Arbol();
        }

        public void addWord(String word) {
            arbol.insert(word);
        }

        public boolean searchWord(String word) {
            return arbol.search(word);
        }

        public Arbol getArbol() {
            return arbol;
        }
    }

