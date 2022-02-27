package com.rbacpro.repository;

import com.rbacpro.model.Role;
import com.rbacpro.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT * FROM Role WHERE name = ?1 AND org_id = ?1", nativeQuery = true)
    List<Role> findAllByRoleNameInOrg(String roleName, String organization);

    @Query(value = "SELECT * FROM Role WHERE org_id = ?1", nativeQuery = true)
    List<Role> findAllRolesInOrg(String organization);

}