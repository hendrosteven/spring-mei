package com.demo.service;

import java.util.Date;

import javax.transaction.Transactional;

import com.demo.model.Session;
import com.demo.model.User;
import com.demo.repository.SessionRepo;
import com.demo.repository.UserRepo;
import com.demo.utility.MD5Generator;
import com.demo.utility.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    public User save(User user) throws Exception {
        user.setPassword(MD5Generator.generate(user.getPassword()));
        return userRepo.save(user);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    private User findByEmailAndPassword(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    public Session login(String email, String password) throws Exception {
        User user = findByEmailAndPassword(email, MD5Generator.generate(password));

        if (user == null) {
            return null;
        }
        try {
            // kick all sessionID sebelumnya
            sessionRepo.setActiveByUserId(false, user.getId());

            // create new session
            Session session = new Session();
            session.setSessionId(UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest());
            session.setUser(user);
            session.setActive(true);
            return sessionRepo.save(session);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Session logout(String sessionId) {
        Session session = sessionRepo.findBySessionId(sessionId);
        if (session == null) {
            return null;
        }
        session.setLogoutAt(new Date());
        session.setActive(false);
        return sessionRepo.save(session);
    }

}
