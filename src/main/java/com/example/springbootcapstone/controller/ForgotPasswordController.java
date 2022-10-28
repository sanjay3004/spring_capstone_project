package com.example.springbootcapstone.controller;

import com.example.springbootcapstone.Document.Token;
import com.example.springbootcapstone.Document.User;
import com.example.springbootcapstone.Document.passwordDto;
import com.example.springbootcapstone.service.ForgotPasswordService;
import com.example.springbootcapstone.service.MailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ForgotPasswordController {

    @Autowired
    MailSendingService mailSendingService;

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot")
    @ResponseBody
    public String forgotPass(@RequestBody String username)  {
        String generatedToken=forgotPasswordService.generateToken(username);
        mailSendingService.forgotMailSender(username,generatedToken);
        return "mail sent";
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestParam("token") String generatedToken){
        if(forgotPasswordService.allow(generatedToken)){
            return "you can change now";
        }

       return "the link is broken";
    }

    @RequestMapping("/change")
    @ResponseBody
    public String change(@Valid @RequestBody passwordDto pass){
        if(forgotPasswordService.isFound(pass.getUsername())){
            if(forgotPasswordService.change(pass.getUsername(),pass.getPassword())){
                    return "changed successfully";
            }
            return "you are not allowed to change";
        }
        return "user Not found";
    }

}
