package tk.tejanikaushik.coinectus;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tk.tejanikaushik.coinectus.DB.DBHelper;

public class GetAmount extends AppCompatActivity {
    private String date;
    private TextView amount, reason;
    private String amountString, reasonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_amount);

        Button submit;


        submit = findViewById(R.id.submit_btn_id_get);



        //..
        //Get Current Time
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy 'at' h:mm a z");
        date = df.format(Calendar.getInstance().getTime());

        //..
        //InsertData Into DataBase

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = findViewById(R.id.amount_id_get);
                reason = findViewById(R.id.reason_id_get);

                //..
                //Convert into String
                amountString = amount.getText().toString();
                reasonString = reason.getText().toString();

                //..
                //CCheck not empty
                if (TextUtils.isEmpty(amountString)) {
                    amount.setError("Enter Amount!");
                } else if (TextUtils.isEmpty(reasonString)) {
                    reason.setError("Enter Reason!");
                }

                //..
                //Conform Info
                AlertDialog.Builder builder;
                AlertDialog alertDialog;

                builder = new AlertDialog.Builder(GetAmount.this);
                builder.setTitle("Do you want to continue?");
                builder.setMessage("Amount : + "+amountString+ "\nReason : "+reasonString);
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
                        DBHelper dbHelper = new DBHelper(GetAmount.this);
                        long id = dbHelper.insertNote(date, amountString, "0", reasonString);
                        if (id == -1) {
                            Toast.makeText(GetAmount.this,"Oops!",Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(GetAmount.this, HomeList.class);
        startActivity(intent);
        finish();
    }
}
