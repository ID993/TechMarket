package com.aegis.TechMarket.Services;

import com.aegis.TechMarket.Entities.*;
import com.aegis.TechMarket.Repositories.IAlarmKeywordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class AlarmKeywordService {

    private final IAlarmKeywordRepository alarmKeywordRepository;

    private final KeywordService keywordService;

    private final AlarmService alarmService;


    public void save(long alarmId, long keywordId){
        var alarmKeyword = new AlarmKeyword(alarmId, keywordId);
        alarmKeywordRepository.save(alarmKeyword);
    }

    public void saveAlarmMultipleKeywords (long alarmId, String multiKeywords) {

        if(Objects.equals(multiKeywords, ""))
            return;
        for (String s : multiKeywords.split(",")) {
            var keyword = s.trim();
            if (keywordService.doesntExist(keyword)) {
                var newKeyword = keywordService.create(keyword);
                save(alarmId, newKeyword.getId());
            } else {
                save(alarmId, keywordService.findByName(keyword).getId());
            }
        }
    }


    public List<AlarmKeyword> findByAlarmId(long id){
        return alarmKeywordRepository.findByAlarmId(id);
    }

    public List<Keyword> getKeywordsByAlarm(long id) {
        var alarmKeyword = alarmKeywordRepository.findByAlarmId(id);
        var keywords = alarmKeyword.stream().map(alarmKey -> keywordService.findById(alarmKey.getKeywordId()));
        return keywords.toList();
    }

    public void delete(long id){
        alarmKeywordRepository.deleteByAlarmId(id);
    }



}
