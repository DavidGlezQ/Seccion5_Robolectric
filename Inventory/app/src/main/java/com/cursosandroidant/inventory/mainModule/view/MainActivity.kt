package com.cursosandroidant.inventory.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cursosandroidant.inventory.R
import com.cursosandroidant.inventory.addModule.view.AddProductFragment
import com.cursosandroidant.inventory.databinding.ActivityMainBinding
import com.cursosandroidant.inventory.entities.Product
import com.cursosandroidant.inventory.mainModule.view.adapters.OnClickListener
import com.cursosandroidant.inventory.mainModule.view.adapters.ProductAdapter
import com.cursosandroidant.inventory.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupFab()

        setupViewModel()
    }

    private fun setupAdapter(){
        adapter = ProductAdapter(this)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,
                resources.getInteger(R.integer.main_columns))
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupFab(){
        binding.fab.setOnClickListener { //Open add fragment
            AddProductFragment().show(supportFragmentManager, getString(R.string.add_title))
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getProducts().observe(this) { products ->
            setListToAdapter(products)
        }
        viewModel.isWelcome().observe(this) { isWelcome ->
            if (isWelcome) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.main_msg_welcome),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.setWelcome(false)
            }
        }
    }

    private fun setListToAdapter(products: List<Product>) {
        adapter.submitList(products)
    }

    /**
     * OnClickListener
     * */
    override fun onClick(product: Product) {
        viewModel.setWelcome(true)

        Snackbar.make(binding.root, product.name, Snackbar.LENGTH_SHORT).show()
    }

    override fun onLongClick(product: Product) {
        viewModel.deleteProduct(product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val message = when(item.itemId) {
            R.id.action_history -> getString(R.string.menu_main_action_history)
            R.id.action_exit -> getString(R.string.menu_main_action_exit)
            else -> "Empty Action"
        }
        Snackbar.make(binding. root, message, Snackbar.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}