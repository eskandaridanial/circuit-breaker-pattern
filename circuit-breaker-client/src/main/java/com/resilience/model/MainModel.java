package com.resilience.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainModel<T> {
    private Integer code;
    private String message;
    private T result;
}
