package solved.level22.lesson09.task02;

import java.util.*;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static void main(String[] args)
    {
        Map<String,String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);

        StringBuilder stringBuilder = getCondition(map);
        System.out.println(stringBuilder.toString());
    }

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        Iterator<Map.Entry<String,String>> itr = params.entrySet().iterator();
        int i=0;

        while (itr.hasNext())
        {
            Map.Entry<String,String> entry = itr.next();
            if(entry.getValue() != null)
            {
                if (i == 0)
                {
                    result.append(entry.getKey());
                    result.append(" = '");
                    result.append(entry.getValue());
                    result.append("'");
                    i++;
                } else
                {
                    result.append(" and ");
                    result.append(entry.getKey());
                    result.append(" = '");
                    result.append(entry.getValue());
                    result.append("'");
                }
            }
        }
        return result;
    }
}
