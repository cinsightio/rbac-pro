package com.rbacpro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_permission")
@ToString
@IdClass(RolePermission.class)
public class RolePermission implements Serializable {
    @Id
    private String role;
    @Id
    private String action;
    @Id
    private String resource;
    @Column(name = "org_id")
    @Id
    private String organization;
}