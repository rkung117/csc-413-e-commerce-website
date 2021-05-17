package demo;

public class MessageDto {
    public final String email;
    public final String product;
    public final String description;
    public final String price;

    public MessageDto(String email, String product, String description, String price){
        this.email = email;
        this.product = product;
        this.description = description;
        this.price = price;
    }
}