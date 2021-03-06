package com.example.mattyice.ultibook;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
/**
 * Created by Matty Ice on 3/26/2015.
 */
public class PlaybookListActivity extends SingleFragmentActivity
        implements PlaybookListFragment.Callbacks {
    @Override
    protected Fragment createFragment(){
        return new PlaybookListFragment();
    }

    @Override
    protected int getLayoutResId(){
        return R.layout.activity_masterdetail;
    }

    public void onPlaybookSelected(Playbook playbook) {
        if (findViewById(R.id.detailFragmentContainer) == null){
            //Start an instance of PlayListActivity
            Intent i = new Intent(this, PlayListActivity.class);
            i.putExtra(PlayListFragment.SELECTED_PLAYBOOK_ID, playbook.getName());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = PlayListFragment.newInstance(playbook.getName());

            if (oldDetail != null){
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }
}
