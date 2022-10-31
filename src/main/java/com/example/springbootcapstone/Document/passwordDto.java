package com.example.springbootcapstone.Document;

import com.example.springbootcapstone.CustomAnnotations.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class passwordDto {

    @NotBlank(message = "username must not be null")
    String username;

    @ValidPassword
    String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
