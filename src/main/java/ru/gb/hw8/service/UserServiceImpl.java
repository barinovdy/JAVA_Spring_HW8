package ru.gb.hw8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.hw8.aspects.TrackUserAction;
import ru.gb.hw8.model.User;
import ru.gb.hw8.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @TrackUserAction
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @TrackUserAction
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    @TrackUserAction
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @TrackUserAction
    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()){
            User userUpdate = userOptional.get();
            userUpdate.setName(user.getName());
            userUpdate.setAge(user.getAge());
            userUpdate.setEmail(user.getEmail());
            return userRepository.save(userUpdate);
        } else{
            throw new IllegalArgumentException("User with id " + user.getId() + " not found!");
        }
    }

    @Override
    @TrackUserAction
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
