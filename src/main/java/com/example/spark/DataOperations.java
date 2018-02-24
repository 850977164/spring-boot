package com.example.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhaochao on 2017/8/22.
 */
public class DataOperations {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf()
                .setAppName("spark_demo04")
                .setJars(JavaSparkContext.jarOfClass(WordCounts.class))
                .setMaster("spark://10.132.28.116:7077");
        JavaSparkContext sc=new JavaSparkContext(conf);
        List<Integer> l = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }

        long count = sc.parallelize(l).filter(i -> {
            double x = Math.random();
            double y = Math.random();
            return x*x + y*y < 1;
        }).count();
        System.out.println("Pi is roughly " + 4.0 * count / 10);
    }
}
