package com.ferreirandre.todo_list_api.infrastructure.repository.user;

import com.ferreirandre.todo_list_api.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
