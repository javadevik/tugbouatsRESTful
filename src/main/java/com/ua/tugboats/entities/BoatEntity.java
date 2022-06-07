package com.ua.tugboats.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.tugboats.models.BoatType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "boats")
public class BoatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private BoatType boatType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boat")
    private List<SailorEntity> sailors = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boat")
    private List<JourneyEntity> journeys;

    private boolean isBusy = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public List<SailorEntity> getSailors() {
        return sailors;
    }

    public void addToSailors(SailorEntity sailor) {
        this.sailors.add(sailor);
        sailor.setBoat(this);
    }

    public void clearSailors() {
        this.sailors.forEach(s -> s.setBoat(null));
        this.sailors.clear();
    }

    public List<JourneyEntity> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<JourneyEntity> journeys) {
        this.journeys = journeys;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setSailors(List<SailorEntity> sailors) {
        this.sailors = sailors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoatEntity boat = (BoatEntity) o;

        if (isBusy != boat.isBusy) return false;
        if (!id.equals(boat.id)) return false;
        if (!name.equals(boat.name)) return false;
        if (boatType != boat.boatType) return false;
        if (!Objects.equals(sailors, boat.sailors)) return false;
        return Objects.equals(journeys, boat.journeys);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + boatType.hashCode();
        result = 31 * result + (sailors != null ? sailors.hashCode() : 0);
        result = 31 * result + (journeys != null ? journeys.hashCode() : 0);
        result = 31 * result + (isBusy ? 1 : 0);
        return result;
    }
}
