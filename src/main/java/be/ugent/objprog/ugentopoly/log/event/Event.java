package be.ugent.objprog.ugentopoly.log.event;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Event {
    private final String message;
    private final String timestamp;

    protected Event(String message) {
        this.message = message;
        this.timestamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
