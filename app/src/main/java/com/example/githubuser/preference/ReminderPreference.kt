package com.example.githubuser.preference

import android.content.Context
import com.example.githubuser.model.Reminder

class ReminderPreference(context: Context) {

    companion object{
        const val PREFS_NAME = "reminder_pref"
        private const val REMINDER = "reminder"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: Reminder){
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isRemind)
        editor.apply()
    }

    fun getReminder(): Reminder{
        val model = Reminder()
        model.isRemind = preference.getBoolean(REMINDER, false)
        return model
    }
}