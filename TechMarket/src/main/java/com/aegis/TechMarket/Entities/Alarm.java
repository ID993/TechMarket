package com.aegis.TechMarket.Entities;


import com.aegis.TechMarket.Enumerators.Enums;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name = "alarm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {

    @Id
    @SequenceGenerator(name = "alarm_alarm_id_seq", sequenceName = "alarm_alarm_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarm_alarm_id_seq")
    @Column(name = "alarm_id", updatable = false)
    private long id;

    @Column(name = "price_min")
    private float minPrice;


    @Column(name = "price_max")
    private float maxPrice;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "category_id")
    @Enumerated(EnumType.ORDINAL)
    private Enums.Category category;

    @Column(name = "name")
    private String name;
}
