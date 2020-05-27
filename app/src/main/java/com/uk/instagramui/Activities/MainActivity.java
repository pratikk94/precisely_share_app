package com.uk.instagramui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uk.instagramui.Fragments.HomeFragment;
import com.uk.instagramui.Fragments.NotificationsFragment;
import com.uk.instagramui.Fragments.ProfileFragment;
import com.uk.instagramui.Fragments.SearchFragment;
import com.uk.instagramui.R;

public class MainActivity extends AppCompatActivity {
	
	//Random image urls below
	public FloatingActionButton fab;
	public static final String profilePicUrl = "https://instagram.fnag1-1.fna.fbcdn.net/vp/92a15d2c91d06d45f9a5b72ffd4cf3bd/5D84C9FD/t51.2885-19/s150x150/54731743_2011997322443409_3029283709959274496_n.jpg?_nc_ht=instagram.fnag1-1.fna.fbcdn.net";
	public static final String images[] = {profilePicUrl,
		"https://blog.rackspace.com/wp-content/uploads/2018/09/pumping-iron-arnold-schwarzenegger-1-1108x0-c-default.jpg",
		"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSijnCjlpixxnEcvYeKm-1pg6s2ohuD2VMcMoIzTZInCSZ57SJN",
		"https://pbs.twimg.com/profile_images/798351849984294912/okhePpJW.jpg",
		"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhuaOnKGXWUAV7UMA9UhUQB66kaIne0HYKUDOgfzr8dCO2tchv"
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}
	
	
	private void initialize() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
		fab = findViewById(R.id.storyButton);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(),Capture.class));
			}
		});
		loadFragment(new HomeFragment());               //Default is home fragment
		
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				
				switch (menuItem.getItemId()) {
					case R.id.home:
						return loadFragment(new HomeFragment());
					case R.id.search:
						return loadFragment(new SearchFragment());
					case R.id.notifications:
						return loadFragment(new NotificationsFragment());
					case R.id.profile:
						return loadFragment(new ProfileFragment());
				}
				
				return false;
			}
		});
	}
	
	
	private boolean loadFragment(Fragment fragment){
		
		if (fragment != null) {
			FragmentManager fm  = getSupportFragmentManager();
			fm.beginTransaction()
				.replace(R.id.container, fragment)
				.commit();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
}
