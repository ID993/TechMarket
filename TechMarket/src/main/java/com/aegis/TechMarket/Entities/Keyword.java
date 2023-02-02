package com.aegis.TechMarket.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "keyword")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    @Id
    @SequenceGenerator(name = "keyword_keyword_id_seq", sequenceName = "keyword_keyword_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyword_keyword_id_seq")
    @Column(name = "keyword_id", updatable = false)
    private long id;

    @Column
    private String name;

}
