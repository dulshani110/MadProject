package com.example.maddbnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddGuEst extends AppCompatActivity {

   private EditText txtname,txtemail;
   private TextView txtAddguest;
   private Button buttonAdd;
   private DbHandler dbHandler;
   private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gu_est);

        txtname=findViewById(R.id.txtname);
        txtemail=findViewById(R.id.txtemail);
        txtAddguest=findViewById(R.id.txtAddguest);
        buttonAdd=findViewById(R.id.buttonAdd);
        context=this;
        dbHandler=new DbHandler(context);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=txtname.getText().toString();
                String useremail=txtemail.getText().toString();
                long started=System.currentTimeMillis();

                GuEst guEst= new GuEst(username,useremail,started,0);
                dbHandler.addGuEst(guEst);


                startActivity(new Intent(context,MainActivity.class));

            }
        });

    }


}