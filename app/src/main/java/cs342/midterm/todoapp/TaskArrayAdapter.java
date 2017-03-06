package cs342.midterm.todoapp;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by tlaminator on 3/6/17.
 */

public class TaskArrayAdapter extends ArrayAdapter<Task> {
    private static final String TAG = "ArrayAdapter";
    private final Context context;
    ArrayList<Task> tasks;

    public TaskArrayAdapter(Context ctx, ArrayList<Task> tasks) {
        super(ctx, R.layout.task);
        this.context = ctx;

        this.tasks = tasks;
        Log.d(TAG, "Read " + tasks.size() + " Task objects");
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View taskView = inflater.inflate(R.layout.task, parent, false);
        final TextView titleView = (TextView) taskView.findViewById(R.id.task_title);
        final TextView contentView = (TextView) taskView.findViewById(R.id.task_content);
        CheckBox box = (CheckBox) taskView.findViewById(R.id.task_check);

        String title = tasks.get(position).getTitle();
        titleView.setText(title);
        titleView.setTextSize(18);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);

        String description = tasks.get(position).getDescription();
        String priority = Integer.toString(tasks.get(position).getPriority());
        contentView.setText(description + "\nPriority: " + priority);

        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    titleView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    contentView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    titleView.setPaintFlags(0);
                    contentView.setPaintFlags(0);
                }
            }
        });

        return taskView;
    }

    public int getCount() { return tasks.size(); }
    public Task getItem(int position) { return tasks.get(position);}
    public long getItemId(int position) { return position; }

}
