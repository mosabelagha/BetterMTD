package me.hyunbin.transit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Hyunbin on 3/10/15.
 */
public class FavoritesAdapter extends RecyclerView.Adapter
        <FavoritesAdapter.ListItemViewHolder> {

    public final static String ARG_STOPID = "cashpa.bettermtd.STOPID";
    public final static String ARG_STOPNAME = "cashpa.bettermtd.STOPNAME";

    ArrayList<HashMap<String, String>> items;
    private static Context sContext;
    AdapterViewCompat.OnItemClickListener mItemClickListener;

    FavoritesAdapter(Context context, ArrayList<HashMap<String, String>> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
        this.sContext = context;
        this.items = modelData;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.favorites_stop,
                        viewGroup,
                        false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(
            ListItemViewHolder viewHolder, int position) {
        final HashMap<String, String> model = items.get(position);
        viewHolder.stopName.setText(model.get("stop_name"));
        viewHolder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StopActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(ARG_STOPID, model.get("stop_id"));
                intent.putExtra(ARG_STOPNAME, model.get("stop_name"));
                sContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAllItems(ArrayList<HashMap<String, String>> newItems){
        for(int n = 0 ; n < newItems.size() ; n++) {
            items.add(newItems.get(n));
            notifyItemInserted(items.size() - 1);
        }
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView stopName;
        View mRootView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            // TODO populate ViewHolder with more items
            stopName = (TextView) itemView.findViewById(R.id.stopName);
            mRootView = itemView.findViewById(R.id.ripple);
        }
    }

}
