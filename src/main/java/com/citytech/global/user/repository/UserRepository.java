package com.citytech.global.user.repository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends CrudRepository <Users,Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUserType(String userType);
    Optional<Users> findByUid(int uid);
}

