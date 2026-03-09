package com.matecode.focusgarden;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.matecode.focusgarden.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // call parent class method with current android state, like: textboxes etc.

        binding = ActivityMainBinding.inflate(getLayoutInflater());     // load xml file (inflater) into object (binding)
        setContentView(binding.getRoot());      // show on screen everything what main container (Root) contains

        setSupportActionBar(binding.toolbar);   // tell android to treat toolbar as ActionBar, activate toolbar and its features

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);  // must be reach by ID because it is not a View object
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();    // build navbar config
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);     // apply config into navbar

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action !!!", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)    // show snackbar above FAB button
                        .setAction("Action", null).show();  // additional action inside snackbar (like unsend on email)
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}