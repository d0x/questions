package so18644171;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main
{
	public static void main(String[] args) throws IOException 
	{
		StringWriter writer = new StringWriter();
		IOUtils.copy(Main.class.getResourceAsStream("/so18644171/html.html"), writer);
		String theString = writer.toString();
		
		Document doc = Jsoup.parse(theString);

		Elements linkTabs = doc.select("a[name^=linkTab] + table");
		
		System.out.println(linkTabs);
	}
}
