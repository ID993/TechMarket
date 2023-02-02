package com.aegis.TechMarket.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String from;

    private String to;

    private String subject;

    private String message;
}
