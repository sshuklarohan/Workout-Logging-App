package model;

import java.util.Calendar;
import java.util.Date;

//Represents a single event that could occur within the model
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;


    //EFFECTS: Creates an event with given description and stored the current date and time
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: Returns date of event
    public Date getDate() {
        return dateLogged;
    }


    //EFFECTS: Returns description of event
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }


        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                &&
                this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}


