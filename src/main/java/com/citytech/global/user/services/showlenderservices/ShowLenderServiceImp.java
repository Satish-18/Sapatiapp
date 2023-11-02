package com.citytech.global.user.services.showlenderservices;

import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import com.citytech.global.user.services.ApiResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class ShowLenderServiceImp implements ShowLenderService {
    private UserRepository userRepository;

    @Inject
    public ShowLenderServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<List<LenderResponse>> getLenderList() {
        return null;
    }
//        List<Users> lenderList = userRepository.findByUserType("LENDER");
//        List<LenderResponse> listOfLenderRequestInfo = mapLenderListToLenderInfo(lenderList);
//        return new ApiResponse<>("200", "Lender List", listOfLenderRequestInfo);
//    }
//    private List<LenderResponse> mapLenderListToLenderInfo(List<Users> lenderList) {
//        return lenderList.stream()
//                .map(lender -> {
//                            LenderResponse lenderRequestInfo = new LenderResponse();
//                            lenderRequestInfo.setFirstName(lender.getFirstName());
//                            lenderRequestInfo.setLastName(lender.getLastName());
//                            return lenderRequestInfo;
//                        }
//                ).collect(Collectors.toList());
//    }
}
