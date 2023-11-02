package com.citytech.global.user.controller;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.utils.RestResponse;
import com.citytech.global.user.repository.Users;
import com.citytech.global.user.repository.dto.UserVerify;
import com.citytech.global.user.services.createuserservces.CreateUserService;
import com.citytech.global.user.services.deleteuserservices.DeleteUserService;
import com.citytech.global.user.services.showborrowerservices.ShowBorrowerService;
import com.citytech.global.user.services.showlenderservices.ShowLenderService;
import com.citytech.global.user.services.showuserservices.ShowUserService;
import com.citytech.global.user.services.updateuserservices.UpdateUserService;
import com.citytech.global.user.services.userverificationservice.UserVerificationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

import static io.micronaut.http.HttpResponse.created;

@Controller("/paisade")
@SerdeImport(Users.class)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersController {

    private final CreateUserService createUser;
    private final ShowUserService showUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;
    private final UserVerificationService userVerificationService;
    private final ShowBorrowerService showBorrowerService;
    private final ShowLenderService showLenderService;

    @Inject
    public UsersController(
            CreateUserService createUser,
            ShowUserService showUserService,
            UpdateUserService updateUserService,
            DeleteUserService deleteUserService,
            UserVerificationService userVerificationService, ShowBorrowerService showBorrowerService,
            ShowLenderService showLenderService) {

        this.createUser = createUser;
        this.showUserService = showUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
        this.userVerificationService = userVerificationService;
        this.showBorrowerService = showBorrowerService;
        this.showLenderService = showLenderService;

    }

    @Post("/users-registration")
    public HttpResponse<?> createUsers(@Body Users users) {
        try{
           created(createUser.createUsers(users));
           return HttpResponse.ok(new RestResponse(0,"User is created sucessfully please wait till you are verified to login",null));
        }
        catch (Exception e){
            return HttpResponse.badRequest(new RestResponse(0,e.getMessage(),null));
        }

    }


    @Get("/total-users")

    public List<Users> getAllUsers() {
        return showUserService.getAllUsers();
    }

    @Get("/{uid}")
    public HttpResponse<?> getUser(int uid) {
        Users users = showUserService.getUserById(uid).orElse(null);

        if (users != null) {
            return HttpResponse.ok("users");
        } else {
            return HttpResponse.notFound().body("User Not Found");
        }
    }
    @Post("/update-users-details/{uid}")
    public HttpResponse<?> updateUsers(int uid, Users users) {
        Optional<Users> updatedUser = updateUserService.updateUsers(uid, users);

        if (updatedUser.isPresent()) {
            return HttpResponse.ok(updatedUser.get());
        } else {
            return HttpResponse.notFound().body("User Not Found");
        }
    }

    @Delete("/{uid}")
    public HttpResponse<?> deleteUsers(@PathVariable int uid) {
        if (deleteUserService.deleteUsers(uid)) {
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    @Post("/verify/{uid}")
    public HttpResponse<?> verifyUser(@PathVariable int uid) {
        try {
            userVerificationService.verifyUser(uid);
            return HttpResponse.ok(new RestResponse(0, "User is verified", null));
        } catch (Exception e) {
            return HttpResponse.badRequest(new RestResponse(0, e.getMessage(), null));
        }
    }

    @Get("/borrowers-lists")
    public HttpResponse<Optional<Users>> showAllBorrowers() {
        return null;
    }

//    @Get("/lenders-lists")
//    public HttpResponse<RestResponse> showAllLenders() {
//        Optional<Users> lenders = showLenderService.showAllLenders();
//        return HttpResponse.ok(new RestResponse(200,"List of all lenders",lenders));
//    }
}

