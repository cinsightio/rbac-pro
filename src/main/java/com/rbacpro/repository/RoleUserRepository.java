package com.rbacpro.repository;

import com.rbacpro.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository  extends JpaRepository<UserRole, String> {
}
