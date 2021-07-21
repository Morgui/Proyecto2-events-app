package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		event = new Event(1L, "PyCon", EventType.TECH, eventNotificationService);
	}


	@Test
	@DisplayName("Test con un attendee null")
	void testAddAttendeeNull() {
		int personAmount = event.getAttendees().size();
		event.addAttendee(null);
		assertEquals(personAmount, event.getAttendees().size());
		
	}

	@Test
	@DisplayName("Test que añade un attendee que no esté en la lista")
	void testAddAttendeeExists() {
		Attendee attendee = new Attendee();
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());
		
	}
	
	@Test
	@DisplayName("Test que comprueba si el attendee ya esta en la lista")
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
	
	@Test
	@DisplayName("Test en el caso de que no se le pase una lista de attendees")
	void testAddAttendeesNull() {
		int personAmount = event.getAttendees().size();
		event.addAttendees(null);
		assertEquals(personAmount, event.getAttendees().size());
	}
	
	@Test
	@DisplayName("Test que añade una lista con attendees")
	void testAddAttendees() {
		List<Attendee> attendees = new ArrayList<>();
		attendees.add(new Attendee());
		int result = event.getAttendees().size();
		event.addAttendees(attendees);
		assertEquals(result+1, event.getAttendees().size());
		
	}
	
	@Test
	@DisplayName("Test que comprueba una lista con un attendee repetio")
	void testAddAttendeesRepetive() {
		List<Attendee> attendees = new ArrayList<>();
		Attendee attendee = new Attendee();
		int result = event.getAttendees().size();
		attendees.add(attendee);
		attendees.add(attendee);
		event.addAttendees(attendees);
		assertEquals(result+1, event.getAttendees().size());

	}
	
	@Test
	@DisplayName("Test que añade los elementos cuando la lista es nula, inicializando y añadiendo en una nueva lista")
	void testAddAttendeesNullList() {
		List<Attendee> attendees = new ArrayList<>();
		attendees.add(new Attendee());
		event.setAttendees(null);
		event.addAttendees(attendees);
		assertEquals(1, event.getAttendees().size());
	}
	
	@Test
	@DisplayName("Test que comprueba que no hace nada cuando se le pasa un attendee null")
	void testRemoveAttendeeNull() {
		int personAmount = event.getAttendees().size();
		event.removeAttendee(null);
		assertEquals(personAmount, event.getAttendees().size());
	}
	
	@Test
	@DisplayName("Test para comprobar que se crea una lista, cuando esta es null")
	void testRemoveAttendeeNullList() {
		Attendee attendee = new Attendee();
		event.setAttendees(null);
		event.removeAttendee(attendee);
		assertEquals(0L, event.getAttendees().size());
	}
	
	@Test
	@DisplayName("Test que comprueba que se borra el attendee dado")
	void testRemoveAttendee() {
		Attendee attendee = new Attendee();
		event.addAttendee(attendee);
		int personAmount = event.getAttendees().size();
		event.removeAttendee(attendee);
		assertEquals(personAmount-1, event.getAttendees().size());
	}
	
	@Test
	@DisplayName("Test comprueba que el metodo no falla cuando se intenta borrar a alguien que no esta añadido")
	void testRemoveAttendeeNotExists() {
		Attendee attendee = new Attendee();
		int personAmount = event.getAttendees().size();
		event.removeAttendee(attendee);
		assertEquals(personAmount, event.getAttendees().size());
	}
}
