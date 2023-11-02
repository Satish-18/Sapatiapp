package com.citytech.global.cashinformation.services.cashinfoservices;
import com.citytech.global.cashinformation.repository.CashInfo;
import com.citytech.global.cashinformation.repository.CashInfoRepository;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.exceptionhandler.AccessDeniedException;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import com.citytech.global.usertransaction.repository.TransactionRepository;
import com.citytech.global.usertransaction.repository.Transactions;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Singleton
public class CashSeriviceImp implements CashService{
    private final CashInfoRepository cashInfoRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    @Inject
    public CashSeriviceImp(CashInfoRepository cashInfoRepository, TransactionRepository transactionRepository, UserRepository userRepository){
        this.cashInfoRepository = cashInfoRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }
@Override
@Transactional
public CashInfo createCashInfoFromTransaction(int id) {
    Transactions transactions = transactionRepository.findById(id)
            .orElseThrow(() ->
                    new IllegalArgumentException("Transaction not found"));
    Users users = userRepository.findById(transactions.getLenderId())
            .orElseThrow(() ->
                    new IllegalArgumentException("Transaction not found"));

    int interest = calculateInterestRate(transactions.getAmount(),transactions.getTimeInDays(),transactions.getInterestRate());
    CashInfo cashInfo = new CashInfo();
    Optional<CashInfo> paymentDetails = cashInfoRepository.findByBorrowerIdAndPaymentStatus(transactions.getBorrowerId(), TransactionStatus.UNPAID);
    if(paymentDetails.isPresent()){
        throw new IllegalArgumentException("You have already lend the money to borrower");
    }
    if (transactions.getStatus() == TransactionStatus.UNPAID) {
        throw new IllegalArgumentException("You have already lend the money");
    }
    if(transactions.getStatus() == TransactionStatus.PENDING){
        throw new IllegalArgumentException("Cannot send money to   this user as the status  is still in pending");
    }  else{
        cashInfo.setBorrowerId(transactions.getBorrowerId());
        cashInfo.setLenderId(transactions.getLenderId());
        cashInfo.setRequestedAmount(transactions.getAmount());
        cashInfo.setPreviousPurse(users.getLenderAmount());
        int borrowerTotalAmount = transactions.getAmount() + cashInfo.getBorrowerAmount() + interest;
        int lenderRemaningAmount = users.getLenderAmount() - transactions.getAmount();
        cashInfo.setBorrowerAmount(borrowerTotalAmount);
        cashInfo.setLenderAmount(lenderRemaningAmount);
        cashInfo.setPaymentStatus(TransactionStatus.UNPAID);
        return cashInfoRepository.save(cashInfo);
    }
}
    @Override
    public CashInfo makePayment(int CashId,int borrowerAmount){
        CashInfo cashInfo = cashInfoRepository.findById(CashId).
                orElseThrow(() ->  new AccessDeniedException("There is no cash information related with this id"));
        if(cashInfo.getBorrowerAmount() == 0){
            throw new IllegalArgumentException("You  have Zero balance");
        }
        if(borrowerAmount > cashInfo.getBorrowerAmount()){
            throw new IllegalArgumentException("You don't have enough Amount to paid");
        };

        if(borrowerAmount != cashInfo.getBorrowerAmount()){
            throw new IllegalArgumentException("Please make full payment");
        }
        int borrowerAmountAfterPaidToBorrower = cashInfo.getBorrowerAmount() - borrowerAmount;
        int lenderAmountAfterPaidByBorrower = cashInfo.getLenderAmount() + borrowerAmount;
        cashInfo.setBorrowerAmount(borrowerAmountAfterPaidToBorrower);
        cashInfo.setLenderAmount(lenderAmountAfterPaidByBorrower);
        cashInfo.setPaymentStatus(TransactionStatus.PAID);
        return cashInfoRepository.update(cashInfo);

    }
    @Override
   public List<CashInfo> transactionDetails(int uid){
        Optional<Users> users = userRepository.findById(uid);
        List<CashInfo> cashInfoList = new ArrayList<>();
        if(users.isPresent()){
            if("BORROWER".equalsIgnoreCase(users.get().getUserType())){
                cashInfoList = cashInfoRepository.findByBorrowerId(uid);
                if(!cashInfoList.isEmpty()){
                    return cashInfoList;
                } else throw new IllegalArgumentException("Borrower not found");

            } else if ("LENDER".equalsIgnoreCase(users.get().getUserType())) {
                cashInfoList = cashInfoRepository.findByLenderId(uid);
                if(!cashInfoList.isEmpty()){
                    return cashInfoList;
                }else throw new IllegalArgumentException("Lender not found");
            }
        }else throw new IllegalArgumentException("Users not found");
        return cashInfoList;
   }

    //Function to calculate simple interest
    private int  calculateInterestRate(int principal,int time,int rate) {
        double timeInYears = (double) time / 365;
        double interestRate = principal * timeInYears * rate / 100;
        return (int)Math.round(interestRate);

    }
}
