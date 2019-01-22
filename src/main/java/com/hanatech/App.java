package com.hanatech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;


/**
 * Hello world!
 *
 */
public class App {
	static String requestUrl="http://api.feige.ee/SmsService/Send";
	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			  List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			   formparams.add(new BasicNameValuePair("Account","账号"));
			   formparams.add(new BasicNameValuePair("Pwd","接口秘钥"));
			   formparams.add(new BasicNameValuePair("Content","您的验证码是1234"));
			   formparams.add(new BasicNameValuePair("Mobile","138****1234"));
			   formparams.add(new BasicNameValuePair("SignId","签名id"));
			   Post(formparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

			public static void Post( List<NameValuePair> formparams) throws Exception {
				CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		
			 	httpClient.start();
			 
			   HttpPost requestPost=new HttpPost(requestUrl);
			 
		       requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

			   httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {
				
				public void failed(Exception arg0) {
					
					 System.out.println("Exception: " + arg0.getMessage());
				}
				
				public void completed(HttpResponse arg0) {
					  System.out.println("Response: " + arg0.getStatusLine());
				try {
					
					InputStream stram= arg0.getEntity().getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
					System.out.println(	reader.readLine());
					
				} catch (UnsupportedOperationException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
					
				}
				
				public void cancelled() {
					// TODO Auto-generated method stub
					
				}
			}).get();
			 
			 
			 
			  System.out.println("Done");
			 }
		
	
}
