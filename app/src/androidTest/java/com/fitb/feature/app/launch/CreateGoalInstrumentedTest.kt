package com.fitb.feature.app.launch

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.fitb.core.authnetwork.retrofit.RetrofitClientProvider
import com.fitb.core.contract.application
import com.example.coroutinelaunch.network.GoalRepository
import com.fitb.feature.gbs.viewmodel.CreateGoalViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.fitb.feature.gbs.R as gbsR

class CreateGoalInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private lateinit var repo: GoalRepository
    private lateinit var navController: NavController

    private lateinit var viewModel: CreateGoalViewModel

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        repo = GoalRepository(
            RetrofitClientProvider.getInstance(appContext.application).getRetrofitClient()
        )

        viewModel = CreateGoalViewModel(repo)
        activityRule.scenario.onActivity { activity ->
            navController = activity.findNavController(R.id.fragment_container_view)
        }
    }

    @Test
    fun swipe_down_should_invoke_listener() {
        onView(withId(gbsR.id.button)).perform(click())
        onView(withId(gbsR.id.swipeRefresh)).perform(swipeDown())
            .check { _, _ ->
                viewModel.swipeRefreshListener.onRefresh().apply {
                    assert(viewModel.fetchGoalCategories().isActive)
                }
            }
    }
}