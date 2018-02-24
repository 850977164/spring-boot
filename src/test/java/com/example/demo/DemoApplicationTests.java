package com.example.demo;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.example.controller.BlockController;
import com.example.service.ReadJSONFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import scala.Tuple2;

import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import java.io.*;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class DemoApplicationTests {
    @Autowired
    private ReadJSONFileService readJSONFileService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/service/user?wsdl");
        QName opName = new QName("http://webservice.example.com/", "getStudent");
        Object[] objects = client.invoke(opName, "111");
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(objects);
        System.out.println(str);
    }

    @Test
    public void client() {
        String url = "http://localhost:8080/demo/webapp/transcation/getTransInfo";
        DefaultHttpClient client = new DefaultHttpClient();          //创建一个对话
        HttpGet get = new HttpGet(url);      //设置一个方法，和对话地址

        try {
            HttpResponse response = client.execute(get);        //执行并得到响应

            HttpEntity entity = response.getEntity();          //取出响应实体
            System.out.println(EntityUtils.toString(entity));
            /*if(entity!=null){
                InputStream is=entity.getContent();
				int l;
				byte[] b=new byte[1024];
				while((l=is.read(b))!=-1){
					System.out.println(l);              //打印长度
				}
			}*/
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void readJSON() throws JSONException {
//		JSONObject object=new JSONObject(readJSONFileService.getData());
        File file = new File(DemoApplicationTests.class.getClassLoader().getResource("menu.json").getPath());
        BufferedReader reader = null;
        String jsonstr = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempstr = null;
            try {
                while ((tempstr = reader.readLine()) != null) {
                    jsonstr = jsonstr + tempstr;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String p2 = DemoApplicationTests.class.getResource("").getPath();
        System.out.println("==========" + p2 + "=============");
        System.out.println("==========" + jsonstr + "=============");
    }

    //    @Test
//    public void shouldReturnDefaultMessage(){
//        this.mockMvc.perform().andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello World")))
//                .andDo(document("home"));
//    }
    @Test
    public void sshClient() throws IOException {
//        Connection conn = new Connection("10.132.97.27");
//        conn.connect();
//       boolean isAuthenticated = conn.authenticateWithPassword("root","root");
//        if(!isAuthenticated){
//
//        }
//        Session session=conn.openSession();
//        session.execCommand("");

    }
    @Test
    public static void sparktest(){
        SparkConf conf=new SparkConf().setAppName("spark_demo01").setMaster("spark://10.132.28.116:7077");
        JavaSparkContext sc=new JavaSparkContext(conf);
        String path="/home/employeesee.json";
        JavaRDD<String> textfile=sc.textFile(path);
        JavaPairRDD<String,Integer> counts=textfile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<String, Integer>(word,1))
                .reduceByKey((a,b)->a+b);
        counts.saveAsTextFile("/home/out.text");
    }
}
