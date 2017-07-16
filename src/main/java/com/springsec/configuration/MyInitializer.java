package com.springsec.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Sumit
 *
 */
public class MyInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MyConfiguration.class };
    }
  
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
     */
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }
 
}