package com.example.mattyice.ultibook;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class PlaybookListFragment extends ListFragment {

    private ArrayList<Playbook> mPlaybookArrayList;

    @Override
    public void onCreate (Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.playbooklist_title);
        mPlaybookArrayList = PlaybookLab.get(getActivity()).getPlaybooks();

        PlaybookAdapter adapter = new PlaybookAdapter(mPlaybookArrayList);
        setListAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((PlaybookAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Playbook p = ((PlaybookListFragment.PlaybookAdapter)getListAdapter()).getItem(position);

        //Starting a different activity
        Intent i = new Intent(getActivity(), PlayListActivity.class);
        i.putExtra(PlayListFragment.SELECTED_PLAYBOOK_ID, p.getName());
        startActivity(i);

    }

    public class PlaybookAdapter extends ArrayAdapter<Playbook> {
        public PlaybookAdapter(ArrayList<Playbook> playbooks) {super(getActivity(), 0, playbooks);}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //If not given a view, inflate one
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_playbook, null);
            }

            //Configure view for crime here!!
            Playbook p = getItem(position);

            //Put the contents of the playbook into the view!
            TextView playbookName = (TextView)convertView.findViewById(R.id.playbooklist_title);
            playbookName.setText(p.getName());
            TextView numOfPlays = (TextView)convertView.findViewById(R.id.playbooklist_numberofplays);
                numOfPlays.setText("Number Of Plays: " + Integer.toString(p.getPlays().size()));

            return convertView;
        }
    }
}
