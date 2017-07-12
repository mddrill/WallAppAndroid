package com.wallapp.mddrill.wallappandroid.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.wallapp.mddrill.wallappandroid.networklayer.VolleyErrorHelper;
import com.wallapp.mddrill.wallappandroid.R;

/**
 * Created by mddrill on 7/9/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected void popUpError(String title, String message, final DialogInterface.OnClickListener onClick){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay", onClick);
        alertDialog.show();
    }
    protected void popUpNetworkError(VolleyError error){
        popUpError(getString(R.string.network_error_title), VolleyErrorHelper.getMessage(error, this),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
    }
    protected void popUpInvalidResponseError(){
        popUpError(getString(R.string.error), getString(R.string.invalid_json_error),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
    }
}
