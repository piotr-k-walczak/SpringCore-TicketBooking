package piotrwalczak.springcore;

import piotrwalczak.springcore.dao.EventDao;
import piotrwalczak.springcore.model.Event;
import piotrwalczak.springcore.model.EventImpl;
import piotrwalczak.springcore.storage.Storage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DaoTests {

    @Mock
    Storage storage;

    @InjectMocks
    EventDao eventDao;

    @Test
    public void get_all_events_on_specific_date(){
        Event e1 = new EventImpl("ABC", Date.valueOf(LocalDate.parse("2023-05-05")));
        Event e2 = new EventImpl("DXC", Date.valueOf(LocalDate.parse("2023-02-05")));
        Event e3 = new EventImpl("DZS", Date.valueOf(LocalDate.parse("2023-02-05")));
        when(storage.getAllForType("event")).thenReturn(List.of(e1, e2, e3));

        assertArrayEquals(eventDao.getByDate(Date.valueOf(LocalDate.parse("2023-05-05")), 1, 1).toArray(), List.of(e1).toArray());
        assertArrayEquals(eventDao.getByDate(Date.valueOf(LocalDate.parse("2023-05-05")), 1, 1).toArray(), List.of(e1).toArray());
        assertArrayEquals(eventDao.getByDate(Date.valueOf(LocalDate.parse("2023-02-05")), 100, 1).toArray(), List.of(e2, e3).toArray());
        assertArrayEquals(eventDao.getByDate(Date.valueOf(LocalDate.parse("2023-02-05")), 1, 1).toArray(), List.of(e2).toArray());
        assertArrayEquals(eventDao.getByDate(Date.valueOf(LocalDate.parse("2023-02-05")), 1, 2).toArray(), List.of(e3).toArray());
    }

    @Test
    public void get_all_events_with_title(){
        Event e1 = new EventImpl("ABC", new java.util.Date());
        Event e2 = new EventImpl("DXC", new java.util.Date());
        when(storage.getAllForType("event")).thenReturn(List.of(e1, e2));

        assertArrayEquals(eventDao.getByTitle("C", 100, 1).toArray(), List.of(e1, e2).toArray());
        assertArrayEquals(eventDao.getByTitle("C", 1, 1).toArray(), List.of(e1).toArray());
        assertArrayEquals(eventDao.getByTitle("D", 1, 1).toArray(), List.of(e2).toArray());
        assertArrayEquals(eventDao.getByTitle("AVD", 1, 1).toArray(), List.of().toArray());
    }
}
