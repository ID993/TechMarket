package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.Ad;
import com.aegis.TechMarket.Enumerators.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByUserId(long user_id);

    List<Ad> findByCategory(Enums.Category category);

    List<Ad> findByCategoryOrderByStartPriceAsc(Enums.Category category);

    List<Ad> findByCategoryOrderByStartPriceDesc(Enums.Category category);


    List<Ad> findAllByNameContainingIgnoreCase(String search);

    List<Ad> findAllByNameContainingIgnoreCaseAndStatus(String search, Enums.Status status);

    List<Ad> findAllByNameContainingIgnoreCaseAndUserId(String search, long id);

    List<Ad> findAllByNameContainingIgnoreCaseAndCategory(String search, Enums.Category category);

    List<Ad> findAllByNameContainingIgnoreCaseAndCategoryAndStatus(String search, Enums.Category category, Enums.Status status);

    @Query("select ad from Ad ad order by ad.startPrice desc")
    List<Ad> findAllOrderByStartPriceDesc();

    @Query("select ad from Ad ad order by ad.startPrice asc")
    List<Ad> findAllOrderByStartPriceAsc();


    List<Ad> findByStartPriceBetween(float min, float max);

    List<Ad> findByCategoryAndStartPriceBetween(Enums.Category category, float min, float max);


    List<Ad> findByStatus(Enums.Status status);

    List<Ad> findByStatusOrderByStartPriceAsc(Enums.Status status);

    List<Ad> findByStatusOrderByStartPriceDesc(Enums.Status status);

    List<Ad> findByCategoryAndStatus(Enums.Category category, Enums.Status status);

    List<Ad> findByCategoryAndStatusOrderByStartPriceAsc(Enums.Category category, Enums.Status status);

    List<Ad> findByCategoryAndStatusOrderByStartPriceDesc(Enums.Category category, Enums.Status status);

}
