package com.rbacpro.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
@ToString
public class Role {
    // primary key
    @Id
    private String name;
    @Column(name = "org_id")
    private String organization;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    private String description;
    @Transient
    private List<Permission> permissions;
}