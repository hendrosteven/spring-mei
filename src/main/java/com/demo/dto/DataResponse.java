package com.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DataResponse {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Object payload;
}
