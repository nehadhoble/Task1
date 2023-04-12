package com.example.task1

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.task1.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var layout: LinearLayout
    private lateinit var random: Random
    private lateinit var buttons: MutableList<Button>
    private lateinit var redButtonBackground: Drawable
    private lateinit var greyButtonBackground: Drawable
    private lateinit var blueButtonBackground: Drawable
    private var redButtonIndex = -1
    private var blueButtonIndex = -1
    private var greyButtonIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        layout = binding.layout
        random = Random
        buttons = mutableListOf()

        redButtonBackground = getDrawable(R.drawable.red_button_background)!!
        greyButtonBackground = getDrawable(R.drawable.grey_button_background)!!
        blueButtonBackground = getDrawable(R.drawable.blue_button_background)!!

        val number = intent.getIntExtra("NUMBER", 0)
        for (i in 0 until number) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            button.background = greyButtonBackground
            button.isEnabled = false
            buttons.add(button)
            layout.addView(button)
        }

        blueButtonIndex = random.nextInt(number)
        buttons[blueButtonIndex].background = blueButtonBackground
        buttons[blueButtonIndex].isEnabled = true

        redButtonIndex = random.nextInt(number)
        while (redButtonIndex == blueButtonIndex) {
            redButtonIndex = random.nextInt(number)
        }
        buttons[redButtonIndex].background = redButtonBackground
        buttons[redButtonIndex].isEnabled = true

        for (i in 0 until number) {
            buttons[i].setOnClickListener {
                onClick(it)
            }
        }
    }

    private fun onClick(view: View) {
        if (view.background == greyButtonBackground) {
            return
        }
        view.background = greyButtonBackground
        greyButtonIndex++
        if (view == buttons[blueButtonIndex]) {
            do {
                blueButtonIndex = random.nextInt(buttons.size)
            } while (blueButtonIndex == redButtonIndex || buttons[blueButtonIndex].background != greyButtonBackground)
            buttons[blueButtonIndex].background = blueButtonBackground
        } else if (view == buttons[redButtonIndex]) {
            do {
                redButtonIndex = random.nextInt(buttons.size)
            } while (redButtonIndex == blueButtonIndex || buttons[redButtonIndex].background != greyButtonBackground)
            buttons[redButtonIndex].background = redButtonBackground
        }
        if (greyButtonIndex == buttons.size - 1) {
            for (i in 0 until buttons.size) {
                if (buttons[i].background == redButtonBackground) {
                    buttons[i].background = greyButtonBackground
                    break
                }
            }
        } else {
            do {
                blueButtonIndex = random.nextInt(buttons.size)
            } while (blueButtonIndex == redButtonIndex || buttons[blueButtonIndex].background != greyButtonBackground)
            buttons[blueButtonIndex].isEnabled = true
            view.isEnabled = false
        }
    }
}
