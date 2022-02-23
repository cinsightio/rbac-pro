package com.rbacpro.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String role;
    @Id
    @NotBlank
    private String action;
    @Id
    @NotBlank
    private String resource;
    @Column(name = "org_id")
    @Id
    private String organization;
}