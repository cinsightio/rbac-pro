package com.rbacpro.controller;

import com.rbacpro.interceptor.AuthenticationInterceptor;
import com.rbacpro.model.AuthorizeRequest;
import com.rbacpro.model.AuthorizeResult;
import com.rbacpro.model.RolePermission;
import com.rbacpro.model.UserRole;
import com.rbacpro.repository.RolePermissionRepository;
import com.rbacpro.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@Transactional
@RequestMapping(path = "v1")
public class AuthorizeController {

    @Autowired
    UserRoleRepository userRole;

    @Autowired
    RolePermissionRepository rolePer;

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResult> authorize(@RequestBody AuthorizeRequest request,
                                                     HttpServletRequest req, HttpServletResponse res) {
        String org_id = (String)req.getAttribute(AuthenticationInterceptor.ORG);
        // Get all the roles for this user
        List<UserRole> roles = userRole.findAllByUserName(request.getUser(), org_id);
        for(UserRole r: roles) {
            List<RolePermission> per = rolePer.findAllByRoleName(r.getRole(), org_id);
            for(RolePermission f: per) {
                if(f.getAction().equals(request.getAction()) && f.getResource().equals(request.getResource())) {
                    return new ResponseEntity<>(new AuthorizeResult(true), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(new AuthorizeResult(false), HttpStatus.OK);
    }
}
