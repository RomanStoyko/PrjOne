package model.entities;

import model.util.Target;
import view.util.localization.Keys;
import view.util.localization.Localization;

import static model.util.CreditConditionConstance.*;

public class Credit {

    private final Bank bank;
    private final Target target;
    private final int percent;
    private final int month;
    private final int loanAmount;
    private final boolean prepay;
    private final boolean raise;

    private Credit(CreditBuilder creditBuilder){
        this.bank = creditBuilder.bank;
        this.target = creditBuilder.target;
        this.percent = creditBuilder.percent;
        this.month = creditBuilder.month;
        this.loanAmount = creditBuilder.loanAmount;
        this.prepay = creditBuilder.prepay;
        this.raise = creditBuilder.raise;
    }

    /**
     * Check credit for with condition credit
     * @param credit - condition credit
     * @param shortInput - check not all conditions
     * @return true if this firs condition form @param credit
     */
    public boolean fitsCondition(Credit credit, boolean shortInput){
        if(credit == null){ return  false;}

        if(!this.target.equals(credit.target)){ return  false;}
        if(!this.prepay == credit.prepay){ return  false;}
        if(!this.raise == credit.raise){ return  false;}

        if(!shortInput) {
            int percentMax = (credit.percent + PERCENT_CONDITION) > 100 ? 100 : credit.percent + PERCENT_CONDITION;
            int percentMin = (credit.percent - PERCENT_CONDITION) < 0 ? 0 : credit.percent - PERCENT_CONDITION;
            if (!(percentMin <= this.percent & this.percent <= percentMax)) {
                return false;
            }

            int monthMax = credit.month + MONTH_CONDITION;
            int monthMin = (credit.month - MONTH_CONDITION) < 0 ? 0 : credit.month - MONTH_CONDITION;
            if (!(monthMin <= this.month & this.month <= monthMax)) {
                return false;
            }

            int loanAmountDelta = (credit.loanAmount * LOAN_CONDITION / 100) + 1;
            int amountMax = credit.loanAmount + loanAmountDelta;
            int amountMin = (credit.loanAmount - loanAmountDelta) < 0 ? 0 : credit.loanAmount - loanAmountDelta;
            if (!(amountMin <= this.loanAmount & this.loanAmount <= amountMax)) {
                return false;
            }
        }

        return true;
    }

    public Bank getBank() {
        return bank;
    }

    public Target getTarget() {
        return target;
    }

    public int getPercent() {
        return percent;
    }

    public int getMonth() {
        return month;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public boolean isPrepay() {
        return prepay;
    }

    public boolean isRaise() {
        return raise;
    }

    @Override
    public String toString() {

        return "Credit{" + ",\n" +
                Localization.INSTANCE.getString(Keys.BANK) + bank + ",\n" +
                "\t" + Localization.INSTANCE.getString(Keys.TARGET) + Localization.INSTANCE.getString(target.name()) + ",\n" +
                "\t" + Localization.INSTANCE.getString(Keys.PERCENT) + percent +",\n" +
                "\t" + Localization.INSTANCE.getString(Keys.MONTH) + month + ",\n" +
                "\t" + Localization.INSTANCE.getString(Keys.LOAN) + loanAmount + "\n" +
                (prepay ? Localization.INSTANCE.getString(Keys.WITH_PREPAY)+ "\n":"")  +
                (raise ? Localization.INSTANCE.getString(Keys.WITH_RAISE)+ "\n":"")  +
                "}";
    }

    public static class CreditBuilder {
        private final Bank bank;
        private final Target target;
        private final int percent;
        private final int month;
        private final int loanAmount;
        private boolean prepay;
        private boolean raise;

        public CreditBuilder(Bank bank, Target target, int percent, int month, int loanAmount) {
            this.bank = bank;
            this.target = target;
            this.percent = percent;
            this.month = month;
            this.loanAmount = loanAmount;
        }

        public CreditBuilder( Target target, int percent, int month, int loanAmount) {
            this.bank = new Bank();
            this.target = target;
            this.percent = percent;
            this.month = month;
            this.loanAmount = loanAmount;
        }

        public CreditBuilder prepay(boolean prepay){
            this.prepay = prepay;
            return this;
        }

        public CreditBuilder raise(boolean raise){
            this.raise = raise;
            return this;
        }

        public Credit build(){
            return new Credit(this);
        }
    }

}
