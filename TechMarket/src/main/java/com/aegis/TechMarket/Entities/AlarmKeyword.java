package com.aegis.TechMarket.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alarm_keyword")
@IdClass(AlarmKeywordId.class)
public class AlarmKeyword implements Serializable {

    @Id
    @Column(name = "alarm_id")
    private long alarmId;

    @Id
    @Column(name = "keyword_id")
    private long keywordId;

}