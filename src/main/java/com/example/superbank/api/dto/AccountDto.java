package com.example.superbank.api.dto;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private String first_name;
    private String last_name;
    private BigDecimal balance;
}
