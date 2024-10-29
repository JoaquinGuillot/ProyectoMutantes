package com.example.mutantes.controllers;
import com.example.mutantes.services.ArbolService;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/trie")
    public class ArbolController {
        private final ArbolService arbolService;

        public ArbolController(ArbolService ArbolService) {
            this.arbolService = ArbolService;
        }

        @PostMapping("/add")
        public void addWord(@RequestBody String word) {
            arbolService.initializeWords(new String[]{word});
        }

        @GetMapping("/search")
        public boolean searchWord(@RequestParam String word) {
            return arbolService.searchWord(word);
        }
    }

