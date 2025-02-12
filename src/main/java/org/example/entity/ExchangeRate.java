package org.example.dto;

public class ExchangeRate {

    private int id;

    private int baseCurrency;

    private int targetCurrency;

    private int rate;

    public ExchangeRate(int id, int baseCurrency, int targetCurrency, int rate) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public int getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(int targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                ", rate=" + rate +
                '}';
    }
}
