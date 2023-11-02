package com.citytech.global.register.services.loginservices;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.exceptionhandler.AccessDeniedException;
import com.citytech.global.common.sendmail.EmailService;
import com.citytech.global.register.dto.login.LoginRequest;
import com.citytech.global.register.dto.login.LoginResponse;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import com.citytech.global.user.services.showuserservices.ShowUserService;
import com.citytech.global.usertransaction.repository.TransactionRepository;
import com.citytech.global.usertransaction.repository.Transactions;
import com.citytech.global.usertransaction.services.usertransactionservices.UserTransactionService;
import jakarta.inject.Singleton;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthenticateServiceImp implements AuthenticateService {
    private UserRepository userRepository;
    private final ShowUserService showUserService;
    private final UserTransactionService userTransactionService;
    private final TransactionRepository transactionRepository;

    public AuthenticateServiceImp(UserRepository userRepository, ShowUserService showUserService, UserTransactionService userTransactionService, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.showUserService = showUserService;
        this.userTransactionService = userTransactionService;
        this.transactionRepository = transactionRepository;
    }
    @Override
    public Optional<LoginResponse> login(LoginRequest loginRequest){
        Optional<Users> users = userRepository.findByEmail(loginRequest.getEmail());
        if(TransactionStatus.UNVERIFIED.equals(users.get().getUserStatus())){
            throw new AccessDeniedException("Please wait for admin to  verify your status");
        }

        if(users.isPresent() && users.get().getPassword().equals(loginRequest.getPassword())){
                String dummyToken = "dummy-access-token";
            List<Transactions> pendingBorrowers = transactionRepository.findByStatus(TransactionStatus.PENDING);
            checkDueDate(pendingBorrowers);
                return Optional.of(new LoginResponse(dummyToken));
        }else {
            return Optional.empty();
        }
    }

    public void checkDueDate(List<Transactions> pendingBorrowers) {
        if (!pendingBorrowers.isEmpty()) {
            for (Transactions borrower: pendingBorrowers) {
                LocalDateTime dueDate = borrower.getTimestamp().plus(borrower.getTimeInDays(), ChronoUnit.DAYS);
                Optional<Users> borrowerEntity = showUserService.getUserById(borrower.getBorrowerId());
                if (Duration.between(dueDate, LocalDateTime.now()).toDays() == 5
                        || Duration.between(dueDate, LocalDateTime.now()).toDays() == 1) {
                    EmailService.sendEmail(borrowerEntity.get().getEmail(),
                            "give me the money",
                            "Test email.");
                }
            }
        }
    }
}


