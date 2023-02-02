package com.aegis.TechMarket.Controllers;

import com.aegis.TechMarket.DataTransferObjects.AdDto;
import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Services.AdService;
import com.aegis.TechMarket.Services.FileService;
import com.aegis.TechMarket.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.http.*;

@RestController
@RequestMapping("api/ads")
@AllArgsConstructor
public class AdController {


    private UserService userService;


    private AdService adService;

    private FileService fileService;


    @GetMapping
    public ResponseEntity<List<Ad>> getAll() {
        return ResponseEntity.ok(adService.getAll());
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Ad>> getAllAdsByUserId(@PathVariable(value = "user_id") long user_id) {
        var user = userService.findById(user_id);
        List<Ad> ads = adService.findByUser(user_id);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(adService.findById(id));
    }

    @PostMapping("/users/{user_id}")
    public ResponseEntity<Ad> create(@PathVariable long user_id, @RequestBody AdDto adRequest) {
        var user = userService.findById(user_id);
        adRequest.setUser(user);
        return ResponseEntity.ok(adService.create(adRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> update(@PathVariable long id, @RequestBody Ad requestAd){
        return new ResponseEntity<>(adService.update(requestAd), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Ad>> getByCategory(@PathVariable String category) {
        var ads = adService.findByCategory(Enums.Category.valueOf(category.toUpperCase()));
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("descprice")
    public ResponseEntity<List<Ad>> orderByStartPriceDescending() {
        var ads = adService.orderByStartPriceDesc();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("ascprice")
    public ResponseEntity<List<Ad>> orderByStartPriceAscending() {
        var ads = adService.orderByStartPriceAsc();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("minmax")
    public ResponseEntity<List<Ad>> minMax() {
        var ads = adService.findByStartPriceBetweenMinMax(1.0F, 15.0F);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("catminmax")
    public ResponseEntity<List<Ad>> catMinMax() {
        var ads = adService.findByCategoryAndStartPriceBetweenMinMax(Enums.Category.DRONES,1.0F, 15.0F);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("findbynameanduser/{id}")
    public ResponseEntity<List<Ad>> findByNameAndUser(@PathVariable long id, @RequestParam String name) {
        var ads = adService.searchByUser(name, id);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
