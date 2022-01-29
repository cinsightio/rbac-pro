package com.rbacpro.model;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @PrimaryKey
    private UUID id;

    private String title;
    private String description;
    private boolean published;

    @Override
    public String toString() {
        return "User [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
}