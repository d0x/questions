package so18259690;

import java.beans.XMLDecoder;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Entities.EscapeMode;

public class Main
{
	public static void main(String[] args)
	{
		String l_input = "<html><body>before <a href=\"http://a.b.com/ct.html\">link text</a> after</body></html>";
		org.jsoup.nodes.Document l_doc = org.jsoup.Jsoup.parse(l_input);
		
		l_doc.outputSettings(l_doc.outputSettings().escapeMode(EscapeMode.xhtml));
		
		org.jsoup.select.Elements l_html_links = l_doc.getElementsByTag("a");

		for (org.jsoup.nodes.Element l : l_html_links)
		{
			l.attr("href", "http://a.b.com/ct.html?a=111&b=222");
		}
		
		String l_output = l_doc.outerHtml();
		
		String unescapedXml = StringEscapeUtils.unescapeXml(l_output);
		
		System.out.println(unescapedXml);
	}
}
