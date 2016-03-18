package solved.level19.lesson03.task03;

/* Адаптация нескольких интерфейсов
Адаптировать IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static
    {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static void main(String[] args)
    {
        class iData implements IncomeData
        {
            String countryCode = "UA";
            String company = "JavaRush Ltd.";
            String firstName = "Ivan";
            String lastName = "Ivanov";
            int phoneCode = 38;
            int numPhone = 501234567;

            @Override
            public String getCountryCode()
            {
                return countryCode;
            }

            @Override
            public String getCompany()
            {
                return company;
            }

            @Override
            public String getContactFirstName()
            {
                return firstName;
            }

            @Override
            public String getContactLastName()
            {
                return lastName;
            }

            @Override
            public int getCountryPhoneCode()
            {
                return phoneCode;
            }

            @Override
            public int getPhoneNumber()
            {
                return numPhone;
            }
        }

        IncomeDataAdapter ap = new IncomeDataAdapter(new iData());
        System.out.println(ap.getName());
        System.out.println(ap.getCompanyName());
        System.out.println(ap.getCountryName());
        System.out.println(ap.getPhoneNumber());
    }

    public static class IncomeDataAdapter implements Customer,Contact {

        private IncomeData incomeData;

        public IncomeDataAdapter(IncomeData incomeData)
        {
            this.incomeData = incomeData;
        }

        @Override
        public String getName()
        {
            return incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber()
        {

            String phone = incomeData.getPhoneNumber()+"";

            for (int i = phone.length(); i < 10; i++)
                phone = "0" + phone;

            String formattedPhone =  "+"
                    + incomeData.getCountryPhoneCode()
                    + "("
                    + phone.substring(0,3)
                    + ")"
                    + phone.substring(3,6)
                    + "-"
                    + phone.substring(6,8)
                    + "-"
                    + phone.substring(8,10);

            return formattedPhone;
        }

        @Override
        public String getCompanyName()
        {
            return incomeData.getCompany();
        }

        @Override
        public String getCountryName()
        {
            return countries.get(incomeData.getCountryCode());
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        int getCountryPhoneCode();      //example 38
        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}