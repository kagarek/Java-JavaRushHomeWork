package solved.level28.lesson15.big01.model;

import solved.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igormakarychev on 8/15/15.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/600.7.12 (KHTML, like Gecko) Version/8.0.7 Safari/600.7.12";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document doc = null;
        try
        {
            int page = 0;

            while (true)
            {
                doc = getDocument(searchString, page);

                List<Element> elementList = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

                if (elementList != null && elementList.size() > 0)
                {
                    for (Element element : elementList)
                    {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                        vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                        vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                        vacancy.setSiteName(doc.title());

                        if (!element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").isEmpty())
                            vacancy.setSalary(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                        else
                            vacancy.setSalary(new String(""));

                        vacancyList.add(vacancy);
                    }
                }

                if (doc.getElementsByAttributeValue("data-qa","pager-next").size()==0) break;
                else page++;
            }
        }
        catch (Exception e) { e.printStackTrace();}
        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT,searchString,page)).userAgent(userAgent).referrer("http://hh.ua").timeout(timeout).get();
    }
}
