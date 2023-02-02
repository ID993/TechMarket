package com.aegis.TechMarket.DataTransferObjects;


import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdDto {

    private long id;

    private String name;

    private String description;

    private float startPrice;

    private Enums.Status status;

    private Enums.Category category;

    private User user;

}
