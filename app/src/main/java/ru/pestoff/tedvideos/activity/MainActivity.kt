package ru.pestoff.tedvideos.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .add(R.id.video_list_fragment_container, ListVideoFragment())
            .commit()
    }

    override fun onItemChosen(item: Item) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.video_list_fragment_container, DetailsVideoFragment.createInstance(item))
            .addToBackStack(null)
            .commit()
    }
}
