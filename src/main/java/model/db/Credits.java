package model.db;

import model.entities.Bank;
import model.entities.Credit;
import model.util.Target;


import java.util.LinkedList;
import java.util.Random;

public enum Credits {
    INSTANCE;
    private LinkedList<Credit> creditList = new LinkedList<Credit>();

    private Credits(){
        Bank[] banks = new Bank[5];
        banks[0] = new Bank(300023,"Укрсоцбанк");
        banks[1] = new Bank(300335,"Райффайзен Банк Аваль");
        banks[2] = new Bank(305299,"Приватбанк");
        banks[3] = new Bank(320478,"Укргазбанк");
        banks[4] = new Bank(334851,"ПУМБ");

        for (int i = 0; i < 30 + (int)(Math.random()*100); i++) {
            Bank bank = banks[getRandomInRange(banks.length-1)];
            Target target = Target.values()[getRandomInRange( Target.values().length-1)];
            int percent = getRandomInRange(target.getMinPercent(),target.getMaxPercent());
            int month = getRandomInRange(target.getMinMonth(),target.getMaxMonth());
            int loan = (getRandomInRange(target.getMinLoan(),target.getMaxLoan())/100)*100;
            boolean  prepay = getRandomInRange(2) == 0 ;
            boolean raise = getRandomInRange(2) == 0 ;
            creditList.add(new Credit.CreditBuilder(bank,target,percent,month,loan).prepay(prepay).raise(raise).build());
        }

    }

    public LinkedList<Credit> getCreditList(){
        return creditList;
    }

    private int getRandomInRange(int min,int max){
        Random random = new Random();
        return  min + random.nextInt(max - min + 1);
    }

    private int getRandomInRange(int max){
        return getRandomInRange(0,max);
    }
}
