package com.example.mattyice.ultibook;

import android.support.v4.app.Fragment;

/**
 * Created by Matty Ice on 4/8/2015.
 */
public class PlayListActivity extends SingleFragmentActivity {
    protected Fragment createFragment(){
        String playbook = getIntent().getStringExtra(PlayListFragment.SELECTED_PLAYBOOK_ID);
        return new PlayListFragment().newInstance(playbook);
    }
}