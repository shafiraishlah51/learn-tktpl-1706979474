package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld;

import android.content.Context;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld", appContext.getPackageName());
    }
}
