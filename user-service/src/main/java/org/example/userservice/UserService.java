package org.example.userservice;

import jakarta.persistence.EntityNotFoundException;
import org.example.userservice.communication.UserEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEventProducer userEventProducer;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User create(UserDto dto){
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    public User update(User user){
        if(!userRepository.existsById(user.getId())){
            throw new EntityNotFoundException("User not found");
        }
        User nu = new User();
        nu.setId(user.getId());
        nu.setLogin(user.getLogin());
        nu.setPassword(passwordEncoder.encode(user.getPassword()));
        nu.setEmail(user.getEmail());
        return userRepository.save(nu);
    }

    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
        userEventProducer.sendUserDeleteEvent(id);
    }
}
