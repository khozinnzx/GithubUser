package com.example.githubuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivitySettingsBinding
import com.example.githubuser.model.Reminder
import com.example.githubuser.preference.ReminderPreference
import com.example.githubuser.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        if (reminderPreference.getReminder().isRemind){
            binding.switch1.isChecked = true
        }else{
            binding.switch1.isChecked = false
        }

        alarmReceiver = AlarmReceiver()
        
        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "RepeatingAlarm", "09:00" ,"Reminder")
            }else{
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }


    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()
        reminder.isRemind = state
        reminderPreference.setReminder(reminder)

    }
}