package com.rbacpro.controller;

import com.rbacpro.interceptor.AuthenticationInterceptor;
import com.rbacpro.model.AuthorizeRequest;
import com.rbacpro.model.AuthorizeResult;
import com.rbacpro.model.RolePermission;
import com.rbacpro.model.UserRole;
import com.rbacpro.repository.RolePermissionRepository;
import com.rbacpro.repository.UserRoleRepository;
import io.github.jamsesso.jsonlogic.JsonLogic;
import io.github.jamsesso.jsonlogic.JsonLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Transactional
@RequestMapping(path = "v1")
public class AuthorizeController {

    Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

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
                    // Evaluate the condiiton
                    // This is performed by loading the data from request and use the expression from the condition field.
                    //JsonLogic logic = new JsonLogic();
                    boolean result = true;
                    /*
                    try{
                        result = (boolean) logic.apply(f.getCondition(), request.getContext());
                    } catch(JsonLogicException e) {

                        return new ResponseEntity<>(new AuthorizeResult(false), HttpStatus.OK);

                    } catch(Exception e) {
                        return new ResponseEntity<>(new AuthorizeResult(false), HttpStatus.OK);
                    }*/
                    return new ResponseEntity<>(new AuthorizeResult(result), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(new AuthorizeResult(false), HttpStatus.OK);
    }
}
