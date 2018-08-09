package adel.co.asyst.biodata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adel.co.asyst.biodata.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener {

    EditText nameET, addressET, emailET, tempatET;
    TextView birthdayTV;
    RadioGroup genderRG;
    RadioButton maleRB, femaleRB;
    ImageView dateIV;
    CheckBox renangCB, lainCB, sepedaCB, belanjaCB;
    Button submitButton;
    Spinner pendidikanSpinner;
    String selectedSchool, selectedGender, editName, editAddress, editEmail, editTempat, hobi;
    ArrayList<String> listPendidikan = new ArrayList<>();
    ArrayList<String> listHobi = new ArrayList<>();
    DatePickerDialog datePickerDialog = new DatePickerDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.edit_text_name);
        addressET = findViewById(R.id.edit_text_address);
        emailET = findViewById(R.id.edit_text_email);
        tempatET = findViewById(R.id.edit_text_tempatLahir);
        femaleRB = findViewById(R.id.radio_button_female);
        maleRB = findViewById(R.id.radio_button_male);
        genderRG = findViewById(R.id.radio_group_gender);
        dateIV = findViewById(R.id.imageView_date);
        birthdayTV = findViewById(R.id.text_view_birthday);
        renangCB = findViewById(R.id.checkbox_renang);
        lainCB = findViewById(R.id.checkbox_lainnya);
        sepedaCB = findViewById(R.id.checkbox_bersepeda);
        belanjaCB = findViewById(R.id.checkbox_belanja);
        submitButton = findViewById(R.id.button_submit);
        pendidikanSpinner = findViewById(R.id.spinner_pendidikan);

        genderRG.setOnCheckedChangeListener(this);
        renangCB.setOnCheckedChangeListener(this);
        sepedaCB.setOnCheckedChangeListener(this);
        belanjaCB.setOnCheckedChangeListener(this);
        lainCB.setOnCheckedChangeListener(this);
        pendidikanSpinner.setOnItemSelectedListener(this);
        submitButton.setOnClickListener(this);
        dateIV.setOnClickListener(this);

        listPendidikan.add("SD");
        listPendidikan.add("SMP");
        listPendidikan.add("SMA");
        listPendidikan.add("SMK");
        listPendidikan.add("S1");
        listPendidikan.add("S2");
        listPendidikan.add("S3");
        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendidikan);
        pendidikanSpinner.setAdapter(schoolAdapter);

        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        dateIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                editName = nameET.getText().toString();
                editAddress = addressET.getText().toString();
                editEmail = emailET.getText().toString();
                editTempat = tempatET.getText().toString();
                Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

                Matcher matcher1 = pattern1.matcher(editEmail);
                if (TextUtils.isEmpty(editName)) {
                    nameET.setError("Nama belum diisi");
                } else if (TextUtils.isEmpty(editAddress)) {
                    addressET.setError("Alamat belum diisi");
                } else if (TextUtils.isEmpty(editTempat)) {
                    tempatET.setError("Tempat lahir belum diisi");
                } else if (TextUtils.isEmpty(editEmail)) {
                    emailET.setError("Email belum diisi");
                } else if (!matcher1.matches()) {
                    emailET.setError("Email Salah");
                } else {
                    hobi = "";
                    for (int i = 0; i < listHobi.size(); i++) {
                        hobi = hobi + " " + listHobi.get(i);
                    }
                    // textView.setText("Welcome "+editText +", kamu " +selectedGender+" suka"+hobi + " tinggal di "+selectedCity);
                    AlertDialog.Builder alertDia = new AlertDialog.Builder(this);
                    alertDia.setTitle("Confirmation").setCancelable(false).setMessage("Are you sure?").
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, HasilActivity.class);

                                    intent.putExtra(Constant.KEY_NAME, nameET.getText().toString());
                                    intent.putExtra(Constant.KEY_ADDRESS, addressET.getText().toString());
                                    intent.putExtra(Constant.KEY_TEMPAT, tempatET.getText().toString());
                                    intent.putExtra(Constant.KEY_TANGGAL, birthdayTV.getText().toString());
                                    intent.putExtra(Constant.KEY_EMAIL, emailET.getText().toString());
                                    String gender = selectedGender + "";
                                    String hobi = listHobi + "";
                                    String pendidikan = "" + selectedSchool;
                                    intent.putExtra(Constant.KEY_GENDER, gender);
                                    intent.putExtra(Constant.KEY_HOBI, hobi);
                                    intent.putExtra(Constant.KEY_PENDIDIKAN, pendidikan);


                                    startActivity(intent);
                                }
                            }).setNegativeButton("No", null).show();
                }
                break;
            case R.id.imageView_date:
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedSchool = pendidikanSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.checkbox_renang:
                if (isChecked) {
                    listHobi.add("Berenang ");
                } else {
                    listHobi.remove("Berenang ");
                }
                break;
            case R.id.checkbox_bersepeda:
                if (isChecked) {
                    listHobi.add("Bersepeda ");
                } else {
                    listHobi.remove("Bersepeda ");
                }
                break;
            case R.id.checkbox_belanja:
                if (isChecked) {
                    listHobi.add("Belanja ");
                } else {
                    listHobi.remove("Belanja ");
                }
                break;
            case R.id.checkbox_lainnya:

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_male:
                selectedGender = "Laki-laki";
                break;
            case R.id.radio_button_female:
                selectedGender = "Perempuan";
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        birthdayTV.setText(date);
    }
}
