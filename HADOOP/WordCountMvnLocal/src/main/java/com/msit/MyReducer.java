package com.msit;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<LongWritable, Text, LongWritable, Text> {
	
	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private Map<Integer, String> sortedMap = new TreeMap<Integer, String>(Collections.reverseOrder());
	
	
	public MyReducer() {
		System.out.println("My Reducer()");
	}
	
	@Override
	protected void reduce(LongWritable one, Iterable<Text> it, Context context) 
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//super.reduce(one, it, context);
		System.out.println("MyReducer.reducer(-,-,-) reduce" + context);
		System.out.println("Word=" + one.toString());
		
		int count = 0;
		for(Text word: it) {
			count = 1;
			String wordStr = word.toString();
			if(wordMap.containsKey(wordStr)) {
				count = wordMap.get(wordStr);
				count++;
			}
			wordMap.put(wordStr, count);
		}
		
		for(Map.Entry<String, Integer> entry: wordMap.entrySet()) {
			sortedMap.put(entry.getValue(), entry.getKey());
		}
		
		for(Map.Entry<Integer, String> entry: sortedMap.entrySet()) {
			context.write(new LongWritable(entry.getKey()), new Text(entry.getValue()));
			break;
		}
		
	}
}
