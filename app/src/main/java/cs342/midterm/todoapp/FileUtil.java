package cs342.midterm.todoapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by tlaminator on 3/6/17.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static ArrayList<Task> getTasksFromFile(Context ctx) {
        ArrayList<Task> taskArr = new ArrayList<Task>();
        String line;

        try {
            InputStream inputStream = ctx.getResources().openRawResource(R.raw.tasks);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bf = new BufferedReader(reader);

            while ((line = bf.readLine()) != null) {
                String[] parts = line.split(" ;; ");
                String title = parts[0];
                String description = parts[1];
                String priority = parts[2];

                Log.d(TAG, "Task details:");
                Log.d(TAG, "title: " + title);
                Log.d(TAG, "description: " + description);
                Log.d(TAG, "priority: " + priority);

                Task newTask = new Task();
                newTask.setTitle(title);
                newTask.setDescription(description);
                newTask.setPriority(Integer.parseInt(priority));
                taskArr.add(newTask);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException thrown");
            e.printStackTrace();
        }

        return taskArr;
    }
}
