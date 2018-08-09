package adel.co.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import adel.co.asyst.testcase.fragments.MatchFragment;
import adel.co.asyst.testcase.utility.Constant;

public class MatchActivity extends AppCompatActivity implements MatchFragment.OnButtonNextClickedListener {

    String timAname, timBname;
    int quarter = 1;
    int scoreTeamA = 0, scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        if (getIntent().getExtras() != null) {
            timAname = getIntent().getExtras().getString(Constant.KEY_TEAMA_NAME);
            timBname = getIntent().getExtras().getString(Constant.KEY_TEAMB_NAME);

        }
        loadFragment();
    }

    private void loadFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MatchFragment matchFragment = MatchFragment.newInstance(timAname, timBname, quarter, scoreTeamA, scoreTeamB);

        transaction.add(R.id.fragment_container, matchFragment);
        transaction.commit();
    }

    @Override
    public void onButtonNextClickedListener(int scoreTeamA, int scoreTeamB) {
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;

        ++quarter;
        if (quarter <= 4) {
            loadFragment();
        } else {
            Intent intent = new Intent(this, ResultActivity.class);

            String winner;
            int scoreWinner = 0;
            if (scoreTeamA > scoreTeamB) {
                scoreWinner = scoreTeamA;
                winner = timAname;
            } else if (scoreTeamA < scoreTeamB) {
                scoreWinner = scoreTeamB;
                winner = timBname;
            } else {
                winner = "Draw";
            }

            intent.putExtra(Constant.KEY_WINNER_TEAM, winner);
            intent.putExtra(Constant.KEY_SCORE_WINNER, scoreWinner);
            startActivity(intent);
        }
    }

}
