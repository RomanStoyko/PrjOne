package controller.util;

import view.View;
import view.util.localization.Localization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtility {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static String readValue;

    public static String inputStringValue(View view, String msg, String errorMsg, String string) {
        view.printMessage(msg);
        String sPattern = string;
        try {
            sPattern = Localization.INSTANCE.getString(string);
        }catch (Exception e){

        }
        Pattern pattern = Pattern.compile(sPattern);
        Matcher matcher;
        try {
            while ((readValue = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(readValue);
                if (matcher.find()) {
                    return readValue;
                } else {
                    view.printMessage(msg);
                    view.printMessage(errorMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int inputIntValue(View view, String msg, String errorMsg, String sPattern) {
        if(sPattern.isEmpty()){
            sPattern = "^[0-9]\\d*$";
        }
        while(true) {
            String string = inputStringValue(view, msg, errorMsg, sPattern);
            if(string != null) {
                try {
                    return Integer.valueOf(string);
                } catch (NumberFormatException e) {

                }
            }
        }
    }
}
