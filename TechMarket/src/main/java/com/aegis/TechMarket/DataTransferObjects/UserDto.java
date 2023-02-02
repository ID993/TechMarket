package com.aegis.TechMarket.DataTransferObjects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String fullName;

    private String email;

    private String password;

    private String matchingPassword;

    private String address;

}
