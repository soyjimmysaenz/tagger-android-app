package me.taggerapp.android.helpers

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(view: View) {
    val inputManager = view.context.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as? InputMethodManager ?: return
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}