package com.example.wehago.common;

import lombok.Data;

@Data
public class BaseCondition {

    private int pageSize = 10;
    private int startIndex = 0;
    private String sortField;    // 정렬 컬럼
    private String sortOrder;    // 정렬 방향

}
