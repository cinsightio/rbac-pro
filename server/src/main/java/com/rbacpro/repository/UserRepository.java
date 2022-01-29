package com.rbacpro.repository;

import java.util.List;
import java.util.UUID;

import com.rbacpro.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface UserRepository extends CassandraRepository<User, UUID> {
    @AllowFiltering
    List<User> findByPublished(boolean published);

    List<User> findByTitleContaining(String title);
}