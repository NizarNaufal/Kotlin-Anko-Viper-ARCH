package id.nizardev.viperarchitecture

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import id.nizardev.viperarchitecture.view.MainActivity
import id.nizardev.viperarchitecture.view.adapter.JokesListAdapter

import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewIsPopulated() {

        waitForSplashScreen()

        onView(withId(R.id.rv_jokes_list_activity_main))
            .check(matches(hasDescendant(withText("2"))))
    }

    @Test
    fun testRecyclerViewItemClickLaunchesDetailActivity() {

        waitForSplashScreen()

        onView(withId(R.id.rv_jokes_list_activity_main))
            .perform(RecyclerViewActions.scrollToPosition<JokesListAdapter.ViewHolder>(2))
            .perform(RecyclerViewActions.actionOnItemAtPosition<JokesListAdapter.ViewHolder>(2, click()))

        assert(onView(withId(R.id.rv_jokes_list_activity_main)) == null)
    }
}
