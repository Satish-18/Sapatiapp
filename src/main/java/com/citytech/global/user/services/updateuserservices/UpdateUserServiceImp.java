package com.citytech.global.user.services.updateuserservices;

import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import jakarta.inject.Inject;

import java.util.Optional;

public class UpdateUserServiceImp implements UpdateUserService {
    private final UserRepository userRepository;
    @Inject
    UpdateUserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Users> updateUsers(int uid, Users users){

        if (userRepository.existsById(uid)){
            users.setUid(uid);
            return Optional.of(userRepository.update(users));
        }
        return Optional.empty();
    }
}
