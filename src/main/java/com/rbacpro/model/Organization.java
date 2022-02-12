package com.rbacpro.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
@ToString
public class Organization {
    @Id
    private String id;

    private String name;
    private String owner;
    private String description;
    private String creator;

    @Column(name = "create_time")
    private ZonedDateTime createTime;
}