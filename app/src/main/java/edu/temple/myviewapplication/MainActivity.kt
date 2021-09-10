package edu.temple.myviewapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // First way to create an event listener:
    // Create a nested or top-level named class
    // least common and not very flexible - cannot directly members of the activity
    class MyOnClickListener(val _context: Context) : View.OnClickListener { // Passed in Context to allow creating a Toast
        val context = _context
        override fun onClick(v: View?) {
            Toast.makeText(context, "Message", Toast.LENGTH_LONG).show()
            Log.d("this@MainActivity", "You clicked the button")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Storing reference in variable to avoid having to call
        // findViewByID multiple times (see SeekBar event listener)
        val helloWorldTextView = findViewById<TextView>(R.id.helloWorldTextView)

        val myButton = findViewById<Button>(R.id.button)

        // Second way to create an event listener
        // Create an anonymous class (assigned to variable)
        // Necessary when multiple events/callbacks

        val ocl = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "You clicked the button", Toast.LENGTH_LONG).show()
                Log.d("Message", "You clicked the button")
            }
        }

        // Third way to create an event listener
        // using a lambda (anonymous function)
        // Most convenient. Only possible when there is a single callback
        myButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked the button", Toast.LENGTH_SHORT).show()
            Log.d("Message", "You clicked the button")
        }

        // Because we only access the seekbar object once, we didn't need
        // to assign the reference to a variable
        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{ // We can't use a lambda here, because the event listener has multiple callbacks
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                helloWorldTextView.textSize = progress.toFloat()
                helloWorldTextView.alpha = 1 - (progress / 100f)

                Log.d("Progress", progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }

}