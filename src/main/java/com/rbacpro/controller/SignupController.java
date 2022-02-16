package com.rbacpro.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


// Each org needs a creator that becomes the admin
// This is recorded as an email id.
// Supports login using Google and Github.
@RestController
@Transactional
@RequestMapping(path = "/v1")
public class SignupController {

}
