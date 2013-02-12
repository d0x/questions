/**
 * 
 */
package com.example;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author panshul
 */
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	private final Text	word	= new Text();

	@Override
	protected void map(final LongWritable key, final Text value, final Context context) throws IOException, InterruptedException
	{
		final String line = value.toString();
		System.out.println(line);
		final StringTokenizer lineTockenizer = new StringTokenizer(line);
		while (lineTockenizer.hasMoreElements())
		{
			final String cleaned = removeNonLettersOrNumbers(lineTockenizer.nextToken());
			this.word.set(cleaned);
			context.write(this.word, new IntWritable(1));
		}
	}

	private String removeNonLettersOrNumbers(final String original)
	{
		return original.replaceAll("[^\\p{L}\\p{N}]", "");
	}
}
