/*package tk.sbschools.gradestat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class GradeEdit extends AppCompatActivity {
    ListView display;
    Button saveBtn;
    ArrayList<String> courseList;
    ArrayList<Double> gradeList, weightingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_edit);

        courseList = new ArrayList<>();
        gradeList = new ArrayList<>();
        weightingList = new ArrayList<>();

        courseList.add("English");gradeList.add(97.0);weightingList.add(new Double("5"));
        courseList.add("Math");gradeList.add(92.7);weightingList.add(new Double("4"));
        courseList.add("Computer Science");gradeList.add(53.2);weightingList.add(new Double("4.5"));
        courseList.add("Biology");gradeList.add(100.2);weightingList.add(new Double("5"));

        display = (ListView)findViewById(R.id.ListView_editGrade);
        saveBtn = (Button)findViewById(R.id.button_editCourses);

        final CustomAdapter courseListEdit = new CustomAdapter(this,R.layout.grade_edit_list,courseList,gradeList,weightingList);

        display.setAdapter(courseListEdit);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for(int i=0;i<display((ArrayAdapter) display.getAdapter()).getItem()
                try {
                    JSONObject savedData = new JSONObject();
                    savedData.put("data",data);
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
            }
        });

    }

    public class CustomAdapter extends ArrayAdapter<String> {
        List courseList,gradeList,weightingList;
        Context mainContext;

        public CustomAdapter(Context context, int resource, List<String> course, List<Double> grade, List<Double> weight) {
            super(context, resource, course);

            mainContext = context;
            courseList = course;
            gradeList = grade;
            weightingList = weight;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grade_edit_list,null);
            EditText courseEdit = (EditText)convertView.findViewById(R.id.editText_courseName);
            EditText gradeEdit = (EditText)convertView.findViewById(R.id.editText_grade);
            Spinner weightingselect = (Spinner) convertView.findViewById(R.id.spinner_weighting);

            //Log.d("Debug",courseList.get(position).toString() + ", " + position);
            courseEdit.setText(courseList.get(position).toString());
            gradeEdit.setText(gradeList.get(position).toString());

            List<String> categories = new ArrayList<String>();
            categories.add("Regular");
            categories.add("Honors");
            categories.add("AP");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(GradeEdit.this, R.layout.support_simple_spinner_dropdown_item, categories);
            weightingselect.setAdapter(dataAdapter);
            if(weightingList.get(position).equals(new Double("4"))){
                weightingselect.setSelection(0);
            }else if(weightingList.get(position).equals(new Double("4.5"))){
                weightingselect.setSelection(1);
            }else if(weightingList.get(position).equals(new Double("5"))){
                weightingselect.setSelection(2);
            }


            weightingselect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            return convertView;
        }
    }
}
*/