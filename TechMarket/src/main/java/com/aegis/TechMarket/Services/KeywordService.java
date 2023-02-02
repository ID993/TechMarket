package com.aegis.TechMarket.Services;


import com.aegis.TechMarket.Entities.Keyword;
import com.aegis.TechMarket.Exceptions.AppException;
import com.aegis.TechMarket.Repositories.IKeywordRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class KeywordService {

    private final IKeywordRepository keywordRepository;


    public Keyword create(String name)
    {
        var keyword = new Keyword();
        keyword.setName(name);
        return keywordRepository.save(keyword);
    }

    public List<Keyword> getAll() {
        return keywordRepository.findAll();
    }

    public Keyword findById(long id) {
        return keywordRepository.findById(id).orElseThrow(() ->
                new AppException("Not found keyword with id: " + id, HttpStatus.NOT_FOUND));
    }


    public Keyword findByName(String name) {
        return keywordRepository.findByName(name).orElseThrow(() -> {
            throw new AppException("Not found keyword:" + name, HttpStatus.NOT_FOUND);
        });
    }

    public boolean doesntExist(String name) {
        return !keywordRepository.existsByName(name);
    }

    public void delete(long id) {
        keywordRepository.deleteById(id);
    }

}
