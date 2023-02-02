package com.aegis.TechMarket.Services;



import java.io.IOException;
import java.util.*;

import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Entities.FileInfo;
import com.aegis.TechMarket.Repositories.IFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.commons.lang3.RandomStringUtils;


@Service
@AllArgsConstructor
public class FileService {

    private FileStorageService fileStorageService;

    private IFileRepository fileRepository;

    private AdService adService;


    public void store(MultipartFile file) {
        String generatedString = RandomStringUtils.random(8, true, true) + "-";
        String fileName = generatedString + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                "/src/main/resources/static/images/";
        fileStorageService.save(file, generatedString);
    }

    public void storeMultiple(long id, MultipartFile[] files) throws IOException {
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                "/src/main/resources/static/images/";
        String generatedString = RandomStringUtils.random(8, true, true) + "-";
        var ad = adService.findById(id);
        for (MultipartFile file : files) {
            if(!file.isEmpty()) {
                String fileName = generatedString + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                fileStorageService.save(file, generatedString);
                fileRepository.save(new FileInfo(fileName, baseUrl + fileName, ad));
            }
        }
    }

    public Optional<FileInfo> get(UUID id) {
        return fileRepository.findById(id);
    }

    public List<FileInfo> getAll() {
        return fileRepository.findAll();
    }

    public List<FileInfo> getByAd(Ad ad){
        return fileRepository.findAllByAd(ad);
    }

    public FileInfo getByAdAndName(Ad ad, String name){
        return fileRepository.findByAdAndName(ad, name);
    }

    public void delete(FileInfo file){
        fileRepository.delete(file);
    }

    public FileInfo findFirstByAd (Ad ad){
        return fileRepository.findFirstByAd(ad);
    }
}
