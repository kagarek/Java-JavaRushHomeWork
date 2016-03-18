package solved.level26.lesson15.big01;

import solved.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by igormakarychev on 6/1/15.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        denominations.put(denomination,count);
    }

    public int getTotalAmount()
    {
        int sum = 0;
        for(Map.Entry<Integer,Integer> entry : denominations.entrySet())
        {
            sum = sum + (entry.getKey() * entry.getValue());
        }
        return sum;
    }

    public boolean hasMoney()
    {
        boolean result = false;

        for(Map.Entry<Integer, Integer> pair : denominations.entrySet()){
            if(pair.getValue() != 0){
                result = true;
            }
        }
        return result;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        if (expectedAmount <= getTotalAmount()) return true;
        else return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        int restAmount = expectedAmount;
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());


        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(denominations.keySet());
        Collections.sort(list);

        for (int i = list.size()-1; i >= 0; i--)
        {
            int currentNominal = list.get(i);
            int currentBanknotCount = 0;

            result.put(currentNominal, 0);

            while (restAmount >= currentNominal && (denominations.get(currentNominal) - result.get(currentNominal) > 0))
            {
                restAmount = restAmount - currentNominal;
                currentBanknotCount++;
                result.put(currentNominal,currentBanknotCount);
            }

            if (result.get(currentNominal)==0)
                result.remove(currentNominal);
        }

        if (restAmount>0)
            throw new NotEnoughMoneyException();
        else
            for (Map.Entry<Integer,Integer> mapEntry : result.entrySet())
                denominations.put(mapEntry.getKey(),denominations.get(mapEntry.getKey()) - mapEntry.getValue());

        return result;
    }
}
