package com.aegis.TechMarket.Entities;

import com.aegis.TechMarket.Enumerators.Enums;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name = "ad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    @Id
    @SequenceGenerator(name = "ad_ad_id_seq", sequenceName = "ad_ad_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_ad_id_seq")
    @Column(name = "ad_id", updatable = false)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "start_price")
    private float startPrice;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Enums.Status status;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Enums.Category category;

    @Column(name = "sold_price")
    private float soldPrice;

    @Column(name = "sold_to")
    private long soldTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
