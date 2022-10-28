package com.example.springbootcapstone.repository;

import com.example.springbootcapstone.Document.Enums.ForgotToken;
import com.example.springbootcapstone.Document.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForgotPasswordRepository extends MongoRepository<ForgotToken,String> {
    ForgotToken findByGeneratedToken(String generatedToken);
}
