package com.aegis.TechMarket.Services;

import com.aegis.TechMarket.DataTransferObjects.AdDto;
import com.aegis.TechMarket.Entities.FileInfo;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Exceptions.AppException;
import com.aegis.TechMarket.Mappers.AdMapper;
import com.aegis.TechMarket.Repositories.IAdRepository;
import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Repositories.IFileRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class  AdService {


    private final IAdRepository adRepository;

    private final IFileRepository fileRepository;

    private AdMapper adMapper;

    public Ad create(AdDto ad) {
        return adRepository.save(adMapper.adDtoToAd(ad));
    }

    public List<Ad> getAll() {
        return adRepository.findAll();
    }

    public List<Ad> search(String name) {
        return adRepository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Ad> searchByUser(String name, long id) {
        return adRepository.findAllByNameContainingIgnoreCaseAndUserId(name, id);
    }

    public List<Ad> searchByCategory(String name, Enums.Category category) {
        return adRepository.findAllByNameContainingIgnoreCaseAndCategory(name, category);
    }

    public List<Ad> searchByCategoryAndStatus(String name, Enums.Category category, Enums.Status status) {
        return adRepository.findAllByNameContainingIgnoreCaseAndCategoryAndStatus(name, category, status);
    }

    public List<Ad> searchByStatus(String name, Enums.Status status) {
        return adRepository.findAllByNameContainingIgnoreCaseAndStatus(name, status);
    }

    public Ad findById(long id) {
        return adRepository.findById(id).orElseThrow(() -> {
            throw new AppException("Not found ad with id:" + id, HttpStatus.NOT_FOUND);});
    }

    public Ad update(Ad ad) {
        var updatedAd = adRepository.findById(ad.getId()).orElseThrow(() ->
                new AppException("Ad Id " + ad.getId() + "not found", HttpStatus.NOT_FOUND));
        return adRepository.save(ad);
    }

    public List<Ad> findByUser(long user_id){
        return adRepository.findByUserId(user_id);
    }

    public List<Ad> findByCategory(Enums.Category category){
        return adRepository.findByCategory(category);
    }

    public List<Ad> findByStatus(Enums.Status status){
        return adRepository.findByStatus(status);
    }

    public List<Ad> findByStatusOrderByStartPriceAsc(Enums.Status status){
        return adRepository.findByStatusOrderByStartPriceAsc(status);
    }

    public List<Ad> findByStatusOrderByStartPriceDesc(Enums.Status status){
        return adRepository.findByStatusOrderByStartPriceDesc(status);
    }

    public List<Ad> findByCategoryAndStatus(Enums.Category category, Enums.Status status){
        return adRepository.findByCategoryAndStatus(category, status);
    }

    public List<Ad> findByCategoryAndStatusOrderByStartPriceAsc(Enums.Category category, Enums.Status status){
        return adRepository.findByCategoryAndStatusOrderByStartPriceAsc(category, status);
    }

    public List<Ad> findByCategoryAndStatusOrderByStartPriceDesc(Enums.Category category, Enums.Status status){
        return adRepository.findByCategoryAndStatusOrderByStartPriceDesc(category, status);
    }

    public List<Ad> findByCategoryOrderByStartPriceAsc(Enums.Category category){
        return adRepository.findByCategoryOrderByStartPriceAsc(category);
    }

    public List<Ad> findByCategoryOrderByStartPriceDesc(Enums.Category category){
        return adRepository.findByCategoryOrderByStartPriceDesc(category);
    }

    public void delete(long id) {
        adRepository.deleteById(id);
    }


    public List<Ad> orderByStartPriceDesc(){
        return adRepository.findAllOrderByStartPriceDesc();
    }

    public List<Ad> orderByStartPriceAsc(){
        return adRepository.findAllOrderByStartPriceAsc();
    }

    public List<Ad> findByStartPriceBetweenMinMax(float min, float max){
        return adRepository.findByStartPriceBetween(min, max);
    }

    public List<Ad> findByCategoryAndStartPriceBetweenMinMax(Enums.Category category, float min, float max){
        return adRepository.findByCategoryAndStartPriceBetween(category, min, max);
    }



    public LinkedHashMap<Ad, String> mapAdsAndFiles(List<Ad> ads) {
        LinkedHashMap<Ad, String> dict = new LinkedHashMap<>();
        ads.forEach(ad -> {
            List<FileInfo> fileInfos = fileRepository.findAllByAd(ad);
            dict.put(ad, fileInfos.isEmpty() ? "dummy.jpg" : fileInfos.get(0).getName());
        });
        return dict;
    }




}
