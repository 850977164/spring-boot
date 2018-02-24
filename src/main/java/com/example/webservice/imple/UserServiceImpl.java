package com.example.webservice.imple;

import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.webservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

/**
 * Created by wuhaochao on 2017/7/18.
 */
@WebService(targetNamespace = "http://localhost:8080/demo/service/user?wsdl", endpointInterface = "com.example.webservice.UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentService studentService;

    @WebMethod
    public String getName(String user_no) {
        return "你好" + user_no;
    }

    @WebMethod
    public Student getStudent(String user_no) {
        return studentService.getStuList().get(0);
    }
}
