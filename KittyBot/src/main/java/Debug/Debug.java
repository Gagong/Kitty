package Debug;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Debug {

    public static void p(String packet, String function, String text) {
        String time = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        System.err.println(time + " [" + packet + "] | <" + function + "> | " + text);
    }

    public static void message(String packet, String function, String text) {
        String time = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        System.out.println(time + " [" + packet + "] | <" + function + "> | " + text);
    }

    public static void b(String packet, String function, boolean bool) {
        String time = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        System.err.println(time + " [" + packet + "] | <" + function + "> | " + bool);
    }

    public static void s(String packet, String function, String[] string) {
        String time = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        System.err.println(time + " [" + packet + "] | <" + function + "> | " + string);
    }

    public static void d(String packet, String function, Date date) {
        String time = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        System.err.println(time + " [" + packet + "] | <" + function + "> | " + date);
    }
}
