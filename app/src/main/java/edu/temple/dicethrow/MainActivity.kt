package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {

    private var hasTwoContainers = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hasTwoContainers = findViewById<View>(R.id.container2) != null

        val dieViewModel = ViewModelProvider(this)[DieViewModel::class.java]

        /* TODO 1: Load fragment(s)
            - Show only Button Fragment if portrait
            - show both fragments if Landscape
          */
        val fragment1 = ButtonFragment()
        val fragment2 = DieFragment()
        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container1, fragment1)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()



        dieViewModel.getDieRoll().observe(this) {
            if(!dieViewModel.hasSeenSelection && !hasTwoContainers){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container1, fragment2)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()
                dieViewModel.hasSeenSelection = true
            }
        }

    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)

        */

    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        if (hasTwoContainers)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container2, DieFragment())
                .commit()
    }
}