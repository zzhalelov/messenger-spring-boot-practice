package kz.zzhalelov.messengerspringbootpractice.controller;

import kz.zzhalelov.messengerspringbootpractice.model.User;
import kz.zzhalelov.messengerspringbootpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User add(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping
    public Collection<User> getAll() {
        return userService.findAll();
    }

    //PUT /users/{userId}/friends/{friendId}
    @PutMapping("/{userId}/friends/{friendId}")
    public void addFriend(@PathVariable int userId,
                          @PathVariable int friendId) {
        userService.addFriend(userId, friendId);
    }
}