package com.rbacpro.repository;

import com.rbacpro.model.Role;
import com.rbacpro.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT * FROM Role_Permission WHERE role = ?1 AND org_id = ?2", nativeQuery = true)
    List<Role> findAllByRoleName(String name, String organization);

}