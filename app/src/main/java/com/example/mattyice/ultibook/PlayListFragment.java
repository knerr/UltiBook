package com.example.mattyice.ultibook;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 4/8/2015.
 */
public class PlayListFragment extends ListFragment {
    public static final String SELECTED_PLAYBOOK_ID = "com.example.mattyice.ultibook.playbook_id";

    private ArrayList<Play> mPlayArrayList;
    String mPlaybook;

    @Override
    public void onCreate (Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.playlist_title);
        mPlaybook = getArguments().getString(SELECTED_PLAYBOOK_ID); //Way to call the contained value within the arguments

        mPlayArrayList = new ArrayList<>();
        if (mPlaybook != null){
            mPlayArrayList = PlayLab.get(getActivity()).getPlaybookPlays(mPlaybook);
        }
        else{
            mPlayArrayList = PlayLab.get(getActivity()).getPlays();
        }

        PlayAdapter adapter = new PlayAdapter(mPlayArrayList);
        setListAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((PlayAdapter)getListAdapter()).notifyDataSetChanged();
    }

    public class PlayAdapter extends ArrayAdapter<Play> {
        public PlayAdapter(ArrayList<Play> plays) {super(getActivity(), 0, plays);}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //If not given a view, inflate one
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_play_preview, null);
            }

            //Configure view for crime here!!
            Play p = getItem(position);

            //Put the contents of the playbook into the view!
            TextView playName = (TextView)convertView.findViewById(R.id.playlist_name);
            playName.setText(p.getName());
            TextView playType = (TextView)convertView.findViewById(R.id.playlist_type);
            playType.setText(p.getType().toString());
            TextView playOffensivePlayers = (TextView)convertView.findViewById(R.id.playlist_offensive_players);
            playOffensivePlayers.setText("Offensive Players: " + Integer.toString(p.getOffensivePlayers()));
            TextView playDefensivePlayers = (TextView)convertView.findViewById(R.id.playlist_defensive_players);
            playDefensivePlayers.setText("Defensive Players: " + Integer.toString(p.getDefensivePlayers()));
            TextView playCones = (TextView)convertView.findViewById(R.id.playlist_cones);
            playCones.setText("Cones: " + Integer.toString(p.getCones()));

            return convertView;
        }
    }

    public static PlayListFragment newInstance(String playbook){
        Bundle args = new Bundle();
        args.putSerializable(SELECTED_PLAYBOOK_ID, playbook);

        PlayListFragment fragment = new PlayListFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
