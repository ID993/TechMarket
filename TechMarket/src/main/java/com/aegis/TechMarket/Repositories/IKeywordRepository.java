package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IKeywordRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByName(String name);

    boolean existsByName(String name);

}
