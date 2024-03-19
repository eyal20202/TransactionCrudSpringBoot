package com.eyalmiz.transaction.Model;

import com.eyalmiz.transaction.Enum.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Integer id;
    private TransactionTypeEnum type;
    private Double amount;
    private String description;
    private Date date;

}
