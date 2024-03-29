package com.example.br161.theeleventh;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    private EditText etPrimary;
    private EditText etSecondary;
    private TextView tvSolution;
    private LinearLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrimary = (EditText) findViewById(R.id.et_primary);
        etSecondary = (EditText) findViewById(R.id.et_secondary);
        tvSolution = (TextView) findViewById(R.id.tv_solution);
        fragmentContainer = (LinearLayout) findViewById(R.id.fragment_container);

        //Request instance of SharedPreferences
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);

        //Populate the EditText with shared values
        etPrimary.setText("" +prefs.getInt("first",-1));
        etSecondary.setText("" +prefs.getInt("second",-1));

        tvSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int firstValue = Integer.parseInt(etPrimary.getText().toString());
                int secondValue = Integer.parseInt(etSecondary.getText().toString());

                //Declare and instantiate the fragment
                SumFragment sumFrag = new SumFragment();

                //Create and store two values into bundle
                Bundle bundle = new Bundle();
                bundle.putInt("primary", firstValue);
                bundle.putInt("secondary", secondValue);

                //Set arguments of the SumFragment
                sumFrag.setArguments(bundle);

                //Transition into new fragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, sumFrag)
                        .addToBackStack("")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                //Save values for next time
                //Request instance of SharedPreferences
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                //Crates editor for SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();

                //Gives values to editor
                editor.putInt("first", firstValue);
                editor.putInt("second", secondValue);

                //Saves values to editor
                editor.commit();

            }
        });


    }

}
