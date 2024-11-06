package org.example.module3.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.module3.ContactDTO;
import org.example.module3.domain.Contact;
import org.example.module3.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ContactService {
    ContactRepository contactRepository;

    public List<Contact> getAll(){
        return contactRepository.findAll();
    }

    public void add(ContactDTO contactDTO) {
        contactRepository.add(
                contactDTO.firstName(),
                contactDTO.secondName(),
                contactDTO.email(),
                contactDTO.phoneNumber());
    }

    public void update(Contact contactDTO) {
        contactRepository.update(
                contactDTO.getId(),
                contactDTO.getFirstName(),
                contactDTO.getSecondName(),
                contactDTO.getEmail(),
                contactDTO.getPhoneNumber());
    }

    public void delete(long id) {
        contactRepository.delete(id);
    }

    public Contact getById(long id) {
        return contactRepository.getById(id);
    }
}
