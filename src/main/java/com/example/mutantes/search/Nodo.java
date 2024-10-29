package com.example.mutantes.search;
import java.util.HashMap;
import java.util.Map;

public class Nodo {
    Map<Character, Nodo> hijo = new HashMap<>();
    boolean isEndOfWord = false;
}
