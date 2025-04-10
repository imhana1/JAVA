package com.example.mini_03.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactDao cDao;

    public int save(Contact contact){
        cDao.save(contact);
        return contact.getCno();
    }

    public List<Contact> findAll() {
        return cDao.findAll();
    }

    public Contact findById(Integer cno) {
        return cDao.findByCno(cno);
    }

    public boolean update(Contact contact) {
        return cDao.update(contact) == 1;
    }

    public boolean delete(Integer cno) {
        return cDao.delete(cno) == 1;
    }
}
