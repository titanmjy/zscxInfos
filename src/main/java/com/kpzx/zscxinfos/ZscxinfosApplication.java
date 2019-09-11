package com.kpzx.zscxinfos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Scanner;

//@RestController
//@SpringBootApplication
public class ZscxinfosApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ZscxinfosApplication.class, args);
        getInfo();
    }

    @RequestMapping
    public String hello(){
        return "hello springboot";
    }



    public static void getInfo(){
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://zscx.osta.org.cn/jiandingApp/command/buzhongxin/ecCertSearchAll"))
                .setHeader("referer1", "http://zscx.osta.org.cn/htm/12221/175546.html")
//                .setHeader("HTTP_X_FORWARD_FOR","121.121.121.121")
//                .setHeader("HTTP_X_FORWARDED_FOR","121.121.121.121")
//                .setHeader("X-FORWARDED-FOR","121.121.121.121")
//                .setHeader("client_ip","121.121.121.121")
//                .setHeader("remote_addr","121.121.121.121")
                .setHeader("x-forwarded-for","121.121.121.121")
                .POST(HttpRequest.BodyPublishers.ofString("tableName=CERT_T&tableName1=000000&CertificateID=&CID=320981199305101981&Name=%E8%B5%B5%E5%B0%91%E8%90%8D&x=169&y=21&province=-1"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
//            String verifyCodeCookie = res.headers().map().get("set-cookie").get(0);
//            System.out.println(verifyCodeCookie);
//            String verifyCode = getVerifyCode(verifyCodeCookie);
            // 再次发送请求
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }


//    public String getVerifyCode(String cookie){
//        String verifyCodeUrl = "http://zscx.osta.org.cn/jiandingApp/verifycode";
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(verifyCodeUrl))
//                .build();
//        try {
//            client.send(request,HttpResponse.BodyHandlers.ofFile(Paths.get("1.jpg")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("请输入验证码：");
//        Scanner s = new Scanner(System.in);
//        String verifyCode = s.nextLine();
//        return verifyCode;
//    }
}
