package view;

import view.util.localization.Localization;

public class View {

    public void printMessage(String string){

        try {
            System.out.println(Localization.INSTANCE.getString(string));
        }catch (Exception e){
            System.out.println(string);
        }

    }
}
