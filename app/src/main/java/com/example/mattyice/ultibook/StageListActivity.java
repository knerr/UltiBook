package com.example.mattyice.ultibook;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Matty Ice on 4/16/2015.
 */
public class StageListActivity extends SingleFragmentActivity
    implements StageListFragment.Callbacks
{
    protected Fragment createFragment(){
        Play play = (Play)getIntent().getSerializableExtra(StageListFragment.SELECTED_PLAY);
        return new StageListFragment().newInstance(play);
    }

    @Override
    protected int getLayoutResId(){
        return R.layout.activity_masterdetail;
    }

    public void onStageSelected(Stage stage) {
        if (findViewById(R.id.detailFragmentContainer) == null){
            //Start an instance of PlayListActivity
            Intent i = new Intent(this, PlayDisplayActivity.class);
            i.putExtra(PlayDisplayFragment.SELECTED_STAGE, stage);
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);

            //IMPLEMENT THE PLAY DISPLAY ACTIVITY HERE
            Fragment newDetail = PlayDisplayFragment.newInstance(stage);

            if (oldDetail != null){
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }
}
