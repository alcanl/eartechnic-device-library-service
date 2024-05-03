package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByeMail(String eMail);
    Optional<User> findByeMailAndPassword(String eMail, String password);

}
