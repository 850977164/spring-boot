package com.example.webservice;

import com.example.entity.Student;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by wuhaochao on 2017/7/18.
 */
@WebService
public interface UserService {
    @WebMethod
    String getName(@WebParam(name = "user_no") String user_no);

    @WebMethod
    Student getStudent(String user_no);
}
