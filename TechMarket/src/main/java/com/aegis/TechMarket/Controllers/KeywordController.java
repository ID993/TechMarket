package com.aegis.TechMarket.Controllers;


import com.aegis.TechMarket.Services.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/keywords")
@AllArgsConstructor
public class KeywordController {


    private final KeywordService keywordService;


    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(keywordService.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestParam String name){
        return ResponseEntity.ok(keywordService.create(name));
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(@RequestParam String name){
        return ResponseEntity.ok(keywordService.findByName(name));
    }
}
