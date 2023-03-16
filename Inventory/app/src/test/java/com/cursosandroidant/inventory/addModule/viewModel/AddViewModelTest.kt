package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by davidgonzalez on 27/02/23
 */
//La libreria de arch core nos ayudara a probar en liveData
@RunWith(AndroidJUnit4::class)
class AddViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    //Configuracion para arquitectura de componentes

    /*Objetivo de la prueba: cuando se ejecute el metodo la variable result cambie de valor y poder
    detectarlo para saber que se agrego correctamente*/
    @Test
    fun addProductTest() {
        val addViewModel = AddViewModel()
        val product = Product(117, "Xbox", 20, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Xbox_Series_S_with_controller.jpg/480px-Xbox_Series_S_with_controller.jpg",
            4.8, 56)
        val observer = Observer<Boolean>{}

        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            assertThat(result, `is`(true))
            assertThat(result, not(nullValue()))
        } finally {
            addViewModel.getResult().removeObserver(observer)
        }
    }


}