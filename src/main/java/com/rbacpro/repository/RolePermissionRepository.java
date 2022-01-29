package com.rbacpro.repository;


import com.rbacpro.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    @Query(value = "SELECT * FROM Role_Permission WHERE name = ?1 ", nativeQuery = true)
    List<RolePermission> findAllByAppId(String appId);
}
