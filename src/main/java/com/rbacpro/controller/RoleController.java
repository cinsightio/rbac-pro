package com.rbacpro.controller;


import com.rbacpro.interceptor.AuthenticationInterceptor;
import com.rbacpro.model.*;
import com.rbacpro.repository.OrganizationRepository;
import com.rbacpro.repository.RolePermissionRepository;
import com.rbacpro.repository.RoleRepository;
import com.rbacpro.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private UserRoleRepository roleUserRepo;

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody Role request, HttpServletRequest req, HttpServletResponse res) {
        String org_id = (String)req.getAttribute(AuthenticationInterceptor.ORG);
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
    public ResponseEntity putPermission(@RequestBody RolePermission permission, HttpServletRequest request, HttpServletResponse res) {
        logger.info("Entering creating new permission.");
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        try {
            logger.info(permission.toString());
            if(!orgRepo.findById(org_id).isPresent()) {
                return new ResponseEntity<>("Organization not found.", HttpStatus.NOT_FOUND);
            }
            if(!roleRepo.findById(permission.getRole()).isPresent()) {
                return new ResponseEntity<>("Role not found.", HttpStatus.NOT_FOUND);
            }
            // Always set the organization correctly
            permission.setOrganization(org_id);
            rolePermissionRepo.save(permission);
        } catch (Exception e) {
           logger.error(e.toString());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/role")
    public ResponseEntity associateUserRole(@RequestBody UserRole assignment, HttpServletRequest request, HttpServletResponse res) {
        logger.info("Entering creating new permission.");
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        try {
            logger.info(assignment.toString());
            assignment.setCreatedBy("system@rbacpro.com");
            assignment.setCreateTime(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
            assignment.setOrganization(org_id);
            roleUserRepo.save(assignment);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/role/permission")
    public ResponseEntity deletePermission(@RequestBody RolePermission permission, HttpServletRequest request, HttpServletResponse res) {
        logger.info("Entering deleting permission.");
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        try {
            logger.info(permission.toString());
            permission.setOrganization(org_id);
            rolePermissionRepo.delete(permission);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/roles/{name}")
    public ResponseEntity<Role> getRole(@PathVariable String name, HttpServletRequest request) {
        logger.info("Entering getting role.");
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        try {
            Optional<Role> res = roleRepo.findById(name);
            if (res.isPresent()) {
                // Get the permissions
                List<RolePermission> permissions = rolePermissionRepo.findAllByRoleName(name, org_id);
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

    @GetMapping(path = "/roles")
    public  ResponseEntity<Set<Role>> listRoles(HttpServletRequest request, HttpServletResponse res) {
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        return null;
    }

    @DeleteMapping(path = "/role/{name}")
    public ResponseEntity<Role>  deleteRole(@PathVariable String name, HttpServletRequest request) {
        logger.info("Entering getting role.");
        String org_id = (String)request.getAttribute(AuthenticationInterceptor.ORG);
        try {
            List<Role> res = roleRepo.findAllByRoleName(name, org_id);
            if (res.size() == 1) {
                return new ResponseEntity<>(res.get(0), HttpStatus.OK);
            } else if(res.size() > 1) {
                logger.error("Getting duplicate roles in one organization.");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                logger.debug("No roles found");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}