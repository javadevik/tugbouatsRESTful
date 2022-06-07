package com.ua.tugboats.entities;

import com.ua.tugboats.models.CargoType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "journeys")
public class JourneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private BoatEntity boat;

    private Timestamp startDate;
    private Timestamp endDate;

    private String beginning;
    private String destination;

    @Enumerated(value = EnumType.STRING)
    private CargoType cargoType;

    private Double weight;

    @ManyToOne
    private CustomerEntity customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoatEntity getBoat() {
        return boat;
    }

    public void setBoat(BoatEntity boat) {
        this.boat = boat;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
