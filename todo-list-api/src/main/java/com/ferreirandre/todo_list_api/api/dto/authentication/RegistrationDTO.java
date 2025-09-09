package com.ferreirandre.todo_list_api.api.dto.authentication;

import com.ferreirandre.todo_list_api.domain.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String login;
    private String password;
    private UserRole role;
}
