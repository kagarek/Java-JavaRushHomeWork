package solved.level26.lesson15.big01;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by igormakarychev on 6/1/15.
 */
public final class CurrencyManipulatorFactory
{
    private CurrencyManipulatorFactory(){}

    public static Collection<CurrencyManipulator> map = new ArrayList<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        CurrencyManipulator m = null;

        for (CurrencyManipulator currencyManipulator : map)
        {
            if (currencyManipulator.getCurrencyCode().equals(currencyCode))
             return currencyManipulator;
        }

        m = new CurrencyManipulator(currencyCode);
        map.add(m);

        return m;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return map;
    }
}
