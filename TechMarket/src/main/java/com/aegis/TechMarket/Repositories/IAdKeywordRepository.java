package com.aegis.TechMarket.Repositories;


import com.aegis.TechMarket.Entities.AdKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IAdKeywordRepository extends JpaRepository<AdKeyword, Long> {

    List<AdKeyword> findByKeywordId(long id);

    List<AdKeyword> findByAdId(long id);

    void deleteByAdId(long id);


}
