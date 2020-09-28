package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.Text;

import id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.MainActivity;
import id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import androidx.test.filters.MediumTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class CounterStringTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void counterString(){
        String defaultString = "0";
        onView(withId(R.id.tx)).check(matches(withText(defaultString)));
    }
}
