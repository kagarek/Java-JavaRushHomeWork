package solved.level28.lesson15.big01.model;

import solved.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Makarychev on 03.11.2015.
 */
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/600.7.12 (KHTML, like Gecko) Version/8.0.7 Safari/600.7.12";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancyList = new ArrayList<>();

        try
        {
            int page = 1;

            while (true)
            {
                Document doc = getDocument(searchString, page);
                List<Element> elementList = doc.getElementsByClass("job");

                if (elementList == null || elementList.size() == 0) break;

                for (Element element : elementList)
                {
                    Vacancy vacancy = new Vacancy();

                    vacancy.setTitle(element.getElementsByClass("title").text());
                    vacancy.setCity(element.getElementsByClass("location").select("a").text());
                    vacancy.setCompanyName(element.getElementsByClass("company_name").select("a").text());
                    vacancy.setUrl("http://moikrug.ru"+element.getElementsByClass("title").select("a").attr("href"));
                    vacancy.setSiteName(doc.title());
                    vacancy.setSalary(element.getElementsByClass("count").text());

                    vacancyList.add(vacancy);
                }

                page++;
            }
        }
        catch (IOException io) {}

        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(userAgent)
                .referrer("http://google.com/")
                .get();
    }
}
