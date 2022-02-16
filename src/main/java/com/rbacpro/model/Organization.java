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

    // Alias
    private String name;
    // The creator becomes the only owner and can perform anything in this org.
    private String owner;
    private String description;
    private String creator;

    @Column(name = "create_time")
    private ZonedDateTime createTime;
}