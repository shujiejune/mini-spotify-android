package com.laioffer.spotify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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
    }
}
