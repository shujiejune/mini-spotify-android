package com.laioffer.spotify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laioffer.spotify.datamodel.Album
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: NetworkApi
    @Inject
    lateinit var databaseDao: DatabaseDao
    private val TAG = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // layout xml
        setContentView(R.layout.activity_main)

        // nav_graph, navhost, navcontroller

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // navHost, navController
        // using navController to change the fragment in navHost

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.nav_graph)

        NavigationUI.setupWithNavController(navView, navController)

//         https://stackoverflow.com/questions/70703505/navigationui-not-working-correctly-with-bottom-navigation-view-implementation
        navView.setOnItemSelectedListener{
            NavigationUI.onNavDestinationSelected(it, navController)
            navController.popBackStack(it.itemId, inclusive = false)
            true
        }
        // Test retrofit
        GlobalScope.launch(Dispatchers.IO) {
            //val api = NetworkModule.provideRetrofit().create(NetworkApi::class.java)
            val response = api.getHomeFeed().execute().body()
            Log.d("Network", response.toString())
        }

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val album = Album(
                    id = 1,
                    name =  "Hexagonal",
                    year = "2008",
                    cover = "https://upload.wikimedia.org/wikipedia/en/6/6d/Leessang-Hexagonal_%28cover%29.jpg",
                    artists = "Lesssang",
                    description = "Leessang (Korean: 리쌍) was a South Korean hip hop duo, composed of Kang Hee-gun (Gary or Garie) and Gil Seong-joon (Gil)"
                )
                databaseDao.favoriteAlbum(album)
            }
        }
    }
}
