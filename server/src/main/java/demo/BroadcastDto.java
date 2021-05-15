package demo;

import java.util.List;

public class BroadcastDto {
    public final List<listingDto> emails;
    public final List<listingDto> titles;
    public final List<listingDto> descriptions;
    public final List<listingDto> prices;
    public final Integer totalUsers;

    public BroadcastDto(List<listingDto> emails, List<listingDto> titles, List<listingDto> descriptions, List<listingDto> prices, Integer totalUsers) {
        this.emails = emails;
        this.titles = titles;
        this.descriptions = descriptions;
        this.prices = prices;
        this.totalUsers = totalUsers;
    }
}