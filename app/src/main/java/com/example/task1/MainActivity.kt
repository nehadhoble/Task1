package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToGame.setOnClickListener {
            val num = binding.etEnterNumber.text.toString().toInt()
            if(num != null && num <= 10){
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("NUMBER", num)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Number should be less than or Equal to 10", Toast.LENGTH_LONG).show()
            }
        }
    }
}