package com.example.attendancedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author xsr
 **/
@SpringBootApplication
public class AttendanceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceDemoApplication.class, args);
		System.out.println(
			String.format("AttendanceDemoApplication start!",
				new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())));
	}
}
