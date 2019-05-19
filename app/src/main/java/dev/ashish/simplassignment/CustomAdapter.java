package dev.ashish.simplassignment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Provide views to RecyclerView with data from dataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private static final String TAG = "CustomAdapter";
    Comparable[] receivedData = null;
    private int size;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param receivedData String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(Comparable[] receivedData) {
        size = receivedData.length;
        this.receivedData = receivedData;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        // Get element from your dataset at this position and replace the contents of the view
        // with that element

        String entry = (String) receivedData[position];
        viewHolder.getTextView().setText(entry);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return size;
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt1;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            txt1 = v.findViewById(R.id.txt1);
        }

        public TextView getTextView() {
            return txt1;
        }

    }
}
