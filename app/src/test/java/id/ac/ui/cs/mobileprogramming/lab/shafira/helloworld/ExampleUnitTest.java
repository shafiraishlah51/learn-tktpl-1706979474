package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld;

import android.content.Context;

import androidx.annotation.VisibleForTesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    private static final String helloWorldText="Hello World!";
    private static final String counterNumber="0";
    Context context;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void helloWorld() {
        assertEquals("Hello World!", helloWorldText);
        assertNotEquals("helloworld", counterNumber);
    }

    @Test
    public void counter() {
        assertEquals("0", counterNumber);
        assertNotEquals("3", counterNumber);
    }
}

