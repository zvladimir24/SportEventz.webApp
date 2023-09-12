
package com.SportEventz.webApp.service;

import com.SportEventz.webApp.dto.RegistrationDto;
import com.SportEventz.webApp.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}

