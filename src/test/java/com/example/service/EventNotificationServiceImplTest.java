package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Attendee;
import com.example.Event;
import com.example.EventType;

class EventNotificationServiceImplTest {
	
	EventNotificationServiceImpl eventNotificationService;
	Event event;
	Attendee attendee;

	@BeforeEach
	void setUp() throws Exception {
		eventNotificationService = new EventNotificationServiceImpl();
		event = new Event(1L, "JavaWorld", EventType.TECH, eventNotificationService);
		attendee = new Attendee(1L, "morgui", "morgui@email.com");
	}

	/*
	 * utilizo el assertDoesNotThrow para asegurarme que no se lanza la excepcion y comprobar que 
	 * el if entra y se cumplen las posibilidades de este
	 */
	@Test
	@DisplayName("Test que comprueba que no falla el anuncio cuando la lista de attendees es null")
	void testAnnounceListNull() {
		event.setAttendees(null);
		assertDoesNotThrow(() -> eventNotificationService.announce(event));
	}
	
	@Test
	@DisplayName("Test que comprueba que no falla el anuncio cuando el event es null")
	void testAnnounceEventNull() {
		assertDoesNotThrow(() -> eventNotificationService.announce(null));
	}
	
	@Test
	@DisplayName("Test que comprueba que la lista de attendees del event esta vacía")
	void testAnnounceEmptyList() {
		event.setAttendees(new ArrayList<Attendee>());
		eventNotificationService.announce(event);
		assertEquals(0, attendee.getNotifications().size());
	}
	
	@Test
	@DisplayName("Test que comprueba que se añada la notificación al attendee")
	void testAnnounce() {
		Attendee attendee = new Attendee();
		event.addAttendee(attendee);
		
		event.notifyAssistants();
		assertEquals(1, attendee.getNotifications().size());
		assertEquals("The next big event is coming!", attendee.getNotifications().get(0).getMessage());
	}

}
