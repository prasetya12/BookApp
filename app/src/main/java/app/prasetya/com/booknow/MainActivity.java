package app.prasetya.com.booknow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import app.prasetya.com.booknow.Adapter.RecyclerViewAdapter;
import app.prasetya.com.booknow.Model.DataModelRoom;
import app.prasetya.com.booknow.Model.RoomModel;

public class MainActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mToolbar;

    private RecyclerView recyclerView;

    private List<RoomModel> listroom;

    public static int currentItem;
    public static View.OnClickListener mainOnclickListener;

    @Override
    public void onBackPressed() {
        showAlert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        mainOnclickListener = new MainOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<RoomModel> mainData = new ArrayList<RoomModel>();

        for(int i = 0; i< DataModelRoom.Image.length; i++){
            mainData.add(new RoomModel(
                    DataModelRoom.Image[i],
                    DataModelRoom.TittleRoom[i],
                    DataModelRoom.priceRoom[i]
            ));
        }

        RecyclerView.Adapter adapter = new RecyclerViewAdapter(mainData);
        recyclerView.setAdapter(adapter);



    }

    private void initToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Book Now");
    }

    private class MainOnClickListener implements View.OnClickListener {
        private final Context context;
        public MainOnClickListener(Context c) {
            this.context = c;
        }

        @Override
        public void onClick(View view) {
            currentItem = recyclerView.getChildPosition(view);
            startActivity(new Intent(getApplicationContext(),DetailActivity.class));

        }
    }

    private void showAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("EXIT");

        alertDialogBuilder.setMessage("Do You Want Exit ?")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
