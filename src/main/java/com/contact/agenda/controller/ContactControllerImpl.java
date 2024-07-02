package com.contact.agenda.controller;

import com.contact.agenda.controller.interfaces.ContactController;
import com.contact.agenda.entity.Contact;
import com.contact.agenda.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contacts")
public class ContactControllerImpl implements ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void saveContact(@RequestBody Contact contact) {
        contactService.save(contact);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Contact getContactById(@PathVariable String uuid) {
        return contactService.findById(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteContact(@PathVariable String uuid) {
        contactService.delete(uuid);
    }



}