package com.example.maddbnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private ListView guestList;
    private TextView guestcount;
    Context context;

    private DbHandler dbHandler;
    private List<GuEst> guEsts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        dbHandler =new DbHandler(context);

        add=findViewById(R.id.add);
        guestList=findViewById(R.id.guestList);
        guestcount=findViewById(R.id.guestcount);
        //context=this;
        guEsts=new ArrayList<>();

        guEsts=dbHandler.getAllGuEsts();

        GuEstAdapter adapter=new GuEstAdapter(context,R.layout.single_guest,guEsts);
        guestList.setAdapter(adapter);
        //get guest counts from the table
        int countGuEst=dbHandler.countGuEst();
        guestcount.setText("You have "+countGuEst+" guests");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddGuEst.class));

            }
        });
        guestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final GuEst guEst=guEsts.get(i);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(guEst.getName());
                builder.setMessage(guEst.getEmail());
                //builder.show();

                //create the buttons in alert dialog box
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            guEst.setFinished(System.currentTimeMillis());
                            dbHandler.updateSingleGuEst(guEst);
                            startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            dbHandler.deleteGuEst(guEst.getId());
                            startActivity(new Intent(context,MainActivity.class));

                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // startActivity(new Intent(context,EditGuEst.class));
                        Intent intent=new Intent(context,EditGuEst.class);
                        intent.putExtra("id",String.valueOf(guEst.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();


            }
        });



    }

}