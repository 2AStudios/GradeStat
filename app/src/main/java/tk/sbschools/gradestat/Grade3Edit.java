package tk.sbschools.gradestat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grade3Edit extends AppCompatActivity {
    ArrayList<String> courseList, gradeList, weightingList;
    final String file = "data.json";
    ListView display;
    EditText CourseDisp, GradeDisp, weightingDisp;
    Button addList, saveList, delList, doneList;
    static final String COURSELIST = "cList";
    static final String GRADELIST = "gList";
    static final String WEIGHTINGLIST = "wList";
    int currentSelection = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_edit3);
        setTitle("Edit Courses");

        courseList = getIntent().getStringArrayListExtra(COURSELIST);
        gradeList = getIntent().getStringArrayListExtra(GRADELIST);
        weightingList = getIntent().getStringArrayListExtra(WEIGHTINGLIST);
        display = (ListView)findViewById(R.id.listView_display);
        CourseDisp = (EditText)findViewById(R.id.editText_name);
        GradeDisp = (EditText) findViewById(R.id.editText_URL);
        weightingDisp = (EditText) findViewById(R.id.editText_Desc);
        addList = (Button) findViewById(R.id.button_add);
        saveList = (Button)findViewById(R.id.button_edit);
        delList = (Button)findViewById(R.id.button_del);
        doneList = (Button)findViewById(R.id.button_Done);

        final CustomAdapter myAdapter = new CustomAdapter(this,R.layout.grade_edit_list,courseList,gradeList);

        display.setAdapter(myAdapter);

        display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseDisp.setText(courseList.get(position));
                GradeDisp.setText(gradeList.get(position));
                weightingDisp.setText(weightingList.get(position));
                currentSelection = position;
            }
        });

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseList.add(currentSelection+1, CourseDisp.getText().toString());
                gradeList.add(currentSelection+1, GradeDisp.getText().toString());
                weightingList.add(currentSelection+1, weightingDisp.getText().toString());
                CourseDisp.setText("");
                GradeDisp.setText("");
                weightingDisp.setText("");
                currentSelection = -1;
                myAdapter.notifyDataSetChanged();
            }
        });

        saveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSelection != -1){
                    courseList.set(currentSelection, CourseDisp.getText().toString());
                    gradeList.set(currentSelection, GradeDisp.getText().toString());
                    weightingList.set(currentSelection, weightingDisp.getText().toString());
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        delList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelection != -1){
                    courseList.remove(currentSelection);
                    gradeList.remove(currentSelection);
                    weightingList.remove(currentSelection);
                    CourseDisp.setText("");
                    GradeDisp.setText("");
                    weightingDisp.setText("");
                    currentSelection = -1;
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        doneList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject savedData = new JSONObject();
                try {
                    JSONArray cJSONArray = new JSONArray(courseList);
                    JSONArray gJSONArray = new JSONArray(gradeList);
                    JSONArray wJSONArray = new JSONArray(weightingList);
                    Log.d("debug",cJSONArray.toString());
                    Log.d("debug",gJSONArray.toString());
                    Log.d("debug",wJSONArray.toString());
                    savedData.put("cList",cJSONArray);
                    savedData.put("gList",gJSONArray);
                    savedData.put("wList",wJSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_WORLD_WRITEABLE));
                    writer.write(savedData.toString());
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent();
                intent.putExtra("USEDATA",true);
                intent.putExtra(COURSELIST, courseList);
                intent.putExtra(GRADELIST,gradeList);
                intent.putExtra(WEIGHTINGLIST,weightingList);
                //intent.putExtra(CURRENT_SEL,currentSelection);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("USEDATA", false);
        setResult(RESULT_OK, intent);
        finish();
    }

    public class CustomAdapter extends ArrayAdapter<String> {
        List list,gradeList;
        Context mainContext;

        public CustomAdapter(Context context, int resource, List<String> objects, List<String> urls) {
            super(context, resource, objects);

            mainContext = context;
            list = objects;
            gradeList = urls;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View layoutView = inflater.inflate(R.layout.grade_edit_list,null);
            //TextView textView = (TextView)layoutView.findViewById(R.id.textView);
            //TextView urlText = (TextView)layoutView.findViewById(R.id.textView_URL);

            //textView.setText(list.get(position).toString());
            //urlText.setText(gradeList.get(position).toString());
            TextView courseEdit = (TextView)layoutView.findViewById(R.id.textView_course);
            TextView gradeEdit = (TextView)layoutView.findViewById(R.id.textView_grade);
            TextView weightingselect = (TextView) layoutView.findViewById(R.id.textView_weighting);

            //Log.d("Debug",courseList.get(position).toString() + ", " + position);
            courseEdit.setText(courseList.get(position).toString());
            gradeEdit.setText(gradeList.get(position).toString());

            if(weightingList.get(position).equals("4")){
                weightingselect.setText("Regular");
            }else if(weightingList.get(position).equals("4.5")){
                weightingselect.setText("Honors");
            }else if(weightingList.get(position).equals("5")){
                weightingselect.setText("AP");
            }else{
                weightingselect.setText("Custom");
            }
            return layoutView;
        }
    }
}
