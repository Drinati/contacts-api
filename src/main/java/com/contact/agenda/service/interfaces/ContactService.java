package com.contact.agenda.service.interfaces;

import com.contact.agenda.entity.Contact;

import java.util.List;

public interface ContactService {

    void save(Contact contact);

    Contact findById(String uuid);

    void delete(String uuid);

    List<Contact> getAllContacts();

    Contact findByName(String name);


}
