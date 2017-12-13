package com.example.junitprimo;

import com.example.junitprimo.pojo.Primo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void esPrimo_isCorrect() throws Exception {
        assertFalse(Primo.esPrimo(-1));
        assertFalse(Primo.esPrimo(0));
        assertFalse(Primo.esPrimo(1));
        assertTrue(Primo.esPrimo(2));
        assertFalse(Primo.esPrimo(4));
        assertTrue(Primo.esPrimo(3));
    }
}