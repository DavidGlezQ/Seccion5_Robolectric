package com.cursosandroidant.inventory.mainModule.view

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import com.cursosandroidant.inventory.R
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnMsg() {
        //Asegurarnos que la vista esta lista
        onView(withId(R.id.recyclerView)).perform(click())

        //Click en el menu
        onView(withId(R.id.action_history)).perform(click())

        //Soperte de pruebas para varios lenguajes
        var snackbarMsg = ""
        activityScenarioRule.scenario.onActivity {
            snackbarMsg = it.resources.getString(R.string.menu_main_action_history)
        }

        //Verificar que si se hace click con comparando el texto
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(
            ViewAssertions.matches(
                withText(snackbarMsg)
            )
        )
    }

    @Test
    fun contextMenu_menuItemClick_returnMessage() {
        onView(withId(R.id.recyclerView)).perform(click())

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText("Salir")).perform(click())
    }
}
