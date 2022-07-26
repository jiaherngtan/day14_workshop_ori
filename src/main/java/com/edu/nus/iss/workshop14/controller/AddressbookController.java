package com.edu.nus.iss.workshop14.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.nus.iss.workshop14.model.Contact;
import com.edu.nus.iss.workshop14.service.ContactsRedis;

@Controller
public class AddressbookController {

    private static final Logger logger = LoggerFactory.getLogger(AddressbookController.class);

    @Autowired
    ContactsRedis contactsRedis;

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, Model model) {
        // Contact c = new Contact(contact.getName(), contact.getEmail(),
        // contact.getPhoneNumber());
        logger.info("|| contact id is " + contact.getId() + " ||");
        contactsRedis.save(contact);
        model.addAttribute("contact", contact);
        return "showContact";
    }

    @GetMapping("/contact/{contactId}")
    public String getContactById(Model model, @PathVariable(value = "contactId") String contactId) {
        Contact cc = contactsRedis.findById(contactId);
        model.addAttribute("contact", cc);
        return "showContact";
    }

}
