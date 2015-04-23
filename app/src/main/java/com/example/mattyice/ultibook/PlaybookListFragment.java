package com.example.mattyice.ultibook;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class PlaybookListFragment extends ListFragment {

    public final static String NEWPLAYBOOK = "NewPlaybook";
    public final static int NEWPLAYBOOKINPUT = 0;

    private ArrayList<Playbook> mPlaybookArrayList;
    private Callbacks mCallbacks;

    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onPlaybookSelected(Playbook playbook);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.playbooklist_title);
        mPlaybookArrayList = PlaybookLab.get(getActivity()).getPlaybooks();

        PlaybookAdapter adapter = new PlaybookAdapter(mPlaybookArrayList);
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
                    inflater.inflate(R.menu.playbook_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item_delete_playbook:
                            PlaybookAdapter adapter = (PlaybookAdapter)getListAdapter();
                            PlaybookLab playbookLab = PlaybookLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--){
                                if (getListView().isItemChecked(i)) {
                                    playbookLab.deletePlaybook(adapter.getItem(i));
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
        ((PlaybookAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_playbook_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.playbook_list_new_playbook:
                //ADD WHEN JUNK HAPPENS IN HERE
                FragmentManager fm = getActivity().getSupportFragmentManager();
                NewPlaybookFragment dialog = NewPlaybookFragment.newInstance();
                dialog.setTargetFragment(this, NEWPLAYBOOKINPUT);
                dialog.show(fm, NEWPLAYBOOK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {}
        else{
            ((PlaybookAdapter)getListAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Playbook p = ((PlaybookAdapter)getListAdapter()).getItem(position);

        /*
        //Starting a different activity
        Intent i = new Intent(getActivity(), PlayListActivity.class);
        i.putExtra(PlayListFragment.SELECTED_PLAYBOOK_ID, p.getName());
        startActivity(i);
        */
        //Detail pane function being run. Found in playbook list activity
        mCallbacks.onPlaybookSelected(p);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getActivity().getMenuInflater().inflate(R.menu.playbook_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        PlaybookAdapter adapter = (PlaybookAdapter)getListAdapter();
        Playbook playbook = adapter.getItem(position);

        switch (item.getItemId()){
            case R.id.menu_item_delete_playbook:
                PlaybookLab.get(getActivity()).deletePlaybook(playbook);
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onContextItemSelected(item);
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
