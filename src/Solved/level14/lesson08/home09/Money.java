package solved.level14.lesson08.home09;

public abstract class Money
{
    private double amount;

    public Money()
    {
        this.amount = getAmount();
    }

    public double getAmount() { return amount; };

    public abstract String getCurrencyName();
}

