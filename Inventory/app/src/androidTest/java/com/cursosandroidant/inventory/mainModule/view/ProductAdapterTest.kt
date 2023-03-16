package com.cursosandroidant.inventory.mainModule.view

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosandroidant.inventory.R
import com.cursosandroidant.inventory.clickInChild
import com.cursosandroidant.inventory.mainModule.view.adapters.ProductAdapter
import junit.framework.TestCase.fail
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher

/**
 * Created by davidgonzalez on 15/03/23
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    //Prueba para saber si el texto del SnackBar en la posicion 1 del recyclerView si coincide
    @Test
    fun listItem_click_successCheck() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText("Queso")))
    }

    //Prueba para hacer scroll y hacer click en el elemento que contenga tijeras
    @Test
    fun listItem_longClick_removeFromView() {
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItem<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras"))), longClick()),
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Vino"))))
            )

        try {
            onView(withId(R.id.recyclerView))
                .perform(scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras")))
                ))

            //Fail es un metodo de JUnit, cualquier linea posterior al fail no deberia de ejecutarse
            //por lo que pasa directamente al catch
            fail("Tijeras aun existe!!")
        } catch (e: Exception) {
            assertThat((e as? PerformException), `is`(notNullValue()))
        }
    }

    @Test
    fun cbFavorite_click_changeState() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, clickInChild(R.id.cbFavorite)))
    }


}