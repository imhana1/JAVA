package com.example.mini_03;

import com.example.mini_03.contact.Contact;
import com.example.mini_03.contact.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class ContactServiceTest {
    @Autowired
    private ContactService cServ;

    // @Test
    public void saveTest() {
        Contact contact = new Contact(null, "ㅇㅇㅇ", "인천", "010-0000-0000");
        System.out.println(contact);

        cServ.save(contact);
        System.out.println(contact);
    }

    // @Test
    public void findAllTest() {
        List<Contact> contacts = cServ.findAll();
        System.out.println(contacts);
        assertNotEquals(0, contacts.size());
    }

    // @Transactional
    // @Test
    public void updateTest() {
        Contact contact = new Contact(1, "변경", "ㄱㄱㄴㄴ", "0000");
        assertEquals(true, cServ.update(contact));
    }

    // @Test
    public void delteTest() {
        assertEquals(true, cServ.delete(1));
    }
}

























