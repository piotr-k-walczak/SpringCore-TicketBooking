package piotrwalczak.springcore.dao;

import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.storage.Storage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventDao implements Dao<Event> {

    private Storage storage;

    @Override
    public Optional<Event> get(long id) {
        return storage.get("event:" + id).map(x -> (Event) x);
    }

    @Override
    public List<Event> getAll() {
        return storage.getAllForType("event").stream().map(x -> (Event) x).toList();
    }

    public List<Event> getByTitle(String title, int pageSize, int pageNum) {
        if (pageNum >= 1 && pageSize >= 1 && title != null) {
            return getAll().stream()
                    .filter(event -> event.getTitle().contains(title))
                    .skip((long) (pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
        }
        return List.of();
    }

    public List<Event> getByDate(Date date, int pageSize, int pageNum) {
        if (pageNum >= 1 && pageSize >= 1 && date != null) {
            return getAll().stream()
                    .filter(event -> event.getDate().equals(date))
                    .skip((long) (pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
        }
        return List.of();
    }

    @Override
    public Event save(Event event) {
        return (Event) storage.put("event:" + event.getId(), event);
    }

    @Override
    public boolean delete(long id) {
        return storage.delete("event:" + id);
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
