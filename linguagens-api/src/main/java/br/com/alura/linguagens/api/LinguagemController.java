package br.com.alura.linguagens.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController{

    private List<Linguagem> linguagens = List.of(
        new Linguagem("Java", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java.png", 1),
        new Linguagem("C#", "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/csharp/csharp.png", 2)    
    );

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        return linguagens;
    }
}