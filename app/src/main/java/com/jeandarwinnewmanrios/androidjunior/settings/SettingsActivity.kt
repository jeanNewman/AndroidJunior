package com.jeandarwinnewmanrios.androidjunior.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jeandarwinnewmanrios.androidjunior.R
import com.jeandarwinnewmanrios.androidjunior.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUMEN = "volumen"
        const val KEY_DARK_MODE= "darkMode"
        const val KEY_BLUETHOOTH = "bluethooth"
        const val KEY_VIBRATION = "vibration"
    }


    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            // ...
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_settings)
            binding = ActivitySettingsBinding.inflate(layoutInflater)
            setContentView(binding.root)
            CoroutineScope(Dispatchers.IO).launch {
                getSettings().filter { firstTime }.collect { settingsModel ->
                    if (settingsModel != null) {
                        runOnUiThread {
                            binding.rangeSlider.setValues(settingsModel.volume.toFloat())
                            binding.switchDarkMode.isChecked = settingsModel.darkMode
                            binding.switchBlueTooth.isChecked = settingsModel.bluetooth
                            binding.switchVibration.isChecked = settingsModel.vibration
                            firstTime = !firstTime
                        }
                    }
                }
            }

            initUI()
        } catch (e: Exception) {
            // ...
            Log.i("trucutru", e.message.toString())
        }

    }

    private fun initUI() {
        try {
            // ...
            // aqui se llama a la persistencia de dato sy se guarda todo
            binding.rangeSlider.addOnChangeListener{_,value,_ ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveVolume(value.toInt())
                }

            }
            binding.switchDarkMode.setOnCheckedChangeListener{_,isChecked ->
                if (isChecked) {
                    enableDarkMode()
                } else {
                    disableDarkMode()
                }
                CoroutineScope(Dispatchers.IO).launch {
                    saveOptions(KEY_DARK_MODE,isChecked)
                }
            }

            binding.switchBlueTooth.setOnCheckedChangeListener{_,isChecked ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveOptions(KEY_BLUETHOOTH,isChecked)
                }
            }

            binding.switchVibration.setOnCheckedChangeListener{_,isChecked ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveOptions(KEY_VIBRATION,isChecked)
                }
            }
            ///////////
        } catch (e: Exception) {
            // ...
            Log.i("trucutru", e.message.toString())
        }

    }

    private suspend fun saveVolume(value : Int){
        try{
            dataStore.edit {
                it[intPreferencesKey(VOLUMEN)] = value
            }
        }catch (e: Exception){
            Log.i("trucutru", e.message.toString())
        }

    }

    private suspend fun saveOptions(key: String,value : Boolean){
        try{
            dataStore.edit {
                it[booleanPreferencesKey(key)] = value
            }
        }catch (e: Exception){
            Log.i("trucutru", e.message.toString())
        }

    }

    private fun getSettings(): Flow<SettingsModel?> {
        return  try{
           dataStore.data.map { preferences ->
                SettingsModel(

                    volume = preferences[intPreferencesKey(VOLUMEN)] ?: 0,
                    darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: true,
                    bluetooth = preferences[booleanPreferencesKey(KEY_BLUETHOOTH)] ?: false,
                    vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true
                )

            }
        }catch (e: Exception){
            Log.i("trucutru", e.message.toString())
            flowOf(null) // Return a flow emitting null in case of exception
        }


    }

    private fun enableDarkMode() {
        // ...
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun disableDarkMode() {
        // ...
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}