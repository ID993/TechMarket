package com.aegis.TechMarket.Repositories;

import com.aegis.TechMarket.Entities.AlarmKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlarmKeywordRepository extends JpaRepository<AlarmKeyword, Long> {

    List<AlarmKeyword> findByKeywordId(long id);

    List<AlarmKeyword> findByAlarmId(long id);

    void deleteByAlarmId(long id);
}
