package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//Singleton pattern, stores all events that occur in the model
public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;


    //EFFECTS: creates an empty list to store events
    private EventLog() {
        events = new ArrayList<>();
    }



    //EFFECTS: gets the instance of event log if it already exists,
    //         creates an instance if it doesn't exist(singleton)
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }


    //MODIFIES: this
    //EFFECTS: Adds an event to event log
    public void logEvent(Event e) {
        events.add(e);
    }


    //EFFECTS: Clears event log, logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}



