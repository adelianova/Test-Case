package adel.co.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adel.co.asyst.testcase.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;
    EditText etTeamA, etTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.start_btn);
        etTeamA = findViewById(R.id.timA_edit);
        etTeamB = findViewById(R.id.timB_edit);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                if (validate()) {
                    Intent intent = new Intent(this, MatchActivity.class);
                    intent.putExtra(Constant.KEY_TEAMA_NAME, etTeamA.getText().toString());
                    intent.putExtra(Constant.KEY_TEAMB_NAME, etTeamB.getText().toString());

                    startActivity(intent);
                }
                break;
        }
    }

    public boolean validate() {
        if (TextUtils.isEmpty(etTeamA.getText().toString()) || TextUtils.isEmpty(etTeamB.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama tim harus diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTeamA.getText().toString().equalsIgnoreCase(etTeamB.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama tim tidak boleh sama", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
