package com.aegis.TechMarket.Controllers;

import com.aegis.TechMarket.Services.AdKeywordService;
import com.aegis.TechMarket.Services.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/adkeyword")
@AllArgsConstructor
public class AdKeywordController {

    private final AdKeywordService adKeywordService;

    private final KeywordService keywordService;


    @PostMapping("/{adId}")
    public ResponseEntity<?> save(@PathVariable long adId, @RequestParam String keywords){
        return ResponseEntity.ok(adKeywordService.saveMultiple(adId, keywords));
    }


    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(adKeywordService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getByKeyword(@RequestParam String keyword){
        return ResponseEntity.ok(adKeywordService.findByKeyword(keyword));
    }

    @GetMapping("/price")
    public ResponseEntity<?> getByPrice(@RequestParam String keyword){
        var id = keywordService.findByName(keyword).getId();
        return ResponseEntity.ok(adKeywordService.getAdsByPrice(id, 1F, 15F));
    }

    @GetMapping("/keys")
    public ResponseEntity<?> getKeywordsByAds(){
        return ResponseEntity.ok(adKeywordService.getKeywordsByAd(195L));
    }

}
