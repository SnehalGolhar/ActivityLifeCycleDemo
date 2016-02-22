package com.example.vaibhav.helloapplication;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.app.Dialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    TextView myTextView;
    int count=0;
    Timer T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            count = bundle.getInt("count");

        }


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myTextView=(TextView)findViewById(R.id.txtTimer);

        T=new Timer();

        T.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        myTextView.setText("count     "+count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);

        Button button = (Button) findViewById(R.id.btndiag);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    onPause();
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Now activity A is in pause state,timer will run in background");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

//on button click application will stop
        Button buttonc = (Button) findViewById(R.id.btnCloseApp);
        buttonc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    quitApp();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        final Context context = this;
        Button buttonb = (Button) findViewById(R.id.btnB);
        buttonb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    Bundle bundle = new Bundle();
                    bundle.putInt("count",count);
                    Intent intent = new Intent(context, Main2Activity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        T.cancel();
        Log.d("stopped",count+"");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myTextView.setText("Thread Counter" + count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d("pause", "activity is paused");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void quitApp() {
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        System.exit(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
