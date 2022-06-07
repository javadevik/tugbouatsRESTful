package com.ua.tugboats.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.tugboats.models.SailorType;

import javax.persistence.*;

@Entity
@Table(name = "sailors")
public class SailorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private SailorType sailorType;
    @JsonIgnore
    @ManyToOne
    private BoatEntity boat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SailorType getSailorType() {
        return sailorType;
    }

    public void setSailorType(SailorType sailorType) {
        this.sailorType = sailorType;
    }

    public BoatEntity getBoat() {
        return boat;
    }

    public void setBoat(BoatEntity boat) {
        this.boat = boat;
    }

}
