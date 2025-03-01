package com.starters.backend.users.application.services;

import com.starters.backend.users.application.dtos.UserCreateDTO;
import com.starters.backend.users.application.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserCreateDTO userCreateDTO);
    void deleteUser(Long id);
}

