package com.teste.projetoAPI.service;

import com.teste.projetoAPI.entities.User;
import com.teste.projetoAPI.service.exceptions.DataBaseException;
import com.teste.projetoAPI.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.teste.projetoAPI.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById (Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert (User user) {
        String encoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return userRepository.save(user);
    }

    public void delete (Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new  ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public User update (Long id,User user) {
        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, user);
            String encoder = passwordEncoder.encode(user.getPassword());
            entity.setPassword(encoder);
            return userRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setPassword(user.getPassword());
    }

    public Boolean validationPassword(User user) {
        String password = userRepository.getById(user.getId()).getPassword();
        Boolean valid = passwordEncoder.matches(user.getPassword(), password);
        return valid;
    }
}



