package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.runner.AndroidJUnit4;

import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Kyleh on 5/2/2018.
 */

@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public IntentsTestRule<ReviewActivity> reviewActivityIntentsTestRule = new IntentsTestRule<ReviewActivity>(ReviewActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void clickButtonScenario() {
        onView(withId(R.id.add_favorite))
                .perform(click());

        intended(allOf(
                hasComponent(hasShortClassName(".ReviewActivity")),
                toPackage("kylehorton.ser210.quinnipiac.edu.critiquecenter")));
    }

    @After
    public void tearDown() throws Exception {
    }
}
