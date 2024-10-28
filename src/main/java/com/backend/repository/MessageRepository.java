package com.backend.repository;

import com.backend.domain.Message;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @EntityGraph(attributePaths = {"sender", "receiver"} )
    @Query("select m from Message m where ((m.sender = :sender and m.receiver = :receiver) or (m.sender = :receiver and m.receiver = :sender)) order by id")
    List<Message> findBySenderAndReceiver(User sender, User receiver);

    @EntityGraph(attributePaths = {"sender", "receiver"})
    List<Message> findAllBySenderOrReceiver(User user1, User user2);


}
