package ru.pestoff.tedvideos.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.concurrent.ScheduledThreadPoolExecutor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.tedvideos.R
import ru.pestoff.tedvideos.databinding.ActivityMainBinding
import ru.pestoff.tedvideos.fragment.DetailsVideoFragment
import ru.pestoff.tedvideos.fragment.ListVideoFragment
import ru.pestoff.tedvideos.model.Item
import ru.pestoff.tedvideos.model.RssFeed
import ru.pestoff.tedvideos.service.RssService

class MainActivity : AppCompatActivity(), ListVideoFragment.FragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    private var isNightModeOn: Boolean = false
    private lateinit var appSettingPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

        appSettingPrefs = getSharedPreferences("AppSettingsPrefs", 0)
        isNightModeOn = appSettingPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val fragment = supportFragmentManager
            .findFragmentById(R.id.video_list_fragment_container)

        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.video_list_fragment_container, ListVideoFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_dark_mode -> {
                if (isNightModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    appSettingPrefs.edit().putBoolean("NightMode", false).apply()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    appSettingPrefs.edit().putBoolean("NightMode", true).apply()
                }
            }
        }

        return true
    }

    override fun onItemChosen(item: Item) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.video_list_fragment_container, DetailsVideoFragment.createInstance(item))
            .addToBackStack(null)
            .commit()
    }
}
