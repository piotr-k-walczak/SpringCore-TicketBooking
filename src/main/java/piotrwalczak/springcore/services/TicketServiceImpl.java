package piotrwalczak.springcore.services;

import piotrwalczak.springcore.dao.TicketDao;
import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.model.Ticket;
import piotrwalczak.springcore.model.TicketImpl;
import piotrwalczak.springcore.model.User;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) throws IllegalStateException {
        if (ticketDao.getTakenPlacesForEvent(eventId).contains(place)) {
            throw new IllegalStateException("Place already taken");
        }

        Ticket ticket = new TicketImpl();
        ticket.setCategory(category);
        ticket.setEventId(eventId);
        ticket.setUserId(userId);
        ticket.setPlace(place);
        return ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getForUser(user.getId(), pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getForEvent(event.getId(), pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.delete(ticketId);
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }
}
