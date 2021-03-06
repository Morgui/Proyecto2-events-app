package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.service.EventNotificationServiceImpl;

@ExtendWith(MockitoExtension.class)
class EventTest {

	@Mock
	EventNotificationServiceImpl eventNotificationService;

	@InjectMocks // inyectar los mocks dependencia dentro de la clase
	Event evento;

	Event event;
	Attendee attendee;

	@BeforeEach
	void setUp() throws Exception {
		event = new Event(1L, "PyCon", EventType.TECH, new EventNotificationServiceImpl());
		attendee = new Attendee(1L, "morgui", "morgui@email.com");
	}

	@Test
	@DisplayName("Test con un attendee null")
	void testAddAttendeeNull() {
		Event event2 = new Event();
		int personAmount = event2.getAttendees().size();
		event2.addAttendee(null);
		assertEquals(personAmount, event2.getAttendees().size());

	}

	@Test
	@DisplayName("Test que añade un attendee que no esté en la lista")
	void testAddAttendeeExists() {
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());

	}

	@Test
	@DisplayName("Test que comprueba si el attendee ya esta en la lista")
	void testAddAttendeeRepeate() {
		event.addAttendee(attendee);
		event.addAttendee(attendee);
		assertEquals(1, event.getAttendees().size());

	}

	@Test
	@DisplayName("Test que comprueba el añadir un attendee cuya lista es nula")
	void testAddAttendeeNullList() {
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
		attendees.add(new Attendee(2L, "javero", "javero@email.com"));
		int result = event.getAttendees().size();
		event.addAttendees(attendees);
		assertEquals(result + 1, event.getAttendees().size());

	}

	@Test
	@DisplayName("Test que comprueba una lista con un attendee repetio")
	void testAddAttendeesRepetive() {
		List<Attendee> attendees = new ArrayList<>();
		int result = event.getAttendees().size();
		attendees.add(attendee);
		attendees.add(attendee);
		event.addAttendees(attendees);
		assertEquals(result + 1, event.getAttendees().size());

	}

	@Test
	@DisplayName("Test que añade los elementos cuando la lista es nula, inicializando y añadiendo en una nueva lista")
	void testAddAttendeesNullList() {
		List<Attendee> attendees = new ArrayList<>();
		attendees.add(new Attendee(3L, "Redfury", "redfury@redfury.com"));
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
		event.addAttendee(attendee);
		int personAmount = event.getAttendees().size();
		event.removeAttendee(attendee);
		assertEquals(personAmount - 1, event.getAttendees().size());
	}

	@Test
	@DisplayName("Test comprueba que el metodo no falla cuando se intenta borrar a alguien que no esta añadido")
	void testRemoveAttendeeNotExists() {
		int personAmount = event.getAttendees().size();
		event.removeAttendee(attendee);
		assertEquals(personAmount, event.getAttendees().size());
	}

	@Test
	@DisplayName("Test que al pasar un null al metodo no borra los elementos de la lista de attendee")
	void testRemoveAttendeesNull() {
		int personAmount = event.getAttendees().size();
		event.removeAttendees(null);
		assertEquals(personAmount, event.getAttendees().size());
	}

	@Test
	@DisplayName("Test que comprueba que se crea una lista nueva vacia cuando se borra una null")
	void testRemoveAttendeesNullList() {
		List<Attendee> attendees = new ArrayList<>();
		event.setAttendees(null);
		event.removeAttendees(attendees);
		assertEquals(0, event.getAttendees().size());
	}

	@Test
	@DisplayName("Test que comprueba que se borra la lista dada")
	void testRemoveAttendees() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		Attendee attendee = new Attendee(1L, "patatita", "patita@example.com");
		attendees.add(attendee);
		event.addAttendees(attendees);
		int result = event.getAttendees().size();
		event.removeAttendees(attendees);
		assertEquals(result - 1, event.getAttendees().size());
	}

	@Test
	@DisplayName("Test que comprueba que no borra nada cuando se le pasa una lista no registrada")
	void testRemoveAttendeesNotExists() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		attendees.add(attendee);
		int result = event.getAttendees().size();
		event.removeAttendees(attendees);
		assertEquals(result, event.getAttendees().size());
	}

	/*
	 * Metodo notifyAssistans testeado usando un mock (Mockito) para la dependencia EventNotificationService usada en Event
	 */
	@Test
	@DisplayName("Test notifyAssistants with Mock")
	void testNotifyAssistants() {
		evento.notifyAssistants();
		verify(eventNotificationService, times(1)).announce(evento);
	}

	@Test
	@DisplayName("Test que comprueba que la lista de speakers no cambia al pasarle un null")
	void testAddSpeakerNull() {
		int speakerAmount = event.getSpeakers().size();
		event.addSpeaker(null);
		assertEquals(speakerAmount, event.getSpeakers().size());
	}

	@Test
	@DisplayName("Test que comprueba que añade el speaker correctamente")
	void testAddSpeaker() {
		Speaker speaker = new Speaker(1L, "Gopher", "Go");
		int speakerAmount = event.getSpeakers().size();
		event.addSpeaker(speaker);
		assertEquals(speakerAmount + 1, event.getSpeakers().size());
	}

	@Test
	@DisplayName("Test que comprueba que no hace nada cuando se le pasa un speaker null")
	void testRemoveSpeakerNull() {
		int result = event.getSpeakers().size();
		event.removeSpeaker(null);
		assertEquals(result, event.getSpeakers().size());

	}

	@Test
	@DisplayName("Test que comprueba que borra el speaker correctamente")
	void testRemoveSpeaker() {
		Speaker speaker = new Speaker(2L, "Octocat", "GitHub");
		event.addSpeaker(speaker);
		int result = event.getSpeakers().size();
		event.removeSpeaker(speaker);
		assertEquals(result-1, event.getSpeakers().size());
	}
	
	@Test
	@DisplayName("Test que comprueba el cambio correcto del Id")
	void testSetId() {
		event.setId(2L);
		assertEquals(2L, event.getId());
	}
	
	@Test
	@DisplayName("Test que comprueba el cambio correcto del Title")
	void testSetTitle() {
		event.setTitle("JavaWorld");
		assertEquals("JavaWorld", event.getTitle());
		}

	@Test
	@DisplayName("Test que comprueba el cambio correcto del Type")
	void testSetType() {
		event.setType(EventType.BUSINESS);
		assertEquals(EventType.BUSINESS, event.getType());	
		}
	
	@Test
	@DisplayName("Test que comprueba el cambio correcto de lista de speakers por lo dada")
	void testSetSpeakers() {
		List<Speaker> speakers = new ArrayList<Speaker>();
		Speaker speaker = new Speaker(3L, "Test", "Jenkins");
		speakers.add(speaker);
		event.setSpeakers(speakers);
		assertEquals(speaker, event.getSpeakers().get(0));
		assertEquals(1, event.getSpeakers().size());
	}
}

