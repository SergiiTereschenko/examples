package com.st.jsoup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JsoupGetHtmlPage {

//    https://websummit.com/featured-startups

    @Test
    public void getContent1() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("websummit.html").getFile());

        Document document = Jsoup.parse(file, "UTF-8");

        Elements lidivdiva = document.select("li div div a");
        Elements figcaption = document.select("figcaption");

        StringBuilder content = new StringBuilder();

        String separator = System.getProperty("line.separator");

        int ordinal = 0;
        for (Element element : figcaption) {
            content.append(element.select(".title").get(0).text());
            content.append(",");
            content.append(element.select(".sub-title").get(0).text());
            String href = lidivdiva.get(ordinal).attr("href");
            content.append(",").append(href);
            content.append(separator);
            System.out.println(element.text());
            ordinal++;

        }

        File result = new File("websummitStartupsContentList.csv");


        FileUtils.writeStringToFile(result, content.toString(), (String) null);
        System.out.println("done");

    }

    @Test
    public void getContent2() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("slush_org.html").getFile());

        Document document = Jsoup.parse(file, "UTF-8");

        Elements elements = document.select(".startups-listing__wrapper .startups-listing__item");

        StringBuilder content = new StringBuilder();

        String separator = System.getProperty("line.separator");

        int ordinal = 0;
        for (Element element : elements) {
            content.append(element.select("h5 a").get(0).text());
            content.append(", ");
//            content.append(element.select(".sub-title").get(0).text());
            String site = element.select(".js-show a ").attr("href");
            content.append(", ").append(site).append(", ");
            Elements domains = element.select(".startups-listing__item__tags .selectionShareable");
            String domainList = "";
            for (Element domain : domains) {
                domainList = domainList + domain.text() + " / ";
//                content.append(domain.text()).append(" / ");
            }
            String removeEnd = StringUtils.removeEnd(domainList, " / ");
            content.append(removeEnd);

            content.append(separator);
            System.out.println(element.text());
            ordinal++;

        }

        File result = new File("slush_startups_contentlist.csv");


        FileUtils.writeStringToFile(result, content.toString(), (String) null);
        System.out.println("done");

    }

//    https://collisionconf.com/featured-attendees

    @Test
    public void getContent3() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("collisionconf_init_data.html").getFile());

        Document document = Jsoup.parse(file, "UTF-8");

        Elements elements = document.select(".item figcaption");

        StringBuilder content = new StringBuilder();

        String separator = System.getProperty("line.separator");

        int ordinal = 0;
        for (Element element : elements) {
            content.append(element.select(".title").get(0).text());
//            content.append(element.select(".sub-title").get(0).text());
            Elements subElements = element.select("span");
            content.append(", ").append(subElements.get(0).attr("title"));
            content.append(", ").append(subElements.get(1).attr("title"));
            content.append(", ").append(subElements.get(2).attr("title"));

            content.append(separator);
            System.out.println(element.text());
            ordinal++;

        }

        File result = new File("collisionconf_attendees.csv");


        FileUtils.writeStringToFile(result, content.toString(), (String) null);
        System.out.println("done: " + ordinal);

    }


    //    https://collisionconf.com/featured-startups
//    там лист компаний, нужно название и желательно индустрия и страна.

    @Test
    public void getContent4() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("collisionconf_startups.html").getFile());

        Document document = Jsoup.parse(file, "UTF-8");

        Elements elements = document.select("figcaption");

        StringBuilder content = new StringBuilder();

        String separator = System.getProperty("line.separator");

        int ordinal = 0;
        for (Element element : elements) {
            content.append(element.select("h4").get(0).text());
//            content.append(element.select(".sub-title").get(0).text());
            Elements subElements = element.select("span strong");
            content.append("; ").append(subElements.get(0).text());
            content.append("; ").append(subElements.get(1).text());

            content.append(separator);
            System.out.println(element.text());
            ordinal++;

        }

        File result = new File("collisionconf_startups.csv");


        FileUtils.writeStringToFile(result, content.toString(), (String) null);
        System.out.println("done: " + ordinal);

    }

}

