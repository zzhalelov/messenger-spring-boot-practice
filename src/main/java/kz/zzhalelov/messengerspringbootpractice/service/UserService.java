package kz.zzhalelov.messengerspringbootpractice.service;

import kz.zzhalelov.messengerspringbootpractice.exception.NotFoundException;
import kz.zzhalelov.messengerspringbootpractice.model.Message;
import kz.zzhalelov.messengerspringbootpractice.model.User;
import kz.zzhalelov.messengerspringbootpractice.storage.UserStorage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage userStorage;
    int counter = 1;

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
        friend.getFriends().add(userId);

        userStorage.update(user);
        userStorage.update(friend);
    }

    public Message sendMessage(int senderId,
                               int receiverId,
                               String messageText) {
        User sender = findById(senderId);
        User receiver = findById(receiverId);

        if (!sender.getFriends().contains(receiverId)) {
            throw new NotFoundException("Sender & Receiver aren't friends");
        }
        Message message = new Message();
        message.setId(getUniqueId());
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setMessageText(messageText);
        message.setCreatedAt(LocalDateTime.now());

        sender.getChats().computeIfAbsent(receiverId, k -> new ArrayList<>()).add(message);
        receiver.getChats().computeIfAbsent(senderId, k -> new ArrayList<>()).add(message);

        userStorage.update(sender);
        userStorage.update(receiver);
        return message;
    }

    public List<Message> getChat(int userId, int friendId) {
        User user = findById(userId);
        if (!user.getFriends().contains(friendId)) {
            throw new NotFoundException("Sender & Receiver aren't friends");
        }

        return user.getChats().getOrDefault(friendId, new ArrayList<>());
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

    private int getUniqueId() {
        return counter++;
    }
}