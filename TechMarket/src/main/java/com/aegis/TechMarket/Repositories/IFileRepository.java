package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface IFileRepository extends JpaRepository<FileInfo, UUID> {

    List<FileInfo> findAllByAd(Ad ad);

    FileInfo findByAdAndName(Ad ad, String name);

    FileInfo findFirstByAd(Ad ad);


}
