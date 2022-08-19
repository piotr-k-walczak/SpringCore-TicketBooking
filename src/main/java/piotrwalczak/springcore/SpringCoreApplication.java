package piotrwalczak.springcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import piotrwalczak.springcore.dao.EventDao;
import piotrwalczak.springcore.dao.TicketDao;
import piotrwalczak.springcore.dao.UserDao;
import piotrwalczak.springcore.facade.BookingFacade;
import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.model.Ticket;
import piotrwalczak.springcore.model.User;
import piotrwalczak.springcore.model.UserImpl;

public class SpringCoreApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpringCoreApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        for (String bean : applicationContext.getBeanDefinitionNames()) {
            logger.info("Initialized bean " + bean);
        }

        BookingFacade bookingFacade = (BookingFacade) applicationContext.getBean("bookingFacade");
        User user = new UserImpl("Anna Maria", "am@email.com");
        bookingFacade.createUser(user);

        for (User u : ((UserDao) applicationContext.getBean("userDaoBean")).getAll()) {
            logger.info("Loaded users: " + u.getId() + "$" + u.getName());
        }

        for (Event e : ((EventDao) applicationContext.getBean("eventDaoBean")).getAll()) {
            logger.info("Loaded events: " + e.getId() + "$" + e.getTitle() + "$" + e.getDate());
        }

        for (Ticket u : ((TicketDao) applicationContext.getBean("ticketDaoBean")).getAll()) {
            logger.info("Loaded tickets: " + u.getUserId() + "$" + u.getEventId() + "$" + u.getPlace());
        }
    }
}
