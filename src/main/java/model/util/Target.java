package model.util;

public enum Target {
    CAR(15,35,3,120,50000,1000000000),
    HOUSE(10,40,120,600,500000,2000000000),
    TRAVEL(5,30,3,12,10000,250000),
    APPLIANCE(1,10,3,12,3500,40000),
    PC(1,20,3,12,5000,50000);

    private int minMonth;
    private int maxMonth;
    private int minPercent;
    private int maxPercent;
    private int minLoan;
    private int maxLoan;

    private Target( int minPercent, int maxPercent, int minMonth, int maxMonth, int minLoan, int maxLoan) {
        this.minMonth = minMonth;
        this.maxMonth = maxMonth;
        this.minPercent = minPercent;
        this.maxPercent = maxPercent;
        this.minLoan = minLoan;
        this.maxLoan = maxLoan;
    }

    public int getMinMonth() {
        return minMonth;
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public int getMinPercent() {
        return minPercent;
    }

    public int getMaxPercent() {
        return maxPercent;
    }

    public int getMinLoan() {
        return minLoan;
    }

    public int getMaxLoan() {
        return maxLoan;
    }
}
