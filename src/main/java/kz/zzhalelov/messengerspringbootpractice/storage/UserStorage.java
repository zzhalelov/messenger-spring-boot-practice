package kz.zzhalelov.messengerspringbootpractice.storage;

import kz.zzhalelov.messengerspringbootpractice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User create(User user);

    User update(User user);

    Optional<User> findById(int id);

    List<User> findAll();
}
