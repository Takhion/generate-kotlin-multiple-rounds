package me.eugeniomarletti.sample

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testGeneratedCode { message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }
}
