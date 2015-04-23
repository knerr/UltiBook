package com.example.mattyice.ultibook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Matty Ice on 4/23/2015.
 */
public class EditStageFragment extends DialogFragment {
    private Stage mStage;

    public static EditStageFragment newInstance(Stage stage){
        Bundle args = new Bundle();
        args.putSerializable("stage", stage);

        EditStageFragment fragment = new EditStageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_stage, null);
        mStage = (Stage)getArguments().getSerializable("stage");

        //Play Name Text
        final EditText playName = (EditText)v.findViewById(R.id.editstage_name);
        playName.setText(mStage.getName());

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setPositiveButton(R.string.editstage_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (mStage.getName().isEmpty()){
                            //FAIL!
                        }
                        else {
                            mStage.setName(playName.getText().toString());
                        }
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(R.string.editstage_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED);
                    }
                })
                .create();


    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        //i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
