package com.example.mutantes.services;
import com.example.mutantes.repositories.ArbolRepository;

import java.util.HashMap;
import java.util.Map;

public class ArbolService {

        private final ArbolRepository arbolRepository;

        public ArbolService(ArbolRepository arbolRepository) {
            this.arbolRepository = arbolRepository;
        }

        public void initializeWords(String[] words) {
            for (String word : words) {
                arbolRepository.addWord(word);
            }
        }

        public boolean searchWord(String word) {
            return arbolRepository.searchWord(word);
        }

}
