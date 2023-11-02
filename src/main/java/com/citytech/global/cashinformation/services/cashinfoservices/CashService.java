package com.citytech.global.cashinformation.services.cashinfoservices;
import com.citytech.global.cashinformation.repository.CashInfo;
import java.util.List;

public interface CashService{
    CashInfo createCashInfoFromTransaction(int id);
    CashInfo makePayment(int cashId,int borrowerAmount);
    List<CashInfo> transactionDetails(int uid);
}
