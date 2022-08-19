package piotrwalczak.springcore;


import piotrwalczak.springcore.model.EventImpl;
import piotrwalczak.springcore.model.TicketImpl;
import piotrwalczak.springcore.storage.Storage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.model.Ticket;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StorageTests {

	@Autowired
	Storage storage;

	@Test
	public void new_instances_of_diffrent_classes_same_ids(){
		EventImpl.ID_COUNT = TicketImpl.ID_COUNT = 0;
		Event event = new EventImpl();
		Ticket ticket = new TicketImpl();
		assertEquals(event.getId(), ticket.getId());
	}

	@Test
	public void new_events_have_unique_ids(){
		Event event = new EventImpl();
		Event event2 = new EventImpl();
		assertEquals(event.getId() + 1, event2.getId());
	}

	@Test
	public void add_new_element_to_storage(){
		Date date = new Date();
		Event event = new EventImpl("Test event", date);
		Object returned = storage.put("event:"+event.getId(), event);
		assertEquals(returned, null);
	}

	@Test
	public void replace_object_in_storage(){
		Event event = new EventImpl("Test event", new Date());
		storage.put("event:"+event.getId(), event);
		Event event2 = new EventImpl("Test event 2", new Date());
		Object returned = storage.put("event:"+event.getId(), event2);
		assertEquals(returned, event);
	}

	@Test
	public void load_data_from_file(){
		String filepath = "load_data_test_file.json";
		assertArrayEquals(new int[]{}, new int[]{});
	}
}
