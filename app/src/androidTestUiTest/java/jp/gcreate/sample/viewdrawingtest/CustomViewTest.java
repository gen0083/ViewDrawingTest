package jp.gcreate.sample.viewdrawingtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CustomViewTest {
    private CustomView sut;

    @Rule
    public ActivityTestRule<CustomViewTestActivity> activityRule
            = new ActivityTestRule<CustomViewTestActivity>(CustomViewTestActivity.class);

    @Before
    public void setUp() {
        sut = activityRule.getActivity().testTarget;
        sut.clearDecorate();
    }

    @Test
    public void decorationTest() {
        CustomViewTestActivity activity = activityRule.getActivity();

        sut.clearDecorate();
        activity.invalidateView();
        Spoon.screenshot(activity, "no_decorate");

        sut.clearDecorate();
        sut.decorateUp();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_up");

        sut.clearDecorate();
        sut.decorateRight();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_right");

        sut.clearDecorate();
        sut.decorateBottom();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_bottom");

        sut.clearDecorate();
        sut.decorateLeft();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_left");

        sut.clearDecorate();
        sut.decorateAround();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_around");

        sut.clearDecorate();
        sut.decorateUp();
        sut.decorateBottom();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_up_and_bottom");

        sut.clearDecorate();
        sut.decorateLeft();
        sut.decorateRight();
        activity.invalidateView();
        Spoon.screenshot(activity, "decorate_left_and_right");
    }
}