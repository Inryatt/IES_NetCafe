package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.User;
import ua.ies.group3.netcafe.api.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
