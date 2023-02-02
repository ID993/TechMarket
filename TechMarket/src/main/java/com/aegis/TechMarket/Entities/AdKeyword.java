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
@Table(name = "ad_keywords")
@IdClass(AdKeywordId.class)
public class AdKeyword implements Serializable {

    @Id
    @Column(name = "ad_id")
    private long adId;

    @Id
    @Column(name = "keyword_id")
    private long keywordId;

}
