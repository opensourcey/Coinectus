package tk.tejanikaushik.coinectus;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import tk.tejanikaushik.coinectus.DB.DBHelper;

public class UseAmount extends AppCompatActivity {
    private String date;
    private TextView amount, reason;
    private String amountString, reasonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_amount);

        Button submit;


        submit = findViewById(R.id.submit_btn_id_get_use);



        //..
        //Get Current Time
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy 'at' h:mm a z");
        date = df.format(Calendar.getInstance().getTime());

        //..
        //InsertData Into DataBase

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = findViewById(R.id.amount_id_get_use);
                reason = findViewById(R.id.reason_id_get_use);

                //..
                //Convert into String
                amountString = amount.getText().toString();
                reasonString = reason.getText().toString();


                //..
                //Conform Info
                AlertDialog.Builder builder;
                AlertDialog alertDialog;

                builder = new AlertDialog.Builder(UseAmount.this);
                builder.setTitle("Do you want to continue?");
                builder.setMessage("Amount : - "+amountString+ "\nReason : "+reasonString);
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //..
                        //Insert Data
                        DBHelper dbHelper = new DBHelper(UseAmount.this);
                        long id = dbHelper.insertNote(date, "0", amountString, reasonString);
                        if (id == -1) {
                            Toast.makeText(UseAmount.this,"Oops!",Toast.LENGTH_SHORT).show();
                        } else {
                            onBackPressed();
                        }
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UseAmount.this, HomeList.class);
        startActivity(intent);
        finish();
    }
}
