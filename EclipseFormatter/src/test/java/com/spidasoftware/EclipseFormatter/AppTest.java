package com.spidasoftware.EclipseFormatter;

import com.spidasoftware.EclipseFormatter.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite; 

/**
 * tests for GroovyFormat and JavaFormat classes
 * 
 * spidasoftware
 * @author Nick Joodi
 */
public class AppTest extends TestCase
{   


    /**
     * Test the JavaFormat constructor
     */
    public void testjavaFormatConstruct()
    {
        JavaFormat javaFormatter = new JavaFormat();
        assertTrue("correctlyFormatted is set to false", javaFormatter.isFormatted() == false);
    }


    /**
     * Test the GroovyFormat constructor
     */
    public void testgroovyFormatConstruct()
    {
        GroovyFormat groovyFormatter = new GroovyFormat();
        assertTrue("correctlyFormatted is set to false", groovyFormatter.isFormatted() == false);
    }

    /**
     * Test the JavaFormat eith unparsable code
     */
    public void testunParsableJavaCode()
    {
        JavaFormat javaFormatter = new JavaFormat();
        javaFormatter.format("java.java", " asfasgfdasgfdagfdsgsafgdasfdsf dsfdsa sdfdsf dsd");
        assertTrue("correctlyFormatted is false", javaFormatter.isFormatted() == false);
    }

    /**
     * Test the JavaFormat with parsable code
     */
    public void testparsableJavaCode()
    {
        JavaFormat javaFormatter = new JavaFormat();
        javaFormatter.format("java.java", " package groovyTest;\npublic class genericJavaClass" 
            + "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}");
        assertTrue("correctlyFormatted is true", javaFormatter.isFormatted() == true);
    }

    /**
     * Test the GroovyFormat with parsable code
     */
    public void testparsableGroovyCode()
    {
        GroovyFormat groovyFormatter = new GroovyFormat();
        groovyFormatter.format("groovy.groovy", " package groovyTest\nclass genericClass "
            + "{\nstatic main(args) {\n}\n}\n");
        assertTrue("correctlyFormatted is true", groovyFormatter.isFormatted() == true);
    }

}
