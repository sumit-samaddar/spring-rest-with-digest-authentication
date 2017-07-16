package com.springsec.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Sumit
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springsec")
public class MyConfiguration {

}