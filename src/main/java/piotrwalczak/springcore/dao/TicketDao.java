package piotrwalczak.springcore.dao;

import piotrwalczak.springcore.model.Ticket;
import piotrwalczak.springcore.storage.Storage;

import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Ticket> {

    private Storage storage;

    @Override
    public Optional<Ticket> get(long id) {
        return storage.get("ticket:" + id).map(x -> (Ticket) x);
    }

    @Override
    public List<Ticket> getAll() {
        return storage.getAllForType("ticket").stream().map(x -> (Ticket) x).toList();
    }

    public List<Ticket> getForEvent(long eventId) {
        return getAll().stream()
                .filter(ticket -> ticket.getEventId() == eventId)
                .toList();
    }

    public List<Ticket> getForEvent(long eventId, int pageSize, int pageNum) {
        if (pageNum >= 1 && pageSize >= 1 && eventId >= 1) {
            return getForEvent(eventId).stream()
                    .skip((long) (pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
        }
        return List.of();
    }

    public List<Ticket> getForUser(long userId) {
        return getAll().stream()
                .filter(ticket -> ticket.getUserId() == userId)
                .toList();
    }

    public List<Ticket> getForUser(long userId, int pageSize, int pageNum) {
        if (pageNum >= 1 && pageSize >= 1 && userId >= 1) {
            return getForUser(userId).stream()
                    .skip((long) (pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
        }
        return List.of();
    }

    public List<Integer> getTakenPlacesForEvent(long eventId) {
        return getForEvent(eventId).stream().map(event -> event.getPlace()).toList();
    }

    @Override
    public Ticket save(Ticket ticket) {
        return (Ticket) storage.put("ticket:" + ticket.getId(), ticket);
    }

    @Override
    public boolean delete(long id) {
        return storage.delete("ticket:" + id);
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
