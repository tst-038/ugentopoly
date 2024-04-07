package be.ugent.objprog.ugentopoly.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Log {
    private final String message;
    private final String timestamp;

    protected Log(String message) {
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
