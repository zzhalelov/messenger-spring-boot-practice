package kz.zzhalelov.messengerspringbootpractice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String login;
    private final Set<Integer> friends = new HashSet<>();
    private final Map<Integer, List<Message>> chats = new HashMap<>();
}