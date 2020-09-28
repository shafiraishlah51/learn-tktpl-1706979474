package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld;

import android.view.View;

import org.junit.Test;
import id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.MainActivity;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CounterTest {
    MainActivity mainActivity = new MainActivity();

    @Test
    public void counter(){
        int defaultCounter = this.mainActivity.counter;
            this.mainActivity.clickedCounter(mock(View.class));
        int counterValue = this.mainActivity.counter;
        assertTrue(counterValue>=defaultCounter);
    }
}
