package ubb.ro.templatetry1.model;

import java.util.Date;

/**
 * Created by calin on 21.01.2017.
 */

public class Event {
    String id;
    String text;
    Date date;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
