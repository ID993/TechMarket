package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.Alarm;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByUser(User user);

    List<Alarm> findAllByCategory(Enums.Category category);


    List<Alarm> findAllByCategoryAndPrice(Enums.Category category, float price);

}
