package adel.co.asyst.biodata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import adel.co.asyst.biodata.utility.Constant;

public class HasilActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nametv, addresstv, emailtv, tempattv, gendertv, hobitv, pendidikantv, birthdaytv;
    String name, address, tempat, email, gender, hobi, pendidikan, birthday;
    Button editbtn, backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        nametv = findViewById(R.id.nameTV);
        addresstv = findViewById(R.id.addressTV);
        emailtv = findViewById(R.id.emailTV);
        tempattv = findViewById(R.id.tempatTV);
        birthdaytv = findViewById(R.id.dateTV);
        gendertv = findViewById(R.id.genderTV);
        hobitv = findViewById(R.id.hobiTV);
        pendidikantv = findViewById(R.id.pendidikanTV);
        editbtn = findViewById(R.id.editBtn);
        backbtn = findViewById(R.id.button_back);
        backbtn.setOnClickListener(this);
        editbtn.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            name = getIntent().getExtras().getString(Constant.KEY_NAME);
            address = getIntent().getExtras().getString(Constant.KEY_ADDRESS);
            email = getIntent().getExtras().getString(Constant.KEY_EMAIL);
            tempat = getIntent().getExtras().getString(Constant.KEY_TEMPAT);
            birthday = getIntent().getExtras().getString(Constant.KEY_TANGGAL);
            hobi = getIntent().getExtras().getString(Constant.KEY_HOBI);
            gender = getIntent().getExtras().getString(Constant.KEY_GENDER);
            pendidikan = getIntent().getExtras().getString(Constant.KEY_PENDIDIKAN);
        }
        nametv.setText(name);
        addresstv.setText(address);
        emailtv.setText(email);
        tempattv.setText(tempat);
        birthdaytv.setText(birthday);
        gendertv.setText(gender + "");
        hobitv.setText(hobi + "");
        pendidikantv.setText(pendidikan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editBtn:
                Intent intent = new Intent(HasilActivity.this, EditActivity.class);
                intent.putExtra(Constant.KEY_NAME, nametv.getText().toString());
                intent.putExtra(Constant.KEY_ADDRESS, addresstv.getText().toString());
                intent.putExtra(Constant.KEY_TEMPAT, tempattv.getText().toString());
                intent.putExtra(Constant.KEY_TANGGAL, birthdaytv.getText().toString());
                intent.putExtra(Constant.KEY_EMAIL, emailtv.getText().toString());
                intent.putExtra(Constant.KEY_GENDER, gendertv.getText().toString());
                intent.putExtra(Constant.KEY_HOBI, hobitv.getText().toString());
                intent.putExtra(Constant.KEY_PENDIDIKAN, pendidikantv.getText().toString());
                startActivity(intent);
                break;
            case R.id.button_back:
                AlertDialog.Builder alertDia = new AlertDialog.Builder(this);
                alertDia.setTitle("Confirmation").setCancelable(false).setMessage("Are you sure?").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1 = new Intent(HasilActivity.this, MainActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent1);
                                startActivity(intent1);
                            }
                        }).setNegativeButton("No", null).show();


                break;
        }

    }
}

