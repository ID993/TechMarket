package com.aegis.TechMarket.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmKeywordId implements Serializable {

    private long alarmId;

    private long keywordId;

}
