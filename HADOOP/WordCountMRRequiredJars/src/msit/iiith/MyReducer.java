package msit.iiith;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text word, Iterable<IntWritable> it, Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Word=" + word.toString());
		System.out.println("[");
		int count = 0;
		for(IntWritable i: it) {
			System.out.println(i.get() + ",");
			count = count + i.get();
		}
		System.out.println("]");
		context.write(word, new IntWritable(count));
	}
}
