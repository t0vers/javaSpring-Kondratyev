package org.example.module3;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.module3.domain.Contact;
import org.example.module3.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/contacts/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactsController {
    ContactService contactService;

    @GetMapping("getAll")
    public String getAll(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "main-contacts";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("contactDTO", new ContactDTO(null, null, null, null));
        return "add-update";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("contactDTO") ContactDTO contactDTO) {
        contactService.add(contactDTO);
        return "redirect:/api/contacts/getAll";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        Contact contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "add-update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("contact") Contact contact) {
        contactService.update(contact);
        return "redirect:/api/contacts/getAll";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute("id") long id) {
        contactService.delete(id);
        return "redirect:/api/contacts/getAll";
    }
}
