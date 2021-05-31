package com.demo.contollers;

import javax.servlet.http.HttpServletRequest;

import com.demo.dto.DataResponse;
import com.demo.model.Session;
import com.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modules/users/logout")
public class LogoutUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<DataResponse> logout() {
        DataResponse response = new DataResponse();
        try {
            String currentSessinId = request.getHeader("SESSIONID");
            Session session = userService.logout(currentSessinId);
            response.setPayload(session);
            response.setStatus(true);
            response.getMessages().add("User logout");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
