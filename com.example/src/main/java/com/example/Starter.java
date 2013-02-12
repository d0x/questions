package com.example;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
	public static void main(final String[] args) throws IOException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
