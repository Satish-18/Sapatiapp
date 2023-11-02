package com.citytech.global.cashinformation.controller;
import com.citytech.global.cashinformation.repository.CashInfo;
import com.citytech.global.cashinformation.services.cashinfoservices.CashService;
import com.citytech.global.common.utils.RestResponse;
import com.citytech.global.usertransaction.repository.Transactions;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import java.util.List;


@Controller("/cash-info")
public class CashController {
    private CashService cashService;

    @Inject
    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @Post("/process-lender-transaction/{id}")
    public HttpResponse<?> createCashInfoFromTransaction(@PathVariable int id) {
        try {
            cashService.createCashInfoFromTransaction(id);
            return HttpResponse.ok( new RestResponse(200," you have lend  the request amount to borrower",null));
        } catch (Exception e) {
            return HttpResponse.badRequest(new RestResponse(400,e.getMessage(),null));
        }

    }

    @Post("/payment/{cashId}")
    public HttpResponse<?> makePayment(int cashId, int borrowerAmount) {
        try {
            cashService.makePayment(cashId, borrowerAmount);
            return HttpResponse.ok(new RestResponse(200,"Your payment is completed thanks for using paisade",null));
        } catch (Exception e) {
            return HttpResponse.badRequest(new RestResponse(400,e.getMessage(),null));
        }
    }
    @Get(value = "/transaction-details/{uid}", produces = MediaType.APPLICATION_JSON)
    public List<CashInfo> transactionDetails(int uid) {
        return cashService.transactionDetails(uid);
    }

}
