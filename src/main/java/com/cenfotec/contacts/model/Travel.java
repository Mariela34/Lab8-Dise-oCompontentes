package com.cenfotec.contacts.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="start_date")
    private LocalDate startdate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="destiny")
    private String destiny;

    @ManyToOne
    @JoinColumn(name="id_contact", nullable=false)
    private Contact contact;


    public Travel(Long id, LocalDate startdate, LocalDate endDate, String destiny) {
        this.id = id;
        this.startdate = startdate;
        this.endDate = endDate;
        this.destiny = destiny;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
