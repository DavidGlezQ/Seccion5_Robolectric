package com.cursosandroidant.inventory

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

/**
 * Created by davidgonzalez on 16/03/23
 */

fun clickInChild(cbFavoriteId: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription() : String = "Child in ViewHolder"

        override fun getConstraints() : Matcher<View> = ViewMatchers.withId(cbFavoriteId)

        override fun perform(uiController: UiController?, view: View) {
            (view.findViewById(cbFavoriteId) as View).performClick()
        }

    }
}