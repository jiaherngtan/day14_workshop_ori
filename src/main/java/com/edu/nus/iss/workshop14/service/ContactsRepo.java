package com.edu.nus.iss.workshop14.service;

import com.edu.nus.iss.workshop14.model.Contact;

public interface ContactsRepo {

    public void save(final Contact contact);

    public Contact findById(final String contactId);

}
