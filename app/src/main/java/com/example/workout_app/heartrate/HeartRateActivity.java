package com.example.workout_app.heartrate;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.workout_app.heartrate.Fragments.HeartRateFragment;

public class HeartRateActivity extends Activity {


    @Override
    protected void onCreate(final Bundle b) {
        super.onCreate(b);

        // Create new fragment and transaction
        Fragment newFragment = new HeartRateFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this
        // fragment,
        // and add the transaction to the back stack
        transaction.replace(android.R.id.content, newFragment);

        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }
}
