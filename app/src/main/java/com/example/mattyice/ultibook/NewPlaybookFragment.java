package com.example.mattyice.ultibook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 4/22/2015.
 */
public class NewPlaybookFragment extends DialogFragment {

    private Playbook mPlaybook;
    public static NewPlaybookFragment newInstance(){
        Bundle args = new Bundle();

        NewPlaybookFragment fragment = new NewPlaybookFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_newplaybook, null);

        final EditText playbookName = (EditText) v.findViewById(R.id.newplaybook_name);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setPositiveButton(R.string.newplaybook_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Playbook> playbookArrayList = PlaybookLab.get(getActivity()).getPlaybooks();
                        if (!playbookName.getText().toString().equals("")) {
                            mPlaybook = new Playbook(playbookName.getText().toString());
                            playbookArrayList.add(mPlaybook);
                        }
                        else {
                            //NO NAME
                        }
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(R.string.newplaybook_cancel, new DialogInterface.OnClickListener() {
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

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, null);
    }
}
