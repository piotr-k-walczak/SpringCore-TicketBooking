package piotrwalczak.springcore.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    T save(T t);

    boolean delete(long id);
}
