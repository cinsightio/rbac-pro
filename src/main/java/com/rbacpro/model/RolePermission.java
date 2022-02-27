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
@Table(name = "role_permission", uniqueConstraints={
        @UniqueConstraint(columnNames = {"role", "operation", "resource", "org_id"})
})
@ToString
@IdClass(RolePermission.class)
public class RolePermission implements Serializable {

    @NotBlank
    @Id
    private String role;
    @NotBlank
    @Column(name= "operation")
    private String action;
    @NotBlank
    private String resource;
    @Column(name = "org_id")
    private String organization;
}