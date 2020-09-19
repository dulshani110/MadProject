package com.example.maddbnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditGuEst extends AppCompatActivity {

    private EditText edittxtname,edittxtemail;
   // private TextView txtEditguest;
    private Button buttonEdit;

    private DbHandler dbHandler;
    private Context context;
    private Long updatedDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gu_est);

        context=this;
        dbHandler=new DbHandler(context);

        edittxtname=findViewById(R.id.edittxtname);
        edittxtemail=findViewById(R.id.edittxtemail);
       // txtEditguest=findViewById(R.id.txtEditguest);
        buttonEdit=findViewById(R.id.buttonEdit);


        //to get the value
        final String id=getIntent().getStringExtra("id");
        GuEst guEst = dbHandler.getSingleGuEst(Integer.parseInt(id));

        edittxtname.setText(guEst.getName());
        edittxtemail.setText(guEst.getEmail());
        System.out.println(id);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtname =edittxtname.getText().toString();
                String txtemail =edittxtemail.getText().toString();

                updatedDate=System.currentTimeMillis();

                GuEst guEst = new GuEst(Integer.parseInt(id),txtname,txtemail,updatedDate,0);

                int state=dbHandler.updateSingleGuEst(guEst);
                System.out.println(state);
                startActivity(new Intent(context,MainActivity.class));



            }
        });





    }
}