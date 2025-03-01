package com.starters.backend.users.application.services.Impl;

import com.starters.backend.users.application.dtos.UserCreateDTO;
import com.starters.backend.users.application.dtos.UserDTO;
import com.starters.backend.users.application.services.UserService;
import com.starters.backend.users.domain.entities.User;
import com.starters.backend.users.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .orElse(null);
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword()); // ⚠️ En producción, hashea la contraseña
        user.setEmail(userCreateDTO.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
