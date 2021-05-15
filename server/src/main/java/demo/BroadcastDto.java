package demo;

import java.util.List;

public class BroadcastDto {
    public final List<MessageDto> messages;
    public final Integer totalUsers;

    public BroadcastDto(List<MessageDto> messages, Integer totalUsers) {
        this.messages = messages;
        this.totalUsers = totalUsers;
    }
}