package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.service.EventNotificationService;
import com.example.service.EventNotificationServiceImpl;

@ExtendWith(MockitoExtension.class)
class EventTest {

	@Mock
	EventNotificationService mock;
	
	@InjectMocks // inyectar los mocks dependencia dentro de la clase 
	EventNotificationServiceImpl eventNotificationService;

	Event event;

	@BeforeEach
	void setUp() throws Exception {
		event = new Event(1L, "Fiesta", EventType.TECH, eventNotificationService);
	}


	@Test
	@DisplayName("Test con un attendee null")
	void testAddAttendeeNull() {
		event = new Event();
		int number = event.getAttendees().size();
		event.addAttendee(null);
		assertEquals(number, event.getAttendees().size());
		
	}

	@Test
	@DisplayName("Test que añade un attendee que no esté en la lista")
	void testAddAttendeeExists() {
		Attendee attendee = new Attendee();
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());
		
	}
	
	@Test
	@DisplayName("Test que comprueba si el attendee esta ya en la lista")
	void testAddAttendeeRepeate() {
		Attendee attendee = new Attendee();
		event.addAttendee(attendee);
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());

	}
	
	@Test
	@DisplayName("Test que comprueba el añadir un attendee cuya lista es nula")
	void testAddAttendeeNullList() {
		Attendee attendee = new Attendee();
		event.setAttendees(null);
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());
	}
}
