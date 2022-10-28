package com.example.springbootcapstone.service;

import com.example.springbootcapstone.Document.Token;
import com.example.springbootcapstone.Document.User;
import com.example.springbootcapstone.repository.TokenRepository;
import com.example.springbootcapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        User givenUser=userRepository.findByUserName(user.getUsername());
        if(givenUser==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        else{
            System.out.println(false);
            return false;
        }
    }

    public String addToken(User user){
        Token token=new Token(user);
        tokenRepository.save(token);
        return token.getGeneratedToken();
    }

    public boolean confirmAccount(String generatedToken){
        Token token = tokenRepository.findByGeneratedToken(generatedToken);
        if(token==null){
            return false;
        }
        User currUser=token.getUser();
        currUser.setEnabled(true);
        tokenRepository.delete(token);
        userRepository.save(currUser);
        return true;
    }

}
