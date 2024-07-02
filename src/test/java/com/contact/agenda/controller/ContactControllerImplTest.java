package com.contact.agenda.controller;



import com.contact.agenda.entity.Contact;
import com.contact.agenda.exception.AlreadyExistException;
import com.contact.agenda.exception.NotFoundException;
import com.contact.agenda.service.interfaces.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact();
        contact.setId(UUID.randomUUID());
        contact.setName("John Doe");
        contact.setNumber("1234567890");
        contact.setMail("john.doe@example.com");
    }

    @Test
    void testSaveContact() throws Exception {
        Mockito.doNothing().when(contactService).save(Mockito.any(Contact.class));

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contact)))
                .andExpect(status().isCreated());

        Mockito.verify(contactService, Mockito.times(1)).save(Mockito.any(Contact.class));
    }

    @Test
    void testSaveContactAlreadyExists() throws Exception {
        Mockito.doThrow(new AlreadyExistException("Already Exist a contact with name: " + contact.getName()))
                .when(contactService).save(Mockito.any(Contact.class));

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contact)))
                .andExpect(status().isConflict());

        Mockito.verify(contactService, Mockito.times(1)).save(Mockito.any(Contact.class));
    }

    @Test
    void testGetContactById() throws Exception {
        Mockito.when(contactService.findById(Mockito.anyString())).thenReturn(contact);

        mockMvc.perform(get("/api/contacts/{uuid}", contact.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.number").value("1234567890"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        Mockito.verify(contactService, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    void testGetContactByIdNotFound() throws Exception {
        Mockito.when(contactService.findById(Mockito.anyString()))
                .thenThrow(new NotFoundException("Not Found Contact with id: " + contact.getId()));

        mockMvc.perform(get("/api/contacts/{uuid}", contact.getId().toString()))
                .andExpect(status().isNotFound());

        Mockito.verify(contactService, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    void testGetAllContacts() throws Exception {
        List<Contact> contacts = Collections.singletonList(contact);
        Mockito.when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].number").value("1234567890"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

        Mockito.verify(contactService, Mockito.times(1)).getAllContacts();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
