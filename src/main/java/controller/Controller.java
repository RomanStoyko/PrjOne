package controller;

import controller.util.InputUtility;
import model.Model;
import model.db.Credits;
import model.entities.Bank;
import model.entities.Credit;
import model.util.Target;
import view.View;
import view.util.localization.Localization;


import java.util.ArrayList;
import java.util.Locale;

import static model.util.CreditConditionConstance.*;

import static view.util.localization.Keys.*;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void work() {
        chengeLocalization();

        view.printMessage(WELCOME);
        while(true){
            view.printMessage(DESCRIPTION);

            boolean shortInput = getBoolean(Localization.INSTANCE.getString(SHORT_SEARCH_DESCRIPTION )+ "\n" + Localization.INSTANCE.getString(YES_NO_QUESTION));

            Target target = getTargetById();

            int persent = 0;
            int month = 0;
            int loan = 0;

            if (!shortInput) {

                persent = InputUtility.inputIntValue(
                        view,
                        String.format(Localization.INSTANCE.getString(PERCENT_INPUT), PERCENT_CONDITION),
                        WRONG_INPUT_DATA,
                        REG_PERCENT);

                month = InputUtility.inputIntValue(
                        view,
                        String.format(Localization.INSTANCE.getString(MONTH_INPUT), MONTH_CONDITION),
                        WRONG_INPUT_DATA,
                        REG_MONTH);

                loan = InputUtility.inputIntValue(
                        view,
                        String.format(Localization.INSTANCE.getString(LOAN_INPUT), LOAN_CONDITION),
                        WRONG_INPUT_DATA,
                        REG_LOAN);
            }

            boolean prepay = getBoolean(Localization.INSTANCE.getString(PREPAY_INPUT)+ "\n" + Localization.INSTANCE.getString(YES_NO_QUESTION));
            boolean raise = getBoolean(Localization.INSTANCE.getString(RAISE_INPUT)+ "\n" + Localization.INSTANCE.getString(YES_NO_QUESTION));

            Credit searchCredit = new Credit.CreditBuilder(target,persent,month,loan).prepay(prepay).raise(raise).build();

            ArrayList<Credit> fitCreditList = new ArrayList<Credit>();
            for (Credit credit: Credits.INSTANCE.getCreditList()) {
                if (credit.fitsCondition(searchCredit,shortInput)) {
                        fitCreditList.add(credit);
                }
            }

            Credit credit = selectCredit(fitCreditList);

            if(credit != null){
                view.printMessage(
                        String.format(
                                Localization.INSTANCE.getString(U_SELECT),
                                credit.getBank(),
                                credit.getPercent(),
                                credit.getLoanAmount(),
                                credit.getMonth()
                        )
                );
                if(credit.isPrepay()){
                    view.printMessage(WITH_PREPAY);
                }
                if(credit.isRaise()){
                    view.printMessage(WITH_RAISE);
                }
                view.printMessage(TNX);
                break;
            }

            boolean continueInput = getBoolean(Localization.INSTANCE.getString(CONTINUE)+ "\n" + Localization.INSTANCE.getString(YES_NO_QUESTION));
            if(!continueInput){
                view.printMessage(TNX);
                break;
            }
        }
    }


    private Credit selectCredit( ArrayList<Credit> creditList){
        if(creditList.size() == 0) {
            view.printMessage(NOTHING_TO_SELECT);
            return null;
        }

        while (true) {
            view.printMessage(CREDIT_SELECT);
            for (int i = 1; i <= creditList.size(); i++){
                view.printMessage(String.format("%2d: %s",i,creditList.get(i-1)));
            }
            int position = InputUtility.inputIntValue(
                    view,
                    INPUT_NUMBER,
                    WRONG_INPUT_DATA,
                    "");
            try{
                if(position == 0){return null;}
                return  creditList.get(position-1);
            }catch (Exception e){

            }
        }
    }




    private Target getTargetById() {

        while (true){
            view.printMessage(TARGET_SELECT);
            Target[] targets = Target.values();
            for (int i = 1; i <= targets.length; i++){
                view.printMessage(String.format("%2d: %s",i,Localization.INSTANCE.getString(targets[i-1].name())));
            }
            int position = InputUtility.inputIntValue(
                    view,
                    INPUT_NUMBER,
                    WRONG_INPUT_DATA,
                    "");
            try{
                Target target = targets[position-1];
                return target;
            }catch (Exception e){

            }
        }
    }

    private boolean getBoolean(String msg){
        return  InputUtility.inputIntValue(
                view,
                msg,
                WRONG_INPUT_DATA,
                "[01]") == 1;
    }

    private void chengeLocalization() {

        int leng = InputUtility.inputIntValue(
                view,
                LANG_MENU,
                WRONG_INPUT_DATA,
                "[123]");

        Locale locale = model.getLocale(leng);
        Localization.INSTANCE.changeResource(locale);
        view.printMessage(LENG_MENU_FINISH);
    }
}
