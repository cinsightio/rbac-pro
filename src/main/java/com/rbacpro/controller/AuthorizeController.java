package com.rbacpro.controller;

import com.rbacpro.model.AuthorizeRequest;
import com.rbacpro.model.AuthorizeResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Transactional
@RequestMapping(path = "v1")
public class AuthorizeController {

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResult> authorize(@RequestBody AuthorizeRequest request) {
        return new ResponseEntity<>(new AuthorizeResult(true), HttpStatus.OK);
    }
}
