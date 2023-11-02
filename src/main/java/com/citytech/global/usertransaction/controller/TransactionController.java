package com.citytech.global.usertransaction.controller;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.utils.RestResponse;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.usertransaction.repository.Transactions;
import com.citytech.global.usertransaction.services.usertransactionservices.UserTransactionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;

@SerdeImport(Transactions.class)
@Controller("/paisade")
public class TransactionController {

    private final UserRepository userRepository;
    private final UserTransactionService userTransactionService;


    @Inject
    public TransactionController(UserRepository userRepository,UserTransactionService  userTransactionService){
        this.userRepository = userRepository;
        this.userTransactionService = userTransactionService;
    }

    @Post("/request-for-transaction")
    public HttpResponse<?> createTransaction(@Body Transactions transactions) {
        try{Transactions transaction = userTransactionService.createTransaction(transactions);
            return HttpResponse.created(transaction);
        }catch(Exception e){
            return HttpResponse.badRequest(new RestResponse(400,e.getMessage(),null));
        }

    }

    @Put("/approve-request/{id}")
    public HttpResponse<?> approveTransaction(@Body @PathVariable int id, TransactionStatus status){
        try{

            userTransactionService.approveTransaction(id,status);
            return HttpResponse.ok(new RestResponse(200,"Your request has been approved",null));
        }catch (Exception e){
            return HttpResponse.badRequest(new RestResponse(400,e.getMessage(),null));
        }
    }
}
