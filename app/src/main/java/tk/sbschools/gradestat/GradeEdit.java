package tk.sbschools.gradestat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GradeEdit extends AppCompatActivity {
    ListView display;
    ArrayList<String> courseList;
    ArrayList<Double> gradeList;
    ArrayList<Integer> weightingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_edit);

        courseList = new ArrayList<>();
        gradeList = new ArrayList<>();
        weightingList = new ArrayList<>();

        display = (ListView)findViewById(R.id.ListView_editGrade);

    }

    public class CustomAdapter extends ArrayAdapter<String> {
        List courseList,gradeList,weightingList;
        Context mainContext;

        public CustomAdapter(Context context, int resource, List<String> course, List<Double> grade, List<Integer> weight) {
            super(context, resource, course);

            mainContext = context;
            courseList = course;
            gradeList = grade;
            weightingList = weight;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View layoutView = inflater.inflate(R.layout.grade_edit_list,null);
            EditText courseEdit = (EditText)findViewById(R.id.editText_courseName);
            EditText gradeEdit = (EditText)findViewById(R.id.editText_grade);
            Spinner weightingselect = (Spinner) findViewById(R.id.spinner_weighting);


            List<String> categories = new ArrayList<String>();
            categories.add("Regular");
            categories.add("Honors");
            categories.add("AP");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(GradeEdit.this, R.layout.support_simple_spinner_dropdown_item, categories);
            weightingselect.setAdapter(dataAdapter);

            weightingselect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            return layoutView;
        }
    }
}
