package co.com.merdadolibre.challenge.util;

import java.util.UUID;

public class Utilities {

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}