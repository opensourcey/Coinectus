package tk.tejanikaushik.coinectus.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tk.tejanikaushik.coinectus.DataTest.Data;
import tk.tejanikaushik.coinectus.R;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    String TAG = "Test@Kaushik@2019@adapter";
    private ArrayList<Data> data ;

    public RecyclerviewAdapter(ArrayList<Data> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, time, amount, reason;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            time = itemView.findViewById(R.id.time);
            amount = itemView.findViewById(R.id.amount);
            reason = itemView.findViewById(R.id.reason);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Data mData = data.get(i);
        int id = mData.getId();
        String mID = String.valueOf(id);
        viewHolder.id.setText(mID);
        viewHolder.time.setText(mData.getTime());
        // Amount
        if (Integer.parseInt(mData.getAmountGet()) != 0) {
            viewHolder.amount.setText(" + "+mData.getAmountGet());
        } else {
            viewHolder.amount.setText(" - "+mData.getAmountUse());
        }
        viewHolder.reason.setText(mData.getReason());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
