package com.cenfotec.contacts.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cenfotec.contacts.model.Travel;
import com.cenfotec.contacts.repository.TravelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cenfotec.contacts.model.Contact;
import com.cenfotec.contacts.repository.ContactRepository;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping({ "/contacts" })
public class ContactController {

	private ContactRepository repository;
	private TravelRepository repoTravel;

	ContactController(ContactRepository contactRepository, TravelRepository travelRepository) {
		this.repository = contactRepository;
		this.repoTravel = travelRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Contact> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Contact create(@RequestBody Contact contact) {
		return repository.save(contact);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact) {
		return repository.findById(id).map(record -> {
			record.setName(contact.getName());
			record.setEmail(contact.getEmail());
			record.setPhone(contact.getPhone());
			Contact updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}


	@PostMapping(path = {"/travels/{id}"})
	public Travel addTravel(@PathVariable("id") long id, @RequestBody Travel travel) {
		Contact c = repository.getOne(id);
		if (c!=null){
			travel.setContact(c);
			repoTravel.save(travel);
			c.getTravels().add(travel);
			repository.save(c);
			Travel t = new Travel(travel.getId(), travel.getStartdate(), travel.getEndDate(),
					travel.getDestiny());
			t.setContact(new Contact(c.getId(), c.getName(), c.getEmail(), c.getPhone()));

			return t;
		}
		return null;
	}


	@GetMapping(path = {"/travels/{id}"})
	public List getTravels(@PathVariable("id") long id) {
		Contact c = repository.getOne(id);
		List<Travel> travels = new ArrayList<>();
		if (c!=null){
				for(Travel t : c.getTravels()){
					Travel tTemp = new Travel(t.getId(), t.getStartdate(),
							t.getEndDate(),	t.getDestiny());
					tTemp.setContact(new Contact(c.getId(), c.getName(), c.getEmail(),
							c.getPhone()));
					travels.add(tTemp);
				}
			return travels;
		}
		return null;
	}

	@GetMapping(path = {"/pagination"})
	public Page<Contact> getPaginationContacts(@RequestParam Optional<Integer> page,
											   @RequestParam Optional<Integer> size,
											   @RequestParam Optional<String> sort) {

		Pageable p = PageRequest.of(page.get(), size.get());
		Page<Contact> contacts = repository.findAll(p);

			for (Contact c: repository.findAll()){
				for(Travel t: c.getTravels()){
					t.setContact(null);
				}

			}

		return contacts;
	}

}
