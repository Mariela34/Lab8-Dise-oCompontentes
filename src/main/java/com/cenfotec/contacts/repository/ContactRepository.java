package com.cenfotec.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cenfotec.contacts.model.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {

}
