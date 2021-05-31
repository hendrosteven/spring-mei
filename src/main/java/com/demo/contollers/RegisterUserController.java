package com.demo.contollers;

import com.demo.dto.DataResponse;
import com.demo.dto.RegisterRequest;
import com.demo.model.User;
import com.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/register")
public class RegisterUserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<DataResponse> register(@RequestBody RegisterRequest registerData) {
        DataResponse response = new DataResponse();
        try {
            User user = new User();
            user.setEmail(registerData.getEmail());
            user.setPassword(registerData.getPassword());
            user.setFullName(registerData.getFullName());

            userService.save(user);

            response.setStatus(true);
            response.getMessages().add("User saved");
            response.setPayload(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.getMessages().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
