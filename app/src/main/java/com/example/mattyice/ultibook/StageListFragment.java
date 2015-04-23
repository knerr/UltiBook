package com.example.mattyice.ultibook;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 4/16/2015.
 */
public class StageListFragment extends ListFragment {
    public static final String SELECTED_PLAY = "com.example.mattyice.ultibook.play";

    public final static String EDITSTAGE = "NewStage";
    public final static int EDITSTAGEINPUT = 0;
    public final static String COPYSTAGE = "CopyStage";
    public final static int COPYSTAGEINPUT = 1;

    private ArrayList<Stage> mStageArrayList;
    private Play mPlay;
    private Stage mCurrentStage;
    private Callbacks mCallbacks;

    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onStageSelected(Stage stage);
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
    public void onCreate (Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mPlay = (Play)getArguments().getSerializable(SELECTED_PLAY); //Way to call the contained value within the arguments
        getActivity().setTitle(R.string.stagelist_title);

        mStageArrayList = new ArrayList<>();
        if (!mPlay.getName().isEmpty()){
            mStageArrayList = StageLab.get(getActivity()).getPlayStages(mPlay.getName());
            getActivity().setTitle(mPlay.getName());
        }
        //CAN'T HAVE AN EMPTY STAGE LIST!
        if (mStageArrayList.isEmpty()){
            Stage stage = new Stage();
            stage.setName("Stage 1");
            stage.setPlay(mPlay.getName());
            stage.setOffensiveDots(new ArrayList<Dot>(mPlay.getOffensivePlayers()));
            stage.setDefensiveDots(new ArrayList<Dot>(mPlay.getDefensivePlayers()));
            stage.setConeDots(new ArrayList<Dot>(mPlay.getCones()));
            mStageArrayList.add(stage);
        }

        mCurrentStage = mStageArrayList.get(0);


        StageAdapter adapter = new StageAdapter(mStageArrayList);
        setListAdapter(adapter);

        //Open up the first stage in the viewer
        Stage s = ((StageAdapter)getListAdapter()).getItem(0);
        mCallbacks.onStageSelected(s);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((StageAdapter)getListAdapter()).notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)v.findViewById(android.R.id.list);
        registerForContextMenu(listView);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_stage_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.stage_list_new_stage:
                //If any stages exist, just copy over the last one
                if (!mStageArrayList.isEmpty()) {
                    Stage copiedStage = mStageArrayList.get(mStageArrayList.size() - 1);
                    copiedStage.setName("New Stage");
                    mStageArrayList.add(copiedStage);
                } else {
                    //Or else create one with data from the play that was create
                    Stage newStage = new Stage();
                    newStage.setName("Stage 1");
                    newStage.setPlay(mPlay.getName());
                    newStage.setOffensiveDots(new ArrayList<Dot>(mPlay.getOffensivePlayers()));
                    newStage.setDefensiveDots(new ArrayList<Dot>(mPlay.getDefensivePlayers()));
                    newStage.setConeDots(new ArrayList<Dot>(mPlay.getCones()));

                    mStageArrayList.add(newStage);
                }
                ((StageAdapter)getListAdapter()).notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Stage s = ((StageAdapter)getListAdapter()).getItem(position);

        /*
        //Starting a different activity
        Intent i = new Intent(getActivity(), PlayListActivity.class);
        i.putExtra(PlayListFragment.SELECTED_PLAYBOOK_ID, p.getName());
        startActivity(i);
        */
        //Detail pane function being run. Found in playbook list activity
        mCallbacks.onStageSelected(s);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getActivity().getMenuInflater().inflate(R.menu.stage_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        StageAdapter adapter = (StageAdapter)getListAdapter();
        Stage stage = adapter.getItem(position);

        switch (item.getItemId()){
            case R.id.menu_item_delete_stage:
                StageLab.get(getActivity()).deleteStage(stage);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_item_edit_stage:
                FragmentManager editfm = getActivity().getSupportFragmentManager();
                EditStageFragment editdialog = EditStageFragment.newInstance(stage);
                editdialog.setTargetFragment(this, EDITSTAGEINPUT);
                editdialog.show(editfm, EDITSTAGE);
                return true;
            case R.id.menu_item_copy_stage:
                FragmentManager copyfm = getActivity().getSupportFragmentManager();
                CopyStageFragment copydialog = CopyStageFragment.newInstance(stage);
                copydialog.setTargetFragment(this, COPYSTAGEINPUT);
                copydialog.show(copyfm, COPYSTAGE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {}
        else{
            ((StageAdapter)getListAdapter()).notifyDataSetChanged();
        }
    }

    public class StageAdapter extends ArrayAdapter<Stage> {
        public StageAdapter(ArrayList<Stage> stages) {super(getActivity(), 0, stages);}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //If not given a view, inflate one
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_stage_info, null);
            }

            //Configure view for crime here!!
            Stage s = getItem(position);

            //Put the contents of the playbook into the view!
            TextView stageName = (TextView)convertView.findViewById(R.id.stagelist_name);
            stageName.setText(s.getName());

            return convertView;
        }
    }

    public static StageListFragment newInstance(Play play){
        Bundle args = new Bundle();
        args.putSerializable(SELECTED_PLAY, play);

        StageListFragment fragment = new StageListFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
