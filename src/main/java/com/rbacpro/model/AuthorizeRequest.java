package com.rbacpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeRequest {

    // Will need to look up the user's role assignment
    private String user;
    private String action;
    private String resource;
}
