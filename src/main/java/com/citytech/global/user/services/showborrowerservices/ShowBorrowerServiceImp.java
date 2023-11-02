package com.citytech.global.user.services.showborrowerservices;
import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;


@Singleton
public class ShowBorrowerServiceImp implements ShowBorrowerService {

    private UserRepository userRepository;

    @Inject
    public ShowBorrowerServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Users> showAllBorrowers(){
        Optional<Users> existingBorrower = userRepository.findByUserType("BORROWER");
        return userRepository.findByUserType("existingBorrower");

    }
}
