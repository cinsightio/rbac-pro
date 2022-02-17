package com.rbacpro.repository;

import com.rbacpro.model.RolePermission;
import com.rbacpro.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    // TODO: add organization.
    @Query(value = "SELECT * FROM User_Role WHERE user = ?1 AND org_id = ?2", nativeQuery = true)
    List<UserRole> findAllByUserName(String name, String organization);
}
