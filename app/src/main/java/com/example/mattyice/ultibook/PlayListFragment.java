package com.example.mattyice.ultibook;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 4/8/2015.
 */
public class PlayListFragment extends ListFragment {
    public static final String SELECTED_PLAYBOOK_ID = "com.example.mattyice.ultibook.playbook_id";
    public final static String NEWPLAY = "NewPlay";
    public final static int NEWPLAYINPUT = 0;

    private ArrayList<Play> mPlayArrayList;
    private String mPlaybook;

    @Override
    public void onCreate (Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mPlaybook = getArguments().getString(SELECTED_PLAYBOOK_ID); //Way to call the contained value within the arguments


        mPlayArrayList = new ArrayList<>();
        if (mPlaybook != null){
            mPlayArrayList = PlayLab.get(getActivity()).getPlaybookPlays(mPlaybook);
            getActivity().setTitle(mPlaybook);
        }
        else{
            mPlayArrayList = PlayLab.get(getActivity()).getPlays();
            getActivity().setTitle(R.string.playlist_title);
        }

        PlayAdapter adapter = new PlayAdapter(mPlayArrayList);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)v.findViewById(android.R.id.list);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            //floating context menus
            registerForContextMenu(listView);
        }
        else {
            //contextual action bar
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.play_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item_delete_play:
                            PlayAdapter adapter = (PlayAdapter)getListAdapter();
                            PlayLab playLab = PlayLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--){
                                if (getListView().isItemChecked(i)) {
                                    playLab.deletePlay(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        ((PlayAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_play_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.play_list_new_play:
                //ADD WHEN JUNK HAPPENS IN HERE
                FragmentManager fm = getActivity().getSupportFragmentManager();
                NewPlayFragment dialog;
                if (mPlaybook == null){
                    dialog = NewPlayFragment.newInstance();
                }
                else {
                    dialog = NewPlayFragment.newInstance(mPlaybook);
                }
                dialog.setTargetFragment(this, NEWPLAYINPUT);
                dialog.show(fm, NEWPLAY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {}
        else{
            ((PlayAdapter)getListAdapter()).notifyDataSetChanged();

            Play mPlay = (Play)data.getSerializableExtra(NewPlayFragment.EXTRA_PLAY);

            //Start up the stage viewer activity
            Intent i = new Intent(getActivity(), StageListActivity.class);
            i.putExtra(StageListFragment.SELECTED_PLAY, mPlay);
            startActivity(i);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Play p = ((PlayAdapter)getListAdapter()).getItem(position);

        //Starting a different activity
        Intent i = new Intent(getActivity(), StageListActivity.class);
        i.putExtra(StageListFragment.SELECTED_PLAY, p);
        startActivity(i);
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
