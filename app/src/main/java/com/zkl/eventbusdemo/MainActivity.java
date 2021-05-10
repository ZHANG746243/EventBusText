package com.zkl.eventbusdemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

//public class MainActivity extends AppCompatActivity implements PublisherDialogFragment.OnEventListener {
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSuccessEvent(SuccessEvent event){
        setImageSrc(R.drawable.ic_happy);
        int id = event.getId();
        String name = event.getName();
        Log.d(TAG, id + name);
    }

    @Subscribe
    public void onFailureEvent(FailureEvent event){
        setImageSrc(R.drawable.ic_sad);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                // Display dialog fragment (publisher)
                final PublisherDialogFragment fragment = new PublisherDialogFragment();
//                fragment.setEventListener(new PublisherDialogFragment.OnEventListener() {
//                    @Override
//                    public void OnSuccess() {
//                        setImageSrc(R.drawable.ic_happy);
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        setImageSrc(R.drawable.ic_sad);
//                        }
//                });
                fragment.show(getSupportFragmentManager(), "publisher");
            }
        });
    }

    /**
     * Update image resource
     *
     * @param resId
     */
    private void setImageSrc(int resId) {
        final ImageView imageView = (ImageView) findViewById(R.id.emotionImageView);
        imageView.setImageResource(resId);
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


//    @Override
//    public void OnSuccess() {
//        setImageSrc(R.drawable.ic_happy);
//    }
//
//    @Override
//    public void onFailure() {
//        setImageSrc(R.drawable.ic_sad);
//
//    }
}