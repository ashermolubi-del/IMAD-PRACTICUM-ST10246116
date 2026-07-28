package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)
        val etItemName = findViewById<EditText>(R.id.etItemName)
        val spItemName = findViewById<Spinner>(R.id.spItemName)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val spCategory = findViewById<Spinner>(R.id.spCategory)
        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val spQuantity = findViewById<Spinner>(R.id.spQuantity)

        // Define items for the Item Name dropdown
        val partyItems = arrayOf("Select an item...", "paper cups", "soda bottles", "potato chips")
        val itemAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, partyItems)
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spItemName.adapter = itemAdapter

        spItemName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) etItemName.setText(partyItems[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Define items for the Category dropdown
        val categories = arrayOf("Select a category...", "supplies", "beverages", "snacks")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCategory.adapter = categoryAdapter

        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) etCategory.setText(categories[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Define items for the Quantity dropdown (1 to 50)
        val quantities = mutableListOf("Select quantity...")
        for (i in 1..50) {
            quantities.add(i.toString())
        }
        val quantityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities)
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spQuantity.adapter = quantityAdapter

        spQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) etQuantity.setText(quantities[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnAddToCart.setOnClickListener {
            val itemName = etItemName.text.toString()
            if (itemName.isNotEmpty()) {
                Toast.makeText(this, "Adding $itemName to cart...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}