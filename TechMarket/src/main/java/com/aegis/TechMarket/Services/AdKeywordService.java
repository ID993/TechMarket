package com.aegis.TechMarket.Services;

import com.aegis.TechMarket.Entities.AdKeyword;
import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Entities.Keyword;
import com.aegis.TechMarket.Repositories.IAdKeywordRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class AdKeywordService {

    private final IAdKeywordRepository adKeywordRepository;

    private final KeywordService keywordService;

    private final AdService adService;


    public void save(long adId, long keywordId){
        var adKeyword = new AdKeyword(adId, keywordId);
        adKeywordRepository.save(adKeyword);

    }

    public ArrayList<Ad> findByKeyword(String word){
        var keywordId = keywordService.findByName(word).getId();
        var adsKeywords = adKeywordRepository.findByKeywordId(keywordId);
        var ads = new ArrayList<Ad>();
        for (AdKeyword ad : adsKeywords) {
            ads.add(adService.findById(ad.getAdId()));
        }
        return ads;
    }


    public Ad saveMultiple (long adId, String multiKeywords){
        var ad = adService.findById(adId);
        if(Objects.equals(multiKeywords, ""))
            return ad;
        Arrays.stream(multiKeywords.split(",")).map(String::trim).forEach(keyword -> {
            if (keywordService.doesntExist(keyword)) {
                var newKeyword = keywordService.create(keyword);
                save(adId, newKeyword.getId());
            } else {
                save(adId, keywordService.findByName(keyword).getId());
            }
        });
        return ad;
    }



    public List<Ad> getAdsByPrice(long keywordId, float min, float max) {
        var adKeyword = adKeywordRepository.findByKeywordId(keywordId);
        var ads = adKeyword.stream().map(adKey -> adService.findById(adKey.getAdId()));
        var adsInRange = ads.filter(ad ->
            ad.getStartPrice() >= min && ad.getStartPrice() <= max);
        return adsInRange.toList();
    }

    public List<Keyword> getKeywordsByAd(long id) {
        var adKeyword = adKeywordRepository.findByAdId(id);
        var keywords = adKeyword.stream().map(adKey -> keywordService.findById(adKey.getKeywordId()));
        return keywords.toList();
    }


    public List<AdKeyword> findByAdId(long id){
        return adKeywordRepository.findByAdId(id);
    }


    public void delete(long id){
        adKeywordRepository.deleteByAdId(id);
    }
    public List<AdKeyword> getAll(){
        return adKeywordRepository.findAll();
    }

}
