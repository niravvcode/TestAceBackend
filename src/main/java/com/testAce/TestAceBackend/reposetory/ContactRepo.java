package com.testAce.TestAceBackend.reposetory;

import com.testAce.TestAceBackend.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
}
