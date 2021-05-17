package DTO;

public class BroadcastDTO implements DTO
{
    public final String MessageType;
    public final DTO pay;

    public BroadcastDTO(String MessageType, DTO pay)
    {
        this.MessageType = MessageType;
        this.pay = pay;
    }
}
