package com.example.mattyice.ultibook;

import android.support.v4.app.Fragment;

/**
 * Created by Matty Ice on 4/23/2015.
 */
public class PlayDisplayActivity extends SingleFragmentActivity {
    protected Fragment createFragment(){
        Stage stage = (Stage)getIntent().getSerializableExtra(PlayDisplayFragment.SELECTED_STAGE);
        return new PlayDisplayFragment().newInstance(stage);
    }
}
