package tk.sbschools.gradestat;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    final String file = "data.json";
    JSONObject savedData = new JSONObject();
    ArrayList<String> data;

    ArrayList<String> courseList, gradeList, weightingList;
    static final String COURSELIST = "cList";
    static final String GRADELIST = "gList";
    static final String WEIGHTINGLIST = "wList";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        try {
            savedData = new JSONObject(readFromFile(this,file));
            courseList = new ArrayList<>();
            gradeList = new ArrayList<>();
            weightingList = new ArrayList<>();
            JSONArray jArray = (JSONArray)savedData.get("cList");
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    courseList.add(jArray.getString(i));
                }
            }
            jArray = (JSONArray)savedData.get("gList");
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    gradeList.add(jArray.getString(i));
                }
            }
            jArray = (JSONArray)savedData.get("wList");
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    weightingList.add(jArray.getString(i));
                }
            }
            Log.d("test",courseList.toString());
            //gradeList =(ArrayList<String>)savedData.get("gList");
            Log.d("test",gradeList.toString());
            //weightingList =(ArrayList<String>)savedData.get("wList");
            Log.d("test",weightingList.toString());
        } catch (JSONException e) {
            courseList = new ArrayList<>();
            gradeList = new ArrayList<>();
            weightingList = new ArrayList<>();

            courseList.add("English");gradeList.add("97.0");weightingList.add("5");
            courseList.add("Math");gradeList.add("92.7");weightingList.add("4");
            courseList.add("Computer Science");gradeList.add("50.2");weightingList.add("4.5");
            courseList.add("Biology");gradeList.add("100.2");weightingList.add("5");
            e.printStackTrace();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i = new Intent(Main3Activity.this, Grade3Edit.class);
                i.putExtra(COURSELIST, courseList);
                i.putExtra(GRADELIST,gradeList);
                i.putExtra(WEIGHTINGLIST,weightingList);
                startActivityForResult(i, 10032);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class OverviewFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public OverviewFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static OverviewFragment newInstance(int sectionNumber, ArrayList<String> cList, ArrayList<String> gList, ArrayList<String> wList) {
            OverviewFragment fragment = new OverviewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putStringArrayList("cList",cList);
            args.putStringArrayList("gList",gList);
            args.putStringArrayList("wList",wList);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a OverviewFragment (defined as a static inner class below).
            if(position==1){
                return detailsFragment.newInstance(position + 1,courseList,gradeList,weightingList);
            }
            if(position==2){
                return analysisFragment.newInstance(position + 1,courseList,gradeList,weightingList);
            }
            return overviewFragment.newInstance(position + 1,courseList,gradeList,weightingList);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Overview";
                case 1:
                    return "Details";
                case 2:
                    return "Analysis";
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 10032) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                recreate();
                recreate();
                Log.d("debug","Recreated Activity");
            }
        }
    }


    //http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
    public String readFromFile(Context context, String file) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Debug", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Debug", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
