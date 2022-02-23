package com.rbacpro.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Permission {
    @NotBlank
    @Size(min = 0, max = 30)
    private String action;
    @NotBlank
    @Size(min = 0, max = 200)
    private String resource;
}