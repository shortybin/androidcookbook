package com.wuhuabin.cookbook.utils

import android.content.SharedPreferences
import com.wuhuabin.cookbook.MainApplication

class SharedPreferencesUtils(name: String) {
    private var mSPref: SharedPreferences
    private var mEditor: SharedPreferences.Editor

    init {
        mSPref = MainApplication.getInstance().getSharedPreferences(name, 0)
        mEditor = mSPref.edit()
    }

    fun put(key: String, value: Boolean) {
        mEditor = mSPref.edit()
        mEditor.putBoolean(key, value)
        mEditor.commit()
    }

    fun put(key: String, value: String) {
        mEditor = mSPref.edit()
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun put(key: String, value: Int) {
        mEditor = mSPref.edit()
        mEditor.putInt(key, value)
        mEditor.commit()
    }

    fun put(key: String, value: Long) {
        mEditor = mSPref.edit()
        mEditor.putLong(key, value)
        mEditor.commit()
    }

    fun put(key: String, value: Float) {
        mEditor = mSPref.edit()
        mEditor.putFloat(key, value)
        mEditor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return mSPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return mSPref.getString(key, null)
    }

    fun getInt(key: String): Int {
        return mSPref.getInt(key, 0)
    }

    fun getLong(key: String): Long {
        return mSPref.getLong(key, 0L)
    }

    fun getFloat(key: String): Float {
        return mSPref.getFloat(key, 0f)
    }

    fun contains(key: String): Boolean {
        return mSPref.contains(key)
    }

    fun remove(key: String) {
        mEditor = mSPref.edit()
        mEditor.remove(key)
        mEditor.commit()
    }

    fun clear() {
        mEditor = mSPref.edit()
        mEditor.clear()
        mEditor.commit()
    }
}