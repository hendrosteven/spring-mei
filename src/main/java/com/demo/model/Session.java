package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "tbl_sessions")
@Data
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =255, nullable = false, unique = true)
    private String sessionId;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutAt;

    private boolean active;

    @PrePersist
    private void createLoginAt(){
        this.loginAt = new Date();
    }
}
