package com.aegis.TechMarket.Exceptions;

import lombok.*;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private int status;
    private Date timestamp;
    private String message;
    private String description;

}