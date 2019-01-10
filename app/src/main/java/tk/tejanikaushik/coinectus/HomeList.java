package tk.tejanikaushik.coinectus;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import java.util.ArrayList;

import tk.tejanikaushik.coinectus.DB.DBHelper;
import tk.tejanikaushik.coinectus.DataTest.Data;
import tk.tejanikaushik.coinectus.RecyclerView.RecyclerviewAdapter;

public class HomeList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Data
    private ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);

        getAllData();
        recyclerViewCon();


        //..
        //Floating Button
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabOnClick();
            }
        });
    }

    public void recyclerViewCon() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerviewAdapter(data);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void getAllData() {
        DBHelper dbHelper = new DBHelper(this);
//        data = new ArrayList<>();
        data = dbHelper.getAllNotes();
        if (data.isEmpty()) {
            TextView error = findViewById(R.id.nothing_error);
            error.setText("Transaction Not Found.");
        }
    }

    public void fabOnClick() {
        new BottomSheet.Builder(this).setSheet(R.menu.menu_add).setTitle("Amount").grid().setListener(new BottomSheetListener() {
            @Override
            public void onSheetShown(@NonNull BottomSheet bottomSheet, @Nullable Object o) {

            }

            @Override
            public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem, @Nullable Object o) {
                switch (menuItem.getItemId()) {
                    case R.id.addAmount_menu:
                        Intent intent = new Intent(HomeList.this, GetAmount.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.useAmount_menu:
                        Intent intent1 = new Intent(HomeList.this, UseAmount.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
            }

            @Override
            public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @Nullable Object o, int i) {

            }
        }).show();
    }
}
