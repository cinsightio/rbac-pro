package com.rbacpro.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Permission {
    private String action;
    private String resource;
}