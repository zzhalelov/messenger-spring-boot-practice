package kz.zzhalelov.messengerspringbootpractice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String login;
    private final Set<Integer> friends = new HashSet<>();
    private final Map<Integer, Message> chats = new HashMap<>();
}