package kz.zzhalelov.messengerspringbootpractice.storage;

import kz.zzhalelov.messengerspringbootpractice.exception.NotFoundException;
import kz.zzhalelov.messengerspringbootpractice.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private Map<Integer, User> users = new HashMap<>();
    int counter = 1;

    @Override
    public User create(User user) {
        user.setId(getUniqueId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователь не найден");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    private int getUniqueId() {
        return counter++;
    }
}