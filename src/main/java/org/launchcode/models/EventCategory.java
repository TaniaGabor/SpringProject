package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity{

    @Size(min = 3, message = "name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "category")
    private final List<Event> events = new ArrayList<>();

    public EventCategory(@Size(min = 3, message = "name must be at least 3 characters long") String name){
        this.name = name;
    }

    public EventCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}