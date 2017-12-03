package model;

import java.util.Locale;

public class Model {

    public Locale getLocale(int command) {
        switch (command) {
            case 3:
                return new Locale("en", "GB");
            case 2:
                return new Locale("uk", "UA");
            default:
                return Locale.getDefault();
        }
    }
}
