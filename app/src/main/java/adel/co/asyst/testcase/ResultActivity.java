package adel.co.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import adel.co.asyst.testcase.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    String winer;
    int score = 0;
    TextView winnertv, scoretv;
    Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winnertv = findViewById(R.id.winnerTeam_textView);
        scoretv = findViewById(R.id.winnerTeamScore_textView);
        backbtn = findViewById(R.id.back_btn);
        backbtn.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            winer = getIntent().getExtras().getString(Constant.KEY_WINNER_TEAM);
            score = getIntent().getExtras().getInt(Constant.KEY_SCORE_WINNER);
        }
        winnertv.setText(winer);
        scoretv.setText(score + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
