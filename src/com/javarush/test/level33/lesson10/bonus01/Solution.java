package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.lang.model.element.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution
{

    public static void main(String[] args)
    {
        First first = new First();
        first.second.add("some string 1");
        first.second.add("some string 2");
        first.second.add("<![CDATA[need CDATA because of < and >]]>");
        first.second.add("");
        System.out.println(toXmlWithComment(first, "second", "it's a comment"));
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment)
    {

        StringWriter stringWriter = new StringWriter();
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, stringWriter);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        String doc_string = stringWriter.toString();

        Document doc = null;

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(doc_string.getBytes()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        NodeList elements = doc.getElementsByTagName(tagName);
        Comment commentDOM = doc.createComment(comment);

        if (elements != null && elements.getLength() > 0)
        {
            for (int i = 0; i < elements.getLength(); i++)
            {
                doc.insertBefore(commentDOM,elements.item(i).getParentNode());
            }
        }

        return doc.toString();
        //return stringWriter.toString();
    }

    @XmlRootElement
    public static class First
    {
        public List<String> second;
        public First(){ this.second = new ArrayList<>(); }
    }
}
