package so18649837;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main
{

	//@formatter:off
	final static String	JS_SCRIPT_GET_TEXT	= "var element = arguments[0]; 										" +
											  "var text = ''; 													" + 
											  "for (var i = 0; i < element.childNodes.length; i++) 				" + 
											  "	if (element.childNodes[i].nodeType === Node.TEXT_NODE) 			" + 
											  "	{ 																" + 
											  "	  text += element.childNodes[i].textContent + ' ';				" +
											  "	} 																" + 
											  "return text;														";
	//@formatter:on

	public static void main(final String[] args)
	{
		final FirefoxDriver driver = new FirefoxDriver();

		driver.get("http://en.wikipedia.org/wiki/HTML");

		final List<WebElement> findElementsByCssSelector = driver.findElementsByCssSelector("#mw-content-text div");

		final WebElement webElement = findElementsByCssSelector.get(0);

		final String extractInnerText = extractInnerText(webElement, driver);

		System.out.println("---------------------");
		System.out.println("Seleniums .getText():\n" + webElement.getText());

		System.out.println("\n\n---------------------");
		System.out.println("Just the node text:\n" + extractInnerText);

	}

	public static String extractInnerText(final WebElement webElement, final WebDriver webDriver)
	{
		final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

		String webElementText = (String) javascriptExecutor.executeScript(JS_SCRIPT_GET_TEXT, webElement);
		webElementText = webElementText.trim();

		return webElementText;
	}
}
