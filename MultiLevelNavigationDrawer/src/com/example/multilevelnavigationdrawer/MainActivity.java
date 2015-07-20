package com.example.multilevelnavigationdrawer;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.Type;
import net.simonvt.menudrawer.Position;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public static MenuDrawer mMenuDrawer;

	ImageButton mLevelABtn, mLevelBBtn, mLevelCBtn;
	ListView mLevelAList, mLevelBList, mLevelCList;
	View mLevelAView, mLevelBView, mLevelCView;

	String[] ALevelArray = { "Level A 1", "Level A 2", "Level A 3", "Level A 4" };

	String[][] BLevelArray = {
			{ "Level B 1.1", "Level B 2.1", "Level B 3.1", "Level B 4.1" },
			{ "Level B 1.2", "Level B 2.2", "Level B 3.2", "Level B 4.2" },
			{ "Level B 1.3", "Level B 2.3", "Level B 3.3", "Level B 4.3" },
			{ "Level B 1.4", "Level B 2.4", "Level B 3.4", "Level B 4.4" } };

	String[][] CLevelArray = {
			{ "Level C 1.1", "Level C 2.1", "Level C 3.1", "Level C 4.1" },
			{ "Level C 1.2", "Level C 2.2", "Level C 3.2", "Level C 4.2" },
			{ "Level C 1.3", "Level C 2.3", "Level C 3.3", "Level C 4.3" },
			{ "Level C 1.4", "Level C 2.4", "Level C 3.4", "Level C 4.4" } };

	Animation animLeftToRight, animRightToLeft, exitAnimLeftToRight,
			exitAnimRightToLeft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//
		mMenuDrawer = MenuDrawer.attach(this, Type.OVERLAY, Position.LEFT,
				MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.activity_main);

		// Initialize Views
		mLevelAView = getLayoutInflater()
				.inflate(R.layout.level_a_layout, null);
		mLevelBView = getLayoutInflater()
				.inflate(R.layout.level_b_layout, null);
		mLevelCView = getLayoutInflater()
				.inflate(R.layout.level_c_layout, null);

		// Set menu View/Size/DropShadow/TouchMode
		mMenuDrawer.setMenuView(mLevelAView);
		mMenuDrawer.setMenuSize(750);
		mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
		mMenuDrawer.setDropShadowEnabled(false);

		// Initialize Buttons
		mLevelABtn = (ImageButton) mLevelAView
				.findViewById(R.id.drawer_btn_level_a);
		mLevelBBtn = (ImageButton) mLevelBView
				.findViewById(R.id.drawer_btn_level_b);
		mLevelCBtn = (ImageButton) mLevelCView
				.findViewById(R.id.drawer_btn_level_c);

		// Initialize Buttons
		mLevelAList = (ListView) mLevelAView.findViewById(R.id.a_level_list);
		mLevelBList = (ListView) mLevelBView.findViewById(R.id.b_level_list);
		mLevelCList = (ListView) mLevelCView.findViewById(R.id.c_level_list);

		mLevelAList.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.custom_list_item_1, ALevelArray));

		mLevelAList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// Initialize Animations
				animLeftToRight = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.left_to_right);
				animRightToLeft = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.right_to_left);

				mLevelBView.setAnimation(animLeftToRight);
				mLevelAView.setAnimation(animRightToLeft);

				mMenuDrawer.setMenuView(mLevelBView);

				mLevelBList.setAdapter(new ArrayAdapter<String>(
						MainActivity.this, R.layout.custom_list_item_1,
						BLevelArray[position]));
			}
		});

		mLevelBList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// Initialize Animations
				animLeftToRight = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.left_to_right);
				animRightToLeft = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.right_to_left);

				mLevelCView.setAnimation(animLeftToRight);
				mLevelBView.setAnimation(animRightToLeft);

				mMenuDrawer.setMenuView(mLevelCView);
				mLevelCList.setAdapter(new ArrayAdapter<String>(
						MainActivity.this, R.layout.custom_list_item_1,
						CLevelArray[position]));
			}
		});

		mLevelCList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Toast.makeText(MainActivity.this,
						"Open Fragment No: " + position++, Toast.LENGTH_SHORT)
						.show();
				mMenuDrawer.toggleMenu(true);

			}
		});

		mLevelABtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		mLevelBBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				exitAnimLeftToRight = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.exit_left_to_right);
				exitAnimRightToLeft = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.exit_right_to_left);

				mLevelAView.setAnimation(exitAnimLeftToRight);
				mLevelBView.setAnimation(exitAnimRightToLeft);

				mMenuDrawer.setMenuView(mLevelAView);
			}
		});

		mLevelCBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				exitAnimLeftToRight = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.exit_left_to_right);
				exitAnimRightToLeft = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.exit_right_to_left);

				mLevelBView.setAnimation(exitAnimLeftToRight);
				mLevelCView.setAnimation(exitAnimRightToLeft);

				mMenuDrawer.setMenuView(mLevelBView);
			}
		});

		// mHomeButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// mMenuDrawer.toggleMenu();
		// Log.e("I'M HOME", "I'M HOME");
		// }
		// });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
