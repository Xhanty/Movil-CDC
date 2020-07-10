package com.admin.cdc.movil;

import com.admin.cdc.movil.Utils.*;
import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;
import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public class InfoIndexActivity extends TutorialActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new DatabaseHelper(this);

        addFragment(
                new PermissionStep
                        .Builder()
                        .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .setTitle(getString(R.string.permission_title)).setContent(getString(R.string.permission_detail))
                        .setBackgroundColor(Color.parseColor("#FF0957"))
                        .setDrawable(R.drawable.ss_1)
                        .setSummary(getString(R.string.continue_and_learn))
                        .build());

        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.result_awesome))
                        .setContent(getString(R.string.after_updating))
                        .setBackgroundColor(Color.parseColor("#CA70F3"))
                        .setDrawable(R.drawable.ss_4)
                        .setSummary(getString(R.string.thank_you))
                        .build());
    }

    @Override
    public void finishTutorial() {
        boolean isInserted = myDB.insertData(1);

        if(isInserted == true){
            Toast.makeText(this, "Finalizado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "A ocurrido un error, intentalo más tarde", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void currentFragmentPosition(int position) {
        //Toast.makeText(this, "Pantalla: " + position, Toast.LENGTH_SHORT).show();
    }
}