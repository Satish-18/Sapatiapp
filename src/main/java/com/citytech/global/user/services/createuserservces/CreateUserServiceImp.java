package com.citytech.global.user.services.createuserservces;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.exceptionhandler.AccessDeniedException;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class CreateUserServiceImp implements CreateUserService {
    private final UserRepository userRepository;

    public CreateUserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users createUsers(Users users)  {
        if(userRepository.findByEmail(users.getEmail()).isPresent()){
            throw  new IllegalArgumentException("The user is already registered with this email");
        }
        if (!isEmailValid(users.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!isPasswordValid(users.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one upper case letter and one number also one special character");
        }

        Optional<Users> existingAdminUser = userRepository.findByUserType("ADMIN");
        if ("ADMIN".equalsIgnoreCase(users.getUserType()) && existingAdminUser.isPresent()) {
            throw  new IllegalArgumentException("An admin user already exists.");
        }

        if("ADMIN".equalsIgnoreCase(users.getUserType())){
            if(users.getBorrowerAmount() !=0){
                throw  new IllegalArgumentException("Admin can't have borrowerAmount");
            }
            if(users.getLenderAmount() != 0){
                throw  new IllegalArgumentException("Admin can't have lenderAmount");
            }
            users.setUserStatus(TransactionStatus.VERIFIED);

        }else{
            users.setUserStatus(TransactionStatus.UNVERIFIED);
        }
        if("BORROWER".equalsIgnoreCase(users.getUserType())){
            if(users.getBorrowerAmount() != 0){
                throw  new IllegalArgumentException("you can't add money here as a borrower");
            }
            if(users.getLenderAmount() !=0){
                throw new AccessDeniedException("You can't add any money here as youare not a Lender");
            }
        }
        if("LENDER".equalsIgnoreCase(users.getUserType())){
            if(users.getLenderAmount() == 0){
                throw  new IllegalArgumentException("You have to add the balance as a lender ");
            }
            if(users.getLenderAmount() < 20000){
                throw  new IllegalArgumentException(" Your Balance should be more than or equals to 20000");
            }
            if(users.getBorrowerAmount() !=0){
                throw  new IllegalArgumentException("You are not a borrower");
            }

        }

        users.setCreatedOn(LocalDateTime.now());
        return userRepository.save(users);

    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    private boolean isPasswordValid(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }
}
