package view.util.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Localization {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private final String resourseName = "localization.text";

    private Localization(){
        resourceBundle = ResourceBundle.getBundle(
                resourseName, new Locale("en","GB"));
    }

    public void changeResource(Locale locale){
        resourceBundle = ResourceBundle.getBundle(
                resourseName, locale);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }

}
