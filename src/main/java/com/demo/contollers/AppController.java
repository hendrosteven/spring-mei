package com.demo.contollers;

import java.util.Base64;

import com.demo.dto.DataResponse;
import com.demo.model.App;
import com.demo.service.AppService;
import com.demo.utility.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apps")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping
    public ResponseEntity<DataResponse> registerApp(@RequestBody App app){
        DataResponse response = new DataResponse();
        try{
            app.setPassword(UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest());
            app.setAppKey(generateAppKey(app.getUsername(),app.getPassword()));
            response.setStatus(true);
            response.setPayload(appService.register(app));
            response.getMessages().add("App saved");
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private String generateAppKey(String userName, String password) {
        String baseString = userName + ":" + password;
        String appKey = Base64.getEncoder().encodeToString(baseString.getBytes());
        return appKey;
    }

    @GetMapping
    public ResponseEntity<DataResponse> findAll(){
        DataResponse response = new DataResponse();
        response.setStatus(true);
        response.setPayload(appService.findAll());
        return ResponseEntity.ok(response);
    }
    
}
