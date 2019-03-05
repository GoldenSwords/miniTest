package com.framework.vtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception{
        Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse("20181109000000");
        System.out.println(date);
        System.out.println("ttt::"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

    }
}
