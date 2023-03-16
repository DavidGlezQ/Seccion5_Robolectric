package com.cursosandroidant.inventory.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by davidgonzalez on 27/02/23
 */
/*A modo de prueba se bajo la version de Robolectric para poder meter poner la configuracion
para algunos targetSDK*/
/*@Config(sdk = [21, 22, 30])
@Config(maxSdk = 30)*/ //Esto nos sirve para decirle a las pruebas hasta donde correra dichas pruebas
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkWelcomeTest() {
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}

        try {
            mainViewModel.isWelcome().observeForever(observer)
            val result = mainViewModel.isWelcome().value
            assertThat(result, not(nullValue()))
            assertThat(result, `is`(true))
        } finally {
            mainViewModel.isWelcome().removeObserver(observer)
        }
    }


}