package com.rbacpro.controller;


import com.rbacpro.model.*;
import com.rbacpro.repository.OrganizationRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping(path = "/v1")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private OrganizationRepository orgRepo;
    @Autowired
    private RolePermissionRepository rolePermissionRepo;

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody Role request) {
        logger.info("Entering create new role.");
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
        logger.info("Entering creating new permission.");
        try {
            logger.info(permission.toString());
            if(!orgRepo.findById(permission.getOrganization()).isPresent()) {
                return new ResponseEntity<>("Organization not found.", HttpStatus.NOT_FOUND);
            }
            if(!roleRepo.findById(permission.getRole()).isPresent()) {
                return new ResponseEntity<>("Role not found.", HttpStatus.NOT_FOUND);
            }

            rolePermissionRepo.save(permission);
        } catch (Exception e) {
           logger.error(e.toString());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/role/permission")
    public ResponseEntity deletePermission(@RequestBody RolePermission permission) {
        logger.info("Entering deleting permission.");
        try {
            logger.info(permission.toString());
            rolePermissionRepo.delete(permission);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/organization/{org_id}/roles/{name}")
    public ResponseEntity<Role> getRole(@PathVariable String name, @PathVariable String org_id) {
        logger.info("Entering getting role.");
        try {
            Optional<Role> res = roleRepo.findById(name);
            if (res.isPresent()) {
                // Get the permissions
                List<RolePermission> permissions = rolePermissionRepo.findAllByRoleName(name);
                List<Permission> p = permissions.stream().map(t-> new Permission(t.getAction(), t.getResource())).collect(Collectors.toList());
                return new ResponseEntity<>(new Role(res.get().getName(), res.get().getOrganization(), res.get().getCreateTime(), res.get().getDescription(), p), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/organization/{org_id}/roles")
    public  ResponseEntity<Set<Role>> listRoles(@PathVariable String org_id) {
        return null;
    }

    @DeleteMapping(path = "/organization/{org_id}/role/{name}")
    public ResponseEntity<Role>  deleteRole(@PathVariable String name, @PathVariable String org_id) {
        logger.info("Entering getting role.");
        try {
            Optional<Role> res = roleRepo.findById(name);
            if (res.isPresent()) {
                return new ResponseEntity<>(res.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}