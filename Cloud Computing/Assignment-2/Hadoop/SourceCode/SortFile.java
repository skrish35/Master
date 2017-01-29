//package com.at2.hadoop.try2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SortFile {

	

  public static class TextComparator extends WritableComparator {
  
	 public TextComparator() {
      super(Text.class);
    }

    private Integer int1;
    private Integer int2;

    @Override
    public int compare(byte[] raw1, int offset1, int length1, byte[] raw2,
        int offset2, int length2) {
      int1 = ByteBuffer.wrap(raw1, offset1, length1).getInt();
      int2 = ByteBuffer.wrap(raw2, offset2, length2).getInt();

      return int2.compareTo(int1);
    }

  }

  public static void main(String[] args) throws IOException,
      ClassNotFoundException, InterruptedException {
    Path inputPath = new Path(args[0]);
    Path outputPath = new Path(args[1]);

    Configuration configuration = new Configuration();
    configuration.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
    Job job = new Job(configuration);
    job.setJobName("SortFile");
    job.setJarByClass(SortFile.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);

    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    job.setSortComparatorClass(TextComparator.class);

    FileInputFormat.setInputPaths(job, inputPath);

    FileSystem.get(configuration).delete(outputPath, true);
    FileOutputFormat.setOutputPath(job, outputPath);

    job.waitForCompletion(true);
  }
}
