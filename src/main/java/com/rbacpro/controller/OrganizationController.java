package com.rbacpro.controller;


import com.rbacpro.model.Organization;
import com.rbacpro.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping(path = "v1")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);


    @Autowired
    private OrganizationRepository orgRepo;

    @PostMapping("/organization")
    public Organization create(@RequestBody Organization request) {
        Organization n = new Organization();
        n.setName(request.getName());
        n.setCreator(request.getCreator());
        n.setDescription(request.getDescription());
        n.setOwner(request.getOwner());
        n.setCreateTime(ZonedDateTime.now());
        n.setId(request.getId());
        orgRepo.save(n);
        return orgRepo.save(n);
    }

    @GetMapping("/organization/{organizationId}")
    public Organization list(@PathVariable String organizationId) {
        Optional<Organization> r = orgRepo.findById(organizationId);
        if (r.isPresent()) {
            return r.get();
        } else {
            return null;
        }
    }
}