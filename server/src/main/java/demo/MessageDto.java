package demo;

public class MessageDto {
    public final String message;
    public final String type;
    public final String price;
    public final String title;

    public MessageDto(String message, String type, String price, String title) {
        this.message = message;
        this.type = type;
        this.price = price;
        this.title = title;
    }
}
