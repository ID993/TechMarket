package com.aegis.TechMarket.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileInfo {

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "file_id")
        private UUID id;

        private String name;

        private String url;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "ad_id", nullable = false)
        private Ad ad;


        public FileInfo(String name, String url, Ad ad) {
                this.name = name;
                this.url = url;
                this.ad = ad;
        }

        public FileInfo(String name, String url) {
                this.name = name;
                this.url = url;
        }

}
