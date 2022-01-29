package com.rbacpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeRequest {

    private String userId;
    private String resource;
    Map<String, ? extends Object> conditions;
}
