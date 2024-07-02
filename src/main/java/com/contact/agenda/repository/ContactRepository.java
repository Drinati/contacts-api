package com.contact.agenda.repository;

import com.contact.agenda.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends CrudRepository<Contact, UUID> {


    Contact findByName(String name);
}
