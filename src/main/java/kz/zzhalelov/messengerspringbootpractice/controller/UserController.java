package kz.zzhalelov.messengerspringbootpractice.controller;

import kz.zzhalelov.messengerspringbootpractice.model.Message;
import kz.zzhalelov.messengerspringbootpractice.model.User;
import kz.zzhalelov.messengerspringbootpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    //POST /users/{userId}/friends/{friendId}/chat
    @PostMapping("/{userId}/friends/{friendId}/chat")
    public Message send(@PathVariable int userId,
                        @PathVariable int friendId,
                        @RequestBody Map<String, String> body) {
        String message = body.get("message");
        return userService.sendMessage(userId, friendId, message);
    }

    //GET /users/{userId}/friends/{friendId}/chat
    @GetMapping("/{userId}/friends/{friendId}/chat")
    public List<Message> showChat(@PathVariable int userId,
                                  @PathVariable int friendId) {
        return userService.getChat(userId, friendId);
    }
}