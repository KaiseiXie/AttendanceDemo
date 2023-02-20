package com.example.attendancedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author xsr
 **/
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class AttendanceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceDemoApplication.class, args);
		System.out.println(
			String.format("AttendanceDemoApplication start!",
				new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())));
	}
}
