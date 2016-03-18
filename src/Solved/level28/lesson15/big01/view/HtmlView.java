package solved.level28.lesson15.big01.view;

import solved.level28.lesson15.big01.Controller;
import solved.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

/**
 * Created by igormakarychev on 8/26/15.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath = "./src/"+ this.getClass().getPackage().getName().replace(".","/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try {
            String newVacanciesFileHtml = getUpdatedFileContent(vacancies);
            updateFile(newVacanciesFileHtml);
        } catch (IOException e){
            System.out.println("Some exception occurred");
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Dnepropetrovsk");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document document = getDocument();

        Element element = document.getElementsByClass("template").first();
        Element template = element.clone();
        template.removeClass("template");
        template.removeAttr("style");
        document.getElementsByAttributeValue("class","vacancy").remove();

        for (Vacancy v : vacancies)
        {
            Element newVacancy = template.clone();
            newVacancy.getElementsByClass("city").first().text(v.getCity());
            newVacancy.getElementsByClass("companyName").first().text(v.getCompanyName());
            newVacancy.getElementsByClass("salary").first().text(v.getSalary());
            Element link = newVacancy.getElementsByTag("a").first();
            link.text(v.getTitle());
            link.attr("href", v.getUrl());
            element.before(newVacancy.outerHtml());
        }

        return document.html();
    }
    private void updateFile(String fileContent)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
    }

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
