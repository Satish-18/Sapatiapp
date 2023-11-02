package com.citytech.global.usertransaction.repository;
import com.citytech.global.common.enumeration.TransactionStatus;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TransactionRepository extends CrudRepository <Transactions,Integer> {
    Optional<Transactions>  findByBorrowerIdAndStatus(int borrowerId, TransactionStatus status);
    List<Transactions>findByStatus(TransactionStatus status);
}
