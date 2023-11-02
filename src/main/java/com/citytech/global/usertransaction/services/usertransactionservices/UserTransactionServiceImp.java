package com.citytech.global.usertransaction.services.usertransactionservices;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.exceptionhandler.AccessDeniedException;
import com.citytech.global.usertransaction.repository.TransactionRepository;
import com.citytech.global.usertransaction.repository.Transactions;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Optional;

@Singleton
public class UserTransactionServiceImp implements UserTransactionService {
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    @Inject
    public UserTransactionServiceImp( TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }
//
//    @Override
//    public Transactions createTransaction(Transactions transactions) {
//        transactions.setStatus(TransactionStatus.PENDING);
//        transactions.setTimestamp(LocalDateTime.now());
//        Optional<Users> transaction = userRepository.findById(transactions.getLenderId());
//        Optional<Transactions> existingPendingRequest = transactionRepository. findByBorrowerIdAndStatus(transactions.getBorrowerId(),TransactionStatus.PENDING);
//        if(transactions.getBorrowerId() == transactions.getLenderId()){
//            throw new AccessDeniedException("The lender and borrower can't be same");
//        }
//        if(transaction == null || !"Lender".equalsIgnoreCase((transaction.get().getUserType()))){
//            throw new AccessDeniedException("Only borrowers are allowed to make request to lenders");
//        }
//        if(transactions.getAmount() < 1000){
//            throw new AccessDeniedException("Request amount should be more then 1000");
//        }
//        if (existingPendingRequest.isPresent()) {
//            throw new AccessDeniedException("Borrower already has a pending request");
//        }
//        if(transactions.getTimeInDays() > 25){
//            throw new AccessDeniedException("The interest days should not exceed then 25");
//        }
//        if(transactions.getInterestRate() < 2){
//            throw new AccessDeniedException("The interest rate should be more then 2 percent");
//        }
//        if(transactions.getInterestRate() >13){
//            throw new AccessDeniedException("The interest rate should not exceeds 13 percent");
//        }
//        return transactionRepository.save(transactions);
//        }
//
//        @Override
//    public void approveTransaction(int id,TransactionStatus status){
//        Transactions transactions = transactionRepository.findById(id).orElseThrow(() ->
//                new AccessDeniedException("Transaction not found"));
//            if (transactions.getStatus() == TransactionStatus.PENDING) {
//                transactions.setStatus(status);
//                transactionRepository.update(transactions);
//
//            } else {
//                throw new AccessDeniedException("Transaction is not in a pending state and cannot be approved");
//
//            }
//        }


    @Override
    public Transactions createTransaction(Transactions transactions) {
        transactions.setStatus(TransactionStatus.PENDING);
        transactions.setTimestamp(LocalDateTime.now());
        Optional<Users> transaction = userRepository.findById(transactions.getLenderId());
        Optional<Transactions> existingPendingRequest = transactionRepository. findByBorrowerIdAndStatus(transactions.getBorrowerId(),TransactionStatus.PENDING);
        if(transactions.getBorrowerId() == transactions.getLenderId()){
            throw new AccessDeniedException("The lender and borrower can't be same");
        }
        if(transaction == null || !"Lender".equalsIgnoreCase((transaction.get().getUserType()))){
            throw new AccessDeniedException("Only borrowers are allowed to make request to lenders");
        }
        if(transactions.getAmount() < 1000){
            throw new AccessDeniedException("Request amount should be more then 1000");
        }
        if (existingPendingRequest.isPresent()) {
            throw new AccessDeniedException("Borrower already has a pending request");
        }
        if(transactions.getTimeInDays() < 3){
            throw new AccessDeniedException("The days should be more than 2 data");
        }
        if(transactions.getTimeInDays() > 25){
            throw new AccessDeniedException("The interest days should not exceed then 25");
        }
        if(transactions.getInterestRate() < 2){
            throw new AccessDeniedException("The interest rate should be more then 2 percent");
        }
        if(transactions.getInterestRate() >13){
            throw new AccessDeniedException("The interest rate should not exceeds 13 percent");
        }
        return transactionRepository.save(transactions);
    }

    @Override
    public void approveTransaction(int id,TransactionStatus status){
        Transactions transactions = transactionRepository.findById(id).orElseThrow(() ->
                new AccessDeniedException("Transaction not found"));
        if (transactions.getStatus() == TransactionStatus.PENDING) {
            transactions.setStatus(status);
            transactionRepository.update(transactions);

        } else {
            throw new AccessDeniedException("Transaction is not in a pending state and cannot be approved");

        }
    }
    }


