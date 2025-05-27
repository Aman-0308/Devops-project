package com.infy.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.dto.ChangePasswordDTO;
import com.infy.dto.CustomerAddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.exception.EPharmacyException;

@Service
public class CredentialsValidationService {

    private final UserRepository userRepository;

    public CredentialsValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse validateCredentials(LoginRequest loginRequest) {
        UserCredential user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Account is inactive.");
        }

        boolean passwordMatches = PasswordUtils.matches(loginRequest.getPassword(), user.getPasswordHash());
        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return new LoginResponse(true, "Login successful");
    }
}

