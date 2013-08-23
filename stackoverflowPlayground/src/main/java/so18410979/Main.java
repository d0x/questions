package so18410979;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		String text = "++ name1\n" +
				"description:asdfmkdfkmfkskfsaf \\n\n" +
				"++ name2\n" +
				"description:asdfmkdfkmfkskfsaf \\n\n" +
				"++ name3 description:asdfmkdfkmfkskfsaf\n" +
				"++ name4 :asdfmkdfkmfkskfsaf\n" +
				"++ name5 asdfmkdfkmfkskfsaf\n";
		System.out.println(text);

		System.out.println("\n\n\nResult:");
		Matcher matcher = Pattern.compile("\\+\\+ (.*?)\\s+(?:description)?:?([\\p{Alpha}]+)\\s*").matcher(text);
		// ^ ^ `- the description "could" be there.
		// | `- read the whitespaces and linebreaks
		// \ the two ++ and the whitespace
		//

		while (matcher.find())
		{
			System.out.println(matcher.group(1) + " - " + matcher.group(2));
		}
	}
}
