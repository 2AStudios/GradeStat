package tk.sbschools.gradestat;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class detailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "section_number";
    static final String COURSELIST = "cList";
    static final String GRADELIST = "gList";
    static final String WEIGHTINGLIST = "wList";

    // TODO: Rename and change types of parameters
    ArrayList<String> courseList, gradeList, weightingList;
    TextView detailsText;

    private OnFragmentInteractionListener mListener;

    public detailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static detailsFragment newInstance(ArrayList<String> cList, ArrayList<String> gList, ArrayList<String> wList) {
        detailsFragment fragment = new detailsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(COURSELIST, cList);
        args.putStringArrayList(GRADELIST, gList);
        args.putStringArrayList(WEIGHTINGLIST, wList);

        fragment.setArguments(args);
        return fragment;
    }

    public static detailsFragment newInstance(int sectionNumber, ArrayList<String> cList, ArrayList<String> gList, ArrayList<String> wList) {
        detailsFragment fragment = new detailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putStringArrayList(COURSELIST, cList);
        args.putStringArrayList(GRADELIST, gList);
        args.putStringArrayList(WEIGHTINGLIST, wList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseList = getArguments().getStringArrayList(COURSELIST);
            gradeList = getArguments().getStringArrayList(GRADELIST);
            weightingList = getArguments().getStringArrayList(WEIGHTINGLIST);
            Log.d("debug",courseList.toString());
            Log.d("debug",gradeList.toString());
            Log.d("debug",weightingList.toString());
        }else {
            Log.d("debug", "arguments not found");
        }

    }

    public Double gpaValue(String grade){
        Double value = 0.0;
        Double gradeVal = Double.parseDouble(grade);
        if(gradeVal >= 93.0){
            value = 4.0;
        } else if(gradeVal >= 90.0){
            value = 3.67;
        } else if(gradeVal >= 87.0){
            value = 3.33;
        } else if(gradeVal >= 83.0){
            value = 3.0;
        } else if(gradeVal >= 80.0){
            value = 2.67;
        } else if(gradeVal >= 77.0){
            value = 2.33;
        } else if(gradeVal >= 73.0){
            value = 2.0;
        } else if(gradeVal >= 70.0){
            value = 1.67;
        } else if(gradeVal >= 67.0){
            value = 1.33;
        } else if(gradeVal >= 63.0){
            value = 1.0;
        } else if(gradeVal >= 60.0){
            value = 0.67;
        } else if(gradeVal >= 0.0){
            value = 0.0;
        } else {
            value = 0.0;
        }
        return value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        // Inflate the layout for this fragment
        detailsText = (TextView)view.findViewById(R.id.textView_detailsText);
        double wGPA = 0.0;
        double uwGPA = 0.0;
        double totalwGPA = 0.0;
        double totaluwGPA = 0.0;
        for(int i=0;i<gradeList.size();i++){
            uwGPA += gpaValue(gradeList.get(i));
            wGPA += gpaValue(gradeList.get(i)) + (Double.parseDouble(weightingList.get(i))-4.0);
        }
        totalwGPA = wGPA;
        totaluwGPA = uwGPA;
        uwGPA = uwGPA/gradeList.size();
        wGPA = wGPA/gradeList.size();

        DecimalFormat df2 = new DecimalFormat("##.##");
        detailsText.setText("Weighted GPA: " +df2.format(wGPA) +"\n");
        detailsText.setText(detailsText.getText() + "Unweighted GPA: " + df2.format(uwGPA)+"\n\n");

        for(int i=0;i<courseList.size();i++){
            detailsText.setText(detailsText.getText()+courseList.get(i)+": " + gradeList.get(i) + "%\n"
            + "  Percentage of Unweighted GPA: " + df2.format((gpaValue(gradeList.get(i))/totaluwGPA)*100) +"%\n"
            + "  Percentage of Weighted GPA: " + df2.format((((gpaValue(gradeList.get(i))+ (Double.parseDouble(weightingList.get(i))-4.0))/totalwGPA)*100.0)) +"%\n");
        }

        TextView textView = (TextView) view.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
