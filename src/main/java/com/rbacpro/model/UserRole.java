package com.rbacpro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
@ToString
@IdClass(UserRole.class)
public class UserRole implements Serializable {
    @Id
    private String role;
    @Id
    private String user;
    @Column(name = "org_id")
    @Id
    private String organization;
    @Column(name = "create_time")
    private ZonedDateTime createTime;
    @Column(name = "create_by")
    private String createdBy;
}