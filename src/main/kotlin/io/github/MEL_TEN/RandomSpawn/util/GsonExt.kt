package io.github.mel_ten.randomspawn.util

// ref on: https://github.com/dalbodeule/FakeBungee/blob/master/src/main/kotlin/space/mori/fakebungee/util/GsonExt.kt

import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser

val gson = GsonBuilder().setPrettyPrinting().create()

fun Any?.serializeJsonString(): String = gson.toJson(this)
fun Any?.serializeJson(): JsonElement = serializeJsonString().parseJson()

fun String.parseJson(): JsonElement = JsonParser.parseString(this)

inline fun <reified T> String.parseJsonTo(): T? = gson.fromJson(this, object : TypeToken<T>(){}.type)

fun <T> String.parseJsonTo(clazz: Class<T>): T? = gson.fromJson<T>(this, clazz)