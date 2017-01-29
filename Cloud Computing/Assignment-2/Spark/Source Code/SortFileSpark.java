import java.util.Arrays;
import java.util.List;
import java.lang.Iterable;

import scala.Tuple2;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;


public class SortFile {
  public static void main(String[] args) throws Exception {
    String inputFile = args[0];
    String outputFile = args[1];
    SparkConf conf = new SparkConf().setAppName("sortFile");
    JavaSparkContext sc = new JavaSparkContext(conf);
    double starttime1=System.currentTimeMillis();
    JavaRDD<String> input = sc.textFile(inputFile);
    JavaRDD<String> lines = input.flatMap(
      new FlatMapFunction<String, String>() {
        public Iterable<String> call(String x) {
          return Arrays.asList(x.split("\r\n"));
        }});
    
    double starttime2=System.currentTimeMillis();
    JavaRDD<String> sorted =(JavaRDD<String>) lines.takeOrdered((int)lines.count());
    double endtime2=System.currentTimeMillis();
    sorted.saveAsTextFile(outputFile);
    double endtime1=System.currentTimeMillis();
    double totaltime=(endtime1-starttime1)/1000;
    double throughput= 10240/(endtime2-starttime2);

	}
}

