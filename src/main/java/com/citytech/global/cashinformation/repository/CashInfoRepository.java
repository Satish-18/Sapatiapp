package com.citytech.global.cashinformation.repository;
import com.citytech.global.common.enumeration.TransactionStatus;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CashInfoRepository extends CrudRepository<CashInfo, Integer> {
    Optional<CashInfo>findByBorrowerIdAndPaymentStatus(int borrowerId, TransactionStatus paymentStatus);
    List<CashInfo> findByLenderId(int lenderId);
    List<CashInfo> findByBorrowerId(int borrowerId);


}
