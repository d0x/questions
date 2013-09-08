package so18682283;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main
{

	public static void main(String[] args)	{
		Document parse = Jsoup.parse("<div id=\"shout_23\"/>" +
				"<div id=\"shout_42\"/>" +
				"<div id=\"notValidId\"/>" +
				"<div id=\"shout_1337\"/>");

		Elements divs = parse.select("div[id^=shout");

		for (Element element : divs) {
			System.out.println(element);
		}
	}
}
