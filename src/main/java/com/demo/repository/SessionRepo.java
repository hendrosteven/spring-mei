package com.demo.repository;

import com.demo.model.Session;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SessionRepo extends CrudRepository<Session, Long> {

    public Session findBySessionId(String sessionId);
    
    @Modifying
    @Query("UPDATE Session s SET s.active = :param1 WHERE s.user.id = :param2")
    public int setActiveByUserId(@Param("param1") boolean active, @Param("param2") Long userId);

}
