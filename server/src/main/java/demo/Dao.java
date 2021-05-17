package demo;

import dto.MessageDto;
import java.util.List;

public interface Dao<T extends MessageDto> {
    static DataAccessObject getInstance(){
        throw new RuntimeException("not implemented");
    }

    T put(T item);

    List<T> getItems();

    void delete(String id);
}
