package com.example.springbootcapstone.Document;

import com.example.springbootcapstone.CustomAnnotations.ValidPassword;

public class passwordDto {

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
