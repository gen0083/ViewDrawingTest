package jp.gcreate.sample.viewdrawingtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Copyright 2016 G-CREATE
 */

@RunWith(AndroidJUnit4.class)
public class CustomViewTestWithMainActivity {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test() {
        MainActivity activity = activityRule.getActivity();

        Spoon.screenshot(activity, "initial_view");

        onView(withId(R.id.button)).perform(click());
        Spoon.screenshot(activity, "first_click");

        onView(withId(R.id.button)).perform(click());
        Spoon.screenshot(activity, "second_click");

        onView(withId(R.id.button)).perform(click());
        Spoon.screenshot(activity, "third_click");

        onView(withId(R.id.button)).perform(click());
        Spoon.screenshot(activity, "fourth_click");
    }
}
