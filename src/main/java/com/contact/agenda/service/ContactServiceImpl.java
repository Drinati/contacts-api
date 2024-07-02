package com.contact.agenda.service;

import com.contact.agenda.entity.Contact;
import com.contact.agenda.exception.AlreadyExistException;
import com.contact.agenda.exception.NotFoundException;
import com.contact.agenda.repository.ContactRepository;
import com.contact.agenda.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public void save(Contact contact) {
        UUID id = UUID.randomUUID();
        contact.setId(id);
        if (findByName(contact.getName()) != null) {
            throw new AlreadyExistException("Already Exist a contact with name: " + contact.getName());
        }
        contactRepository.save(contact);
    }

    public Contact findByName(String name) {
        return contactRepository.findByName(name);
    }

    @Override
    public Contact findById(String uuid) {
        UUID id = UUID.fromString(uuid);
        Optional<Contact> contact = contactRepository.findById(id);
        if (!contact.isPresent()) {
            throw new NotFoundException("Not Found Contact with id: " + id);
        }

        return contact.get();
    }

    @Override
    public void delete(String uuid) {
        UUID id = UUID.fromString(uuid);
        findById(uuid);
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> getAllContacts() {
        Iterable<Contact> contacts = contactRepository.findAll();
        List<Contact> contactList = new ArrayList<>();
        contacts.forEach(contactList::add);
        return contactList;
    }
}
