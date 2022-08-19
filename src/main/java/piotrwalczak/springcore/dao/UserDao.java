package piotrwalczak.springcore.dao;

import piotrwalczak.springcore.model.User;
import piotrwalczak.springcore.storage.Storage;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private Storage storage;

    @Override
    public Optional<User> get(long id) {
        return storage.get("user:" + id).map(x -> (User) x);
    }

    @Override
    public List<User> getAll() {
        return storage.getAllForType("user").stream().map(x -> (User) x).toList();
    }

    public Optional<User> getByEmail(String email) {
        return getAll().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    public List<User> getByName(String name, int pageSize, int pageNum) {
        if (pageNum >= 1 && pageSize >= 1 && name != null) {
            return getAll().stream()
                    .filter(event -> event.getName().contains(name))
                    .skip((long) (pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
        }
        return List.of();
    }

    @Override
    public User save(User user) {
        return (User) storage.put("user:" + user.getId(), user);
    }

    @Override
    public boolean delete(long id) {
        return storage.delete("user:" + id);
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
