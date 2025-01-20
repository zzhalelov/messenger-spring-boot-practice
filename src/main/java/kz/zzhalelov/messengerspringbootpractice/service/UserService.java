package kz.zzhalelov.messengerspringbootpractice.service;

import kz.zzhalelov.messengerspringbootpractice.exception.NotFoundException;
import kz.zzhalelov.messengerspringbootpractice.model.User;
import kz.zzhalelov.messengerspringbootpractice.storage.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        findById(user.getId());
        return userStorage.update(user);
    }

    public void addFriend(int userId, int friendId) {
        User user = findById(userId);
        User friend = findById(friendId);

        user.getFriends().add(friendId);
        user.getFriends().add(userId);

        userStorage.update(user);
        userStorage.update(friend);
    }

    public List<User> getFriends(int userId) {
        User user = findById(userId);
        return user.getFriends().stream()
                .map(friendId -> findById(friendId))
                .collect(Collectors.toList());
    }

    public User findById(int id) {
        return userStorage.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }
}