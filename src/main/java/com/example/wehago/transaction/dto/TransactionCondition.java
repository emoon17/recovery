package com.example.wehago.transaction.dto;

import com.example.wehago.common.BaseCondition;
import lombok.Data;

@Data
public class TransactionCondition extends BaseCondition {
    private String clientId;
}
