package com.citytech.global.user.services.updateuserservices;

import com.citytech.global.user.repository.Users;

import java.util.Optional;

public interface UpdateUserService {
    Optional<Users> updateUsers(int uid, Users users);
}
