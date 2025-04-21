package com.testAce.TestAceBackend.services;

import com.testAce.TestAceBackend.Entity.Contact;
import com.testAce.TestAceBackend.reposetory.ContactRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepo contactRepo;

    public Contact saveContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }
}
