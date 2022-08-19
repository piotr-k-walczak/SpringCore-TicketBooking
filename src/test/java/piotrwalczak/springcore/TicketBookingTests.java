package piotrwalczak.springcore;

import piotrwalczak.springcore.dao.TicketDao;
import piotrwalczak.springcore.model.TicketImpl;
import piotrwalczak.springcore.services.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import piotrwalczak.springcore.model.Ticket;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TicketBookingTests {

    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    TicketDao ticketDao;

    @Test(expected = IllegalStateException.class)
    public void place_already_taken(){
        when(ticketDao.getTakenPlacesForEvent(1)).thenReturn(List.of(1, 2, 3));
        ticketService.bookTicket(1, 1, 2, Ticket.Category.STANDARD);
    }

    @Test
    public void place_not_taken(){
        when(ticketDao.getTakenPlacesForEvent(1)).thenReturn(List.of(1, 2, 3));
        when(ticketDao.save(any(Ticket.class))).thenReturn(new TicketImpl());
        assertNotNull(ticketService.bookTicket(1, 1, 5, Ticket.Category.STANDARD));
    }
}
