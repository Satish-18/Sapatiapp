package com.citytech.global.user.services.deleteuserservices;
import com.citytech.global.user.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


@Singleton
public class DeleteUserServiceImp implements DeleteUserService {
    private UserRepository userRepository;
    @Inject
    public DeleteUserServiceImp(UserRepository userRepository){
        this.userRepository= userRepository;
    }
    @Override
    public boolean deleteUsers(int uid){
        userRepository.deleteById(uid);
        return true;
}


}
