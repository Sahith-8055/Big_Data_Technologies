package com.msit;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
	
	private final LongWritable one = new LongWritable(1);
	
	public MyMapper() {
		System.out.println("My Mapper()");
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//super.map(key, value, context);
		System.out.println("MyMapper.map(-,-,-) context=" + context);
		System.out.println("Key=" + key.get());
		System.out.println("Value=" + value.toString());
		String[] words = value.toString().split(" ");
		
		for(String word: words) {
			context.write(one, new Text(word));
		}
		
	}

}
