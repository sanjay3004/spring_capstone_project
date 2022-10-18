package com.example.springbootcapstone.controller;

import com.example.springbootcapstone.Document.User;
import com.example.springbootcapstone.service.MailSendingService;
import com.example.springbootcapstone.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/register")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    MailSendingService mailSendingService;

    @PostMapping("/newUser")
    public ResponseEntity<?> signUp( @Valid @RequestBody User user){
        boolean isAdded = signUpService.addUser(user);
        if(!isAdded){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists");
        }
        String generatedToken = signUpService.addToken(user);
        mailSendingService.mailSender(user.getUsername(),generatedToken);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mail Sent");
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String generatedToken) {
        boolean isFound = signUpService.confirmAccount(generatedToken);
        if(!isFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

}
