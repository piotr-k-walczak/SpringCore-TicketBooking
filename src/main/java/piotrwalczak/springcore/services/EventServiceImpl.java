package piotrwalczak.springcore.services;

import piotrwalczak.springcore.dao.EventDao;
import piotrwalczak.springcore.model.Event;

import java.util.Date;
import java.util.List;

public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    @Override
    public Event getEventById(long id) {
        return eventDao.get(id).orElse(null);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDao.getByDate(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDao.delete(eventId);
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
