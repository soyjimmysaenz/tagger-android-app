package me.taggerapp.android.helpers

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> tokenType(): Type = object: TypeToken<T>() {}.type
inline fun <reified T> tokenListType(): Type = object: TypeToken<List<T>>() {}.type
