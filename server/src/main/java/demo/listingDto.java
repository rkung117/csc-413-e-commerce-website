package demo;

public class listingDto
{
    public final String email;
    public final String title;
    public final String description;
    public final int price;

    public listingDto(String email, String title, String description, int price) {
        this.email = email;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}