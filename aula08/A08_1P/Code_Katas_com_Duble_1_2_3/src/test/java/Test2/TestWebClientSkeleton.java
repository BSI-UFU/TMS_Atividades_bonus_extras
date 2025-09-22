/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test2;


import com.mycompany.code_katas_com_duble_1_2_3.WebClient;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author kensl
 */
public class TestWebClientSkeleton {

    @BeforeAll
    public static void setUp() {
        // Start Jetty and configure it to return "It works" when
        // the http://localhost:8081/testGetContentOk URL is
        // called.
    }

    @AfterAll
    public static void tearDown() {
        // Stop Jetty.
    }

    @Test
    @Disabled(value = "This is just the initial skeleton of a test. Therefore, if we run it now, it will fail.")
    public void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));

        assertEquals("It works", workingContent);
    }
}