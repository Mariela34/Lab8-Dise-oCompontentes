package com.cenfotec.contacts.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String name;
	 private String email;
	 private String phone;

	 @OneToMany(fetch=FetchType.LAZY, mappedBy="contact")
	 private List<Travel> travels;



	public Contact(Long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.travels = new ArrayList<>();
	}
	public Contact(Long id) {
		this.id = id;
		this.name = null;
		this.email = null;
		this.phone = null;
		this.travels = new ArrayList<>();
	}


	public Long getId() {
		 return this.id;
	 }
	 
	 public String getName() {
		 return this.name;
	 }
	 
	 public String getEmail() {
		 return this.email;
	 }
	 
	 public String getPhone() {
		 return this.phone;
	 }
	 
	 public void setId(Long id) {
		 this.id = id;
	 }
	 
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 
	 public void setPhone(String phone){
	 	this.phone = phone;
	 }

	public List<Travel> getTravels() {
		return travels;
	}

	public void setTravels(List<Travel> travels) {
		this.travels = travels;
	}

}
