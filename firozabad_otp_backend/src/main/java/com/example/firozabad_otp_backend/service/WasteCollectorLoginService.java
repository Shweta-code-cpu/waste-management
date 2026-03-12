//package com.example.firozabad_otp_backend.service;
//
//import com.example.firozabad_otp_backend.model.WasteCollectorLogin;
//import com.example.firozabad_otp_backend.repository.WasteCollectorLoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class WasteCollectorLoginService {
//
//    @Autowired
//    private WasteCollectorLoginRepository repository;
//
//    public boolean login(String mobile) {
//        Optional<WasteCollectorLogin> user = repository.findByMobile(mobile);
//        return user.isPresent(); // Returns true if user exists, false otherwise
//    }
//}


package com.example.firozabad_otp_backend.service;

import com.example.firozabad_otp_backend.model.WasteCollectorLoginData;
import com.example.firozabad_otp_backend.repository.WasteCollectorLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WasteCollectorLoginService {

    @Autowired
    private WasteCollectorLoginDataRepository repository;

    public boolean isUserExists(String mobile) {
        return repository.findByMobile(mobile).isPresent();
    }

    public String updateCollectorData(String mobile, String userName, String image) {
        Optional<WasteCollectorLoginData> existingUser = repository.findByMobile(mobile);
        if (existingUser.isPresent()) {
            WasteCollectorLoginData user = existingUser.get();
            user.setUserName(userName);
            user.setWastecollectorImage(image);
            repository.save(user);
            return "User data updated successfully!";
        }
        return "Mobile number not found";
    }
}
