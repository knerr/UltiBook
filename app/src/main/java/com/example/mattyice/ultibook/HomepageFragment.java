package com.example.mattyice.ultibook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Matty Ice on 3/23/2015.
 */
public class HomepageFragment extends Fragment {

    public final static String NEWPLAY = "NewPlay";
    public final static int NEWPLAYINPUT = 0;

    private Button mNewPlay;
    private Button mLoadPlay;
    private Button mPlaybooks;
    private Button mRecentPlay;
    private Button mRecentPlaybook;

    Play mPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, parent, false);

        mNewPlay = (Button)v.findViewById(R.id.homepage_new_play);
        mNewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                NewPlayFragment dialog = NewPlayFragment.newInstance();
                dialog.setTargetFragment(HomepageFragment.this, NEWPLAYINPUT);
                dialog.show(fm, NEWPLAY);
            }
        });

        mLoadPlay = (Button)v.findViewById(R.id.homepage_load_play);
        mLoadPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PlayListActivity.class);

                startActivity(i);
            }
        });

        mPlaybooks = (Button)v.findViewById(R.id.homepage_load_playbooks);
        mPlaybooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PlaybookListActivity.class);

                startActivity(i);
            }
        });

        mRecentPlay = (Button)v.findViewById(R.id.homepage_load_recent_play);
        mRecentPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRecentPlaybook = (Button)v.findViewById(R.id.homepage_load_recent_playbook);
        mRecentPlaybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == NEWPLAYINPUT) {
            mPlay = (Play)data.getSerializableExtra(NewPlayFragment.EXTRA_PLAY);

            //Start up the stage viewer activity
            Intent i = new Intent(getActivity(), StageListActivity.class);
            i.putExtra(StageListFragment.SELECTED_PLAY, mPlay);
            startActivity(i);
        }


    }
}

