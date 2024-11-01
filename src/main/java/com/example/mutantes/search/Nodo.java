package com.example.mutantes.search;

public class Nodo {
    Nodo[] hijos;
    boolean esFinDePalabra;

    public Nodo() {
        hijos = new Nodo[4]; // Para A, C, G, T
        esFinDePalabra = false;
    }
}