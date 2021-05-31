package com.demo.contollers;

import com.demo.dto.DataResponse;
import com.demo.dto.LoginRequest;
import com.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/login")
public class LoginUserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<DataResponse> login(@RequestBody LoginRequest login){
        DataResponse response = new DataResponse();
        try{
            response.setPayload(userService.login(login.getEmail(), login.getPassword()));
            response.setStatus(true);
            response.getMessages().add("Login success");
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
