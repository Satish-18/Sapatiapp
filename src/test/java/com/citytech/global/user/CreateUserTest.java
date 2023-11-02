package com.citytech.global.user;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import com.citytech.global.user.services.createuserservces.CreateUserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateUserTest {
    private UserRepository userRepository;
    private CreateUserServiceImp createUserServiceImp;

    @BeforeEach
    void setup(){
        userRepository = Mockito.mock(UserRepository.class);
        createUserServiceImp = new CreateUserServiceImp(userRepository);
    }
    @Test
    void shouldReturnSuccessIfSignUpIsInvalid(){
        Users userToCreate = new Users();
        userToCreate.setFirstName("Satish");
        userToCreate.setLastName("Subedi");
        userToCreate.setEmail("satish18gmail.com");
        userToCreate.setPassword("Hello18@gmail");
        userToCreate.setUserType("LENDERS");
        userToCreate.setLenderAmount(20000);

//        var result = createUserServiceImp.createUsers(userToCreate);
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> Mockito.when(createUserServiceImp.createUsers(userToCreate)));
    }


    @Test
    void shouldReturnSuccessIfEmailIsInvalid(){
        Users userToCreate = new Users();
        userToCreate.setFirstName("Satish");
        userToCreate.setLastName("Subedi");
        userToCreate.setEmail("satish18gmail.com");
        userToCreate.setPassword("Hello18@gmail");
        userToCreate.setUserType("LENDERS");
        userToCreate.setLenderAmount(20000);

//        var result = createUserServiceImp.createUsers(userToCreate);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Mockito.when(createUserServiceImp.createUsers(userToCreate)));
    }
    @Test
    void shouldReturnSuccessIfPasswordIsInvalid(){
        Users userToCreate = new Users();
        userToCreate.setFirstName("Satish");
        userToCreate.setLastName("Subedi");
        userToCreate.setEmail("satish18gmail.com");
        userToCreate.setPassword("ello18@gmail");
        userToCreate.setUserType("LENDERS");
        userToCreate.setLenderAmount(20000);

//        var result = createUserServiceImp.createUsers(userToCreate);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Mockito.when(createUserServiceImp.createUsers(userToCreate)));
    }

    @Test
    void shouldReturnSuccessIfLenderSignUpIsInvalid(){
        Users userToCreate = new Users();
        userToCreate.setFirstName("Satish");
        userToCreate.setLastName("Subedi");
        userToCreate.setEmail("satish18@gmail.com");
        userToCreate.setPassword("Hello18@gmail");
        userToCreate.setUserType("LENDER");
        userToCreate.setLenderAmount(200);

//      var result = createUserServiceImp.createUsers(userToCreate);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Mockito.when(createUserServiceImp.createUsers(userToCreate)));
    }

    @Test
    void shouldReturnSuccessIfBorrowerSignUpIsInvalid(){
        Users userToCreate = new Users();
        userToCreate.setFirstName("Satish");
        userToCreate.setLastName("Subedi");
        userToCreate.setEmail("satish18@gmail.com");
        userToCreate.setPassword("Hello18@gmail");
        userToCreate.setUserType("BORROWER");
        userToCreate.setLenderAmount(200);
        userToCreate.setBorrowerAmount(40);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Mockito.when(createUserServiceImp.createUsers(userToCreate)));
    }


}
