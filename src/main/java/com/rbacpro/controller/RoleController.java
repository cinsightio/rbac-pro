package com.rbacpro.controller;


import com.rbacpro.model.*;
import com.rbacpro.repository.RolePermissionRepository;
import com.rbacpro.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping(path = "/v1")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RolePermissionRepository rolePermissionRepo;

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody Role request) {
        logger.info("In create role.");
        Role n = new Role();
        n.setOrganization(request.getOrganization());
        n.setName(request.getName());
        n.setDescription(request.getDescription());
        n.setCreateTime(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
        n = roleRepo.save(n);
        return new ResponseEntity<>(n, HttpStatus.OK);
    }

    @PostMapping("/role/permission")
    public ResponseEntity putPermission(@RequestBody RolePermission permission) {
        logger.info("Putting permissions");
        try {
            logger.info(permission.toString());
            rolePermissionRepo.save(permission);
        } catch (Exception e) {
           logger.error(e.toString());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/role/permission")
    public ResponseEntity deletePermission(@RequestBody RolePermission permission) {
        logger.info("Entering deleting permissions");
        try {
            rolePermissionRepo.delete(permission);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/role/{name}")
    public @ResponseBody
    ResponseEntity<Role>  getRole(@PathVariable String roleName) {
        try {
            Optional<Role> res = roleRepo.findById(roleName);
            if (res.isPresent()) {
                // Get the permissions
                //List<RolePermission> permissions = rolePermissionRepo.findAllByAppId(roleName);
                //Role ret = res.get();
                return new ResponseEntity<>(res.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}