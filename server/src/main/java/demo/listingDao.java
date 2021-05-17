package demo;

import dto.MessageDto;
import java.util.ArrayList;
import java.util.List;

public class listingDao {

    private static MessageDto instance = new Dao();
    private List<MessageDto> list = new ArrayList<>();
    public MessageDto newId;
    public static Dao getInstance(){
        return instance;
    }

    private Dao(){

    }

    @Override
    public MessageDto put(MessageDto item) {
        String unique_id = String.valueOf(Math.random());
        newId = new MessageDto(unique_id, item.description, item.email, item.price, item.product);
        list.add(newId);
        return null;
    }

    @Override
    public List<MessageDto> getItems() {
        return list;
    }

    @Override
    public void delete(String id) {
        //list.removeIf(a -> a.id.equals(newId));
        list.clear();
    }

}
