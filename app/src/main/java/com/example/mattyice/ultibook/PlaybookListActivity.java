package com.example.mattyice.ultibook;

import android.support.v4.app.Fragment;
/**
 * Created by Matty Ice on 3/26/2015.
 */
public class PlaybookListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new PlaybookListFragment();
    }
}
