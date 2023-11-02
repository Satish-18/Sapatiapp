package com.citytech.global.user.services.userverificationservice;
import com.citytech.global.common.enumeration.TransactionStatus;
import com.citytech.global.common.exceptionhandler.AccessDeniedException;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import jakarta.inject.Inject;


public class UserVerificationServiceImp implements UserVerificationService{
    private final UserRepository userRepository;
    @Inject
    public UserVerificationServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void verifyUser(int uid) {
        Users user = userRepository.findByUid(uid).orElseThrow(() ->
                new AccessDeniedException("User not found"));

        System.out.println(user);

        if (user.getUserStatus() == TransactionStatus.UNVERIFIED) {
            user.setUserStatus(TransactionStatus.VERIFIED);
            userRepository.update(user);
        } else {
            throw new AccessDeniedException("User is already verified");
        }
    }
}









