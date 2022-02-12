package com.rbacpro.controller;


import com.rbacpro.model.AuthorizeRequest;
import com.rbacpro.model.Permission;
import com.rbacpro.model.Role;
import com.rbacpro.model.RolePermission;
import com.rbacpro.repository.OrganizationRepository;
import com.rbacpro.repository.RolePermissionRepository;
import com.rbacpro.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1")
public class CheckAccessController {

    Logger logger = LoggerFactory.getLogger(CheckAccessController.class);

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private OrganizationRepository orgRepo;
    @Autowired
    private RolePermissionRepository rolePermissionRepo;

    @GetMapping(path = "/authorize")
    public ResponseEntity<Role> checkAccess(@PathVariable AuthorizeRequest name) {
        logger.info("Entering getting role.");
        try {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
