package piotrwalczak.springcore.storage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface Storage {

    void loadFromFile(String filepath) throws FileNotFoundException;

    Optional<Object> get(String key);

    List<Object> getAllForType(String type);

    boolean delete(String key);

    Object put(String key, Object object);
}
