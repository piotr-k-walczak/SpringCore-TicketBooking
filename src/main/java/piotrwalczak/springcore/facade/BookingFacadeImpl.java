package piotrwalczak.springcore.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.model.Ticket;
import piotrwalczak.springcore.model.User;
import piotrwalczak.springcore.services.EventService;
import piotrwalczak.springcore.services.TicketService;
import piotrwalczak.springcore.services.UserService;

import java.util.Date;
import java.util.List;

public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    private final static Logger logger = LoggerFactory.getLogger(BookingFacadeImpl.class);

    public BookingFacadeImpl(EventService eventService, UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public Event getEventById(long eventId) {
        logger.info("Called getEventById(" + eventId + ")");
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("Called getEventsByTitle(" + title + "," + pageSize + "," + pageNum + ")");
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        logger.info("Called getEventsForDay(" + day.toString() + "," + pageSize + "," + pageNum + ")");
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        logger.info("Called createEvent");
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        logger.info("Called updateEvent");
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        logger.info("Called deleteEvent");
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        logger.info("Called getEventById(" + userId + ")");
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Called getEventByEmail(" + email + ")");
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("Called getUsersByName(" + name + "," + pageSize + "," + pageNum + ")");
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        logger.info("Called createUser");
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        logger.info("Called updateUser");
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        logger.info("Called deleteUser");
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) throws IllegalStateException {
        logger.info("Called bookTicket(" + userId + "," + eventId + "," + place + "," + category + ")");
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("Called getBookedTickets(" + user.getId() + "," + pageSize + "," + pageNum + ")");
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("Called getBookedTickets(" + event.getId() + "," + pageSize + "," + pageNum + ")");
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        logger.info("Called cancelTicket(" + ticketId + ")");
        return ticketService.cancelTicket(ticketId);
    }
}
