/**
 * 
 */
package com.example;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author panshul
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
	protected static final String	TARGET_WORD	= "this";

	@Override
	protected void reduce(final Text key, final Iterable<IntWritable> values, final Context context) throws IOException, InterruptedException
	{
		if (containsTargetWord(key))
		{
			System.out.println("found");
			int wordCount = 0;
			for (final IntWritable value : values)
			{
				wordCount += value.get();
			}
			context.write(key, new IntWritable(wordCount));
			System.out.println(String.valueOf(wordCount));
			System.out.println(new Date().toString());
		}
		
		context.write(new Text("foo"
				), new IntWritable(42));
	}

	private boolean containsTargetWord(final Text key)
	{
		return key.toString().equals(TARGET_WORD);
	}
}
