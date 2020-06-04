package com.msit;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.shaded.org.apache.commons.io.FileUtils;

public class MyDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		//String iPath = "src/main/resources/wordcount_input";
		//String oPath = "src/main/resources/wordcount_output";
		
		String iPath = "hdfs://localhost:9000/user/hduser/input";
		String oPath = "hdfs://localhost:9000/user/hduser/output";
		
		Path inputPath = new Path(iPath);
		Path outputPath = new Path(oPath);
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setJarByClass(MyDriver.class);
		
		//job.setOutputKeyClass(LongWritable.class);
		//job.setOutputValueClass(Text.class);
		
		System.out.println("" + job.getInputFormatClass());
		System.out.println("" + job.getOutputFormatClass());
		System.out.println("" + job.getOutputKeyClass());
		System.out.println("" + job.getOutputValueClass());
		
		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		FileUtils.deleteDirectory(new File(oPath));
		
		System.exit(job.waitForCompletion(true)?0:1);
		
		

	}

}
