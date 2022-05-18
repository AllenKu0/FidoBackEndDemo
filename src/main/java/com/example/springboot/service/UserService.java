package com.example.springboot.service;

import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.utitlity.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterRequest dto) {
        Optional<User> userOptional = this.findByEmail(dto.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }
        User user = userMapper.convertOfUserRegisterDTO(dto);
        String cryptPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        user.setPassword(cryptPassword);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }
    }

    public Optional<User> findByEmail(String userName) {
        return userRepository.findByEmail(userName);

    }
}
