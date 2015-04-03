package com.example.mattyice.ultibook;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        }
    }
}
