package adel.co.asyst.testcase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import adel.co.asyst.testcase.R;
import adel.co.asyst.testcase.utility.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnButtonNextClickedListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    Button btnplus1A, btnplus2A, btnplus3A, btnplus1B, btnplus2B, btnplus3B, btnNext;
    TextView quartertv, namaTimAtv, namaTimBtv, scoreTimAtv, scoreTimBtv;
    // TODO: Rename and change types of parameters
    private String teamAName;
    private String teamBName;
    private int quarter;
    private int scoreTeamA;
    private int scoreTeamB;
    private OnButtonNextClickedListener mListener;

    public MatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(String teamA, String teamB, int quarter, int scoreTeamA, int scoreTeamB) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_TEAMA_NAME, teamA);
        args.putString(Constant.KEY_TEAMB_NAME, teamB);
        args.putInt(Constant.KEY_QUARTER, quarter);
        args.putInt(Constant.KEY_SCORE_TEAMA, scoreTeamA);
        args.putInt(Constant.KEY_SCORE_TEAMB, scoreTeamB);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamAName = getArguments().getString(Constant.KEY_TEAMA_NAME);
            teamBName = getArguments().getString(Constant.KEY_TEAMB_NAME);
            quarter = getArguments().getInt(Constant.KEY_QUARTER);
            scoreTeamA = getArguments().getInt(Constant.KEY_SCORE_TEAMA);
            scoreTeamB = getArguments().getInt(Constant.KEY_SCORE_TEAMB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        btnNext = view.findViewById(R.id.next_btn);
        btnplus1A = view.findViewById(R.id.plus1timA_btn);
        btnplus2A = view.findViewById(R.id.plus2timA_btn);
        btnplus3A = view.findViewById(R.id.plus3timA_btn);
        btnplus3B = view.findViewById(R.id.plus3timB_btn);
        btnplus2B = view.findViewById(R.id.plus2timB_btn);
        btnplus1B = view.findViewById(R.id.plus1timB_btn);
        quartertv = view.findViewById(R.id.quarter_textview);
        namaTimAtv = view.findViewById(R.id.timA_textView);
        namaTimBtv = view.findViewById(R.id.timB_textView);
        scoreTimBtv = view.findViewById(R.id.score_timB);
        scoreTimAtv = view.findViewById(R.id.score_timA);

        btnNext.setOnClickListener(this);
        btnplus1A.setOnClickListener(this);
        btnplus2A.setOnClickListener(this);
        btnplus3A.setOnClickListener(this);
        btnplus3B.setOnClickListener(this);
        btnplus2B.setOnClickListener(this);
        btnplus1B.setOnClickListener(this);
        switch (quarter) {
            case 1:
                quartertv.setText("1st");
                break;
            case 2:
                quartertv.setText("2nd");
                break;
            case 3:
                quartertv.setText("3th");
                break;
            case 4:
                quartertv.setText("4th");
                break;
        }
        namaTimAtv.setText(teamAName);
        namaTimBtv.setText(teamBName);

        scoreTimAtv.setText(scoreTeamA + "");
        scoreTimBtv.setText(scoreTeamB + "");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonNextClickedListener) {
            mListener = (OnButtonNextClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnButtonNextClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus1timA_btn:
                scoreTeamA += 1;
                break;
            case R.id.plus2timA_btn:

                scoreTeamA += 2;
                break;
            case R.id.plus3timA_btn:

                scoreTeamA += 3;
                break;
            case R.id.plus1timB_btn:
                scoreTeamB += 1;
                break;
            case R.id.plus2timB_btn:
                scoreTeamB += 2;
                break;
            case R.id.plus3timB_btn:
                scoreTeamB += 3;
                break;
            case R.id.next_btn:
                mListener.onButtonNextClickedListener(scoreTeamA, scoreTeamB);
                getActivity().getSupportFragmentManager().popBackStack();

                break;
        }

        scoreTimAtv.setText(scoreTeamA + "");
        scoreTimBtv.setText(scoreTeamB + "");

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
    public interface OnButtonNextClickedListener {
        // TODO: Update argument type and name
        void onButtonNextClickedListener(int scoreTeamA, int scoreTeamB);
    }
}
