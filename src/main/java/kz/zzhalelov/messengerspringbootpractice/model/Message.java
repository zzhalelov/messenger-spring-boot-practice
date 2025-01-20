package kz.zzhalelov.messengerspringbootpractice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Message {
    private int id;
    private int sender;
    private int receiver;
    private String messageText;
    private LocalDateTime createdAt;
}