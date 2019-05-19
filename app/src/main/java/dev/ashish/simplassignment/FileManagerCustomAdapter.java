package dev.ashish.simplassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class FileManagerCustomAdapter extends RecyclerView.Adapter<FileManagerCustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    Comparable[] receivedData = null;
    Context context = null;
    private int size;


    public FileManagerCustomAdapter(Comparable[] receivedData, Context applicationContext) {
        context = applicationContext;
        size = receivedData.length;
        this.receivedData = receivedData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.file_manager_single_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        File file = (File) receivedData[position];
        String entry = file.getName();
        viewHolder.getTextView().setText(entry);

        if (file.isDirectory()) {
//            viewHolder.getImageView().setBackground(ContextCompat.getDrawable(context, R.drawable.file) ) ;
            viewHolder.getImageView().setImageResource(R.drawable.folder);
        } else
            viewHolder.getImageView().setImageResource(R.drawable.file);

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt2;
        private final ImageView img2;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    File file = (File) receivedData[i];
                    Log.i("LOL NOTHING", "Element " + file.getName() + " clicked. and " + file.isDirectory());

                    if (file.isDirectory()) {
                        Intent myIntent = new Intent(v.getContext(), FileManager.class);
                        if (file.listFiles() != null) {
                            myIntent.putExtra("list", file.listFiles());
                            v.getContext().startActivity(myIntent);
                        } else {
                            Snackbar.make(v, "Empty Directory", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }

                    } else if (file.isFile()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                        alertDialog.setMessage("Size : " + file.length() + " bytes. \n" + "Last Modified: " + convertDate("" + file.lastModified(), "dd/MM/yyyy hh:mm:ss") + "\n\n" + file.getAbsolutePath());
                        alertDialog.setTitle(file.getName());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }


                }
            });


            txt2 = v.findViewById(R.id.txt2);
            img2 = v.findViewById(R.id.imageView);
        }

        public String convertDate(String dateInMilliseconds, String dateFormat) {
            return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
        }

        public TextView getTextView() {
            return txt2;
        }

        public ImageView getImageView() {
            return img2;
        }
    }
}
