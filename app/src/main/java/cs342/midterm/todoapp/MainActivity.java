package cs342.midterm.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView taskList;
    TaskArrayAdapter allTasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Task> tasks = FileUtil.getTasksFromFile(this);
        TaskArrayAdapter adapter = new TaskArrayAdapter(this, tasks);
        setContentView(R.layout.activity_main);
        taskList = (ListView) findViewById(R.id.todo_list);
        allTasksAdapter = adapter;
        taskList.setAdapter(adapter);
    }

    public void searchTask(View view) {
        EditText queryView = (EditText) findViewById(R.id.filter_query);
        String query = queryView.getText().toString();
        Log.d(TAG, "Query searched: " + query);

        // Modify adapter to only contain tasks that contain query result
        ArrayList<Task> resultTasks = new ArrayList<Task>();
        for (int i = 0; i < allTasksAdapter.getCount(); i++) {
            Task task = allTasksAdapter.getItem(i);
            String title = task.getTitle();
            String description = task.getDescription();
            if (title.toLowerCase().contains(query.toLowerCase())
                    || description.toLowerCase().contains(query.toLowerCase())) {
                resultTasks.add(task);
            }
        }
        TaskArrayAdapter modifiedAdapter = new TaskArrayAdapter(this, resultTasks);
        taskList.setAdapter(modifiedAdapter);
    }

    public void clearSearch(View view) {
        taskList.setAdapter(allTasksAdapter);
    }
}
