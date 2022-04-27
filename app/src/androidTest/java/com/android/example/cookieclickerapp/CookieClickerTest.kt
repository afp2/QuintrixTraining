package com.android.example.cookieclickerapp

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.example.cookieclickerapp.MainActivity
import kotlin.Throws
import com.android.example.cookieclickerapp.R
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class CookieClickerTest {
    @Rule
    var activityTestRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    @Throws(Exception::class)
    fun totalStartsAtZero() {
        Espresso.onView(ViewMatchers.withId(R.id.total))
            .check(ViewAssertions.matches(ViewMatchers.withText("0")))
    }

    @Test
    @Throws(Exception::class)
    fun totalIncreasesWhenCookieClicked() {
        Espresso.onView(ViewMatchers.withId(R.id.imgCookie))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.total))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    @Throws(Exception::class)
    fun achieveHighScore() {
        for (i in 0..99) {
            Espresso.onView(ViewMatchers.withId(R.id.imgCookie))
                .perform(ViewActions.click())
        }
        Espresso.onView(ViewMatchers.withId(R.id.total))
            .check(ViewAssertions.matches(ViewMatchers.withText("100")))
    }

    @Test
    @Throws(Exception::class)
    fun submitTest() {
        for (i in 0..99) {
            Espresso.onView(ViewMatchers.withId(R.id.imgCookie))
                .perform(ViewActions.click())
        }
        Espresso.onView(ViewMatchers.withId(R.id.editText))
            .perform(ViewActions.typeText("Name"))
        Espresso.onView(ViewMatchers.withId(R.id.submitButton))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.total))
            .check(ViewAssertions.matches(ViewMatchers.withText("100")))
        Espresso.onView(ViewMatchers.withId(R.id.editText))
            .check(ViewAssertions.matches(ViewMatchers.withText("Name")))


    }



}