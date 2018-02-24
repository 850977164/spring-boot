package com.example.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import javax.xml.soap.SOAPPart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wuhaochao on 2017/8/21.
 */
public class WordCounts {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("wordCounts")
//                .setJars(JavaSparkContext.jarOfClass(WordCounts.class))
                .setMaster("spark://10.132.28.116:7077");
        JavaSparkContext sc=new JavaSparkContext(conf);
        //   D:/temp/wordcounts.txt
        String path="file:///D:/temp/wordcounts.txt";
        List list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("1");
        list.add("1");
        list.add("b");
        List<Integer> listint=new ArrayList<Integer>();
        listint.add(1);
        listint.add(2);
        listint.add(3);
        listint.add(4);
        listint.add(5);
        listint.add(6);
        JavaRDD<String> str=sc.parallelize(list);
        JavaRDD<Integer> in=sc.parallelize(listint);
        System.out.println("======map操作=========");
        System.out.println("======================"+in.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        }).collect());
        System.out.println("======map操作=========");

       /* System.out.println("======filter操作=========");
        System.out.println("======================"+in.filter(x -> x>3).collect());
        System.out.println("======filter操作=========");

        System.out.println("======flatMap操作=========");
       // System.out.println("======================"+in.flatMap(x -> ).collect());
        System.out.println("======flatMap操作=========");

        System.out.println("======count操作=========");
        System.out.println("======================"+in.count());
        System.out.println("======count操作=========");

        System.out.println("======reduce操作=========");
        System.out.println("======================"+in.reduce((x,y) -> x+y));
        System.out.println("======reduce操作=========");

        System.out.println("======countByValue操作=========");
        System.out.println("======================"+in.countByValue());
        System.out.println("======countByValue操作=========");

        System.out.println("======fold操作=========");
        System.out.println("======================"+in.fold(0,((x,y) -> x+y)));
        System.out.println("======fold操作=========");*/

//        System.out.println("======forEach操作=========");
//        System.out.println("======================"+str.map(x -> x.split(" "))
//                .mapToPair(word -> new Tuple2<String, Integer>(word,1))
//
//                .foreach((key,value) -> System.out.print(key+"="+value)));
//        System.out.println("======forEach操作=========");
       // JavaRDD<String> textfile=sc.textFile(path);
        System.out.println("==============="+str.count());
        System.out.println("==============="+str.first());
        /*JavaPairRDD<String,Integer> counts=textfile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<String, Integer>(word,1))
                .reduceByKey((a,b)->a+b);
//        System.out.println("==============="+counts.count());
        Map<String,Integer> map=counts.collectAsMap();
        map.forEach((key,value)->System.out.println(key+"="+value));
        counts.saveAsTextFile("D:/temp/out");*/
    }
}
