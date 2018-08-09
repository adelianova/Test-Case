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

public class EditActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText enameET, eaddressET, eemailET, etempatET;
    TextView ebirthdayTV;
    RadioGroup egenderRG;
    RadioButton emaleRB, efemaleRB;
    ImageView edateIV;
    CheckBox erenangCB, elainCB, esepedaCB, ebelanjaCB;
    Button esubmitButton;
    Spinner ependidikanSpinner;
    String eselectedSchool, eselectedGender, eeditName, eeditAddress, eeditEmail, eeditTempat;
    String name, address, tempat, email, hobi, pendidikan, gender, tanggal;
    ArrayList<String> elistPendidikan = new ArrayList<>();
    ArrayList<String> elistHobi = new ArrayList<>();
    DatePickerDialog datePickerDialog = new DatePickerDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        enameET = findViewById(R.id.edit_name);
        eaddressET = findViewById(R.id.edit_address);
        eemailET = findViewById(R.id.edit_email);
        etempatET = findViewById(R.id.edit_tempatLahir);
        efemaleRB = findViewById(R.id.edit_radio_button_female);
        emaleRB = findViewById(R.id.edit_radio_button_male);

        egenderRG = findViewById(R.id.radio_group_gender);
        edateIV = findViewById(R.id.edit_imageView_date);
        ebirthdayTV = findViewById(R.id.edit_text_view_birthday);
        erenangCB = findViewById(R.id.edit_checkbox_renang);
        elainCB = findViewById(R.id.edit_checkbox_lainnya);
        esepedaCB = findViewById(R.id.edit_checkbox_bersepeda);
        ebelanjaCB = findViewById(R.id.edit_checkbox_belanja);
        esubmitButton = findViewById(R.id.edit_button);
        ependidikanSpinner = findViewById(R.id.spinner_pendidikan);

        egenderRG.setOnCheckedChangeListener(this);
        erenangCB.setOnCheckedChangeListener(this);
        esepedaCB.setOnCheckedChangeListener(this);
        ebelanjaCB.setOnCheckedChangeListener(this);
        elainCB.setOnCheckedChangeListener(this);
        ependidikanSpinner.setOnItemSelectedListener(this);
        esubmitButton.setOnClickListener(this);
        edateIV.setOnClickListener(this);

        elistPendidikan.add("SD");
        elistPendidikan.add("SMP");
        elistPendidikan.add("SMA");
        elistPendidikan.add("SMK");
        elistPendidikan.add("S1");
        elistPendidikan.add("S2");
        elistPendidikan.add("S3");
        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elistPendidikan);
        ependidikanSpinner.setAdapter(schoolAdapter);

        if (getIntent().getExtras() != null) {
            name = getIntent().getExtras().getString(Constant.KEY_NAME);
            address = getIntent().getExtras().getString(Constant.KEY_ADDRESS);
            email = getIntent().getExtras().getString(Constant.KEY_EMAIL);
            tempat = getIntent().getExtras().getString(Constant.KEY_TEMPAT);
            tanggal = getIntent().getExtras().getString(Constant.KEY_TANGGAL);
            hobi = getIntent().getExtras().getString(Constant.KEY_HOBI);
            gender = getIntent().getExtras().getString(Constant.KEY_GENDER);
            pendidikan = getIntent().getExtras().getString(Constant.KEY_PENDIDIKAN);
        }
        enameET.setText(name);
        eaddressET.setText(address);
        eemailET.setText(email);
        etempatET.setText(tempat);
        ebirthdayTV.setText(tanggal);
        if (gender == "Laki-laki") {
            egenderRG.check(R.id.edit_radio_button_male);
        } else {
            egenderRG.check(R.id.edit_radio_button_female);
        }
        //eselectedGender.setChecked(true);

        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                EditActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        edateIV.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.edit_radio_button_male:
                eselectedGender = "Laki-laki";
                break;
            case R.id.edit_radio_button_female:
                eselectedGender = "Perempuan";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.edit_checkbox_renang:
                if (isChecked) {
                    elistHobi.add("Berenang ");
                } else {
                    elistHobi.remove("Berenang ");
                }
                break;
            case R.id.edit_checkbox_bersepeda:
                if (isChecked) {
                    elistHobi.add("Bersepeda ");
                } else {
                    elistHobi.remove("Bersepeda ");
                }
                break;
            case R.id.edit_checkbox_belanja:
                if (isChecked) {
                    elistHobi.add("Belanja ");
                } else {
                    elistHobi.remove("Belanja ");
                }
                break;
            case R.id.edit_checkbox_lainnya:

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        eselectedSchool = ependidikanSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_button:
                eeditName = enameET.getText().toString();
                eeditAddress = eaddressET.getText().toString();
                eeditEmail = eemailET.getText().toString();
                eeditTempat = etempatET.getText().toString();
                Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

                Matcher matcher1 = pattern1.matcher(eeditEmail);
                if (TextUtils.isEmpty(eeditName)) {
                    enameET.setError("Nama belum diisi");
                } else if (TextUtils.isEmpty(eeditAddress)) {
                    eaddressET.setError("Alamat belum diisi");
                } else if (TextUtils.isEmpty(eeditTempat)) {
                    etempatET.setError("Tempat lahir belum diisi");
                } else if (TextUtils.isEmpty(eeditEmail)) {
                    eemailET.setError("Email belum diisi");
                } else if (!matcher1.matches()) {
                    eemailET.setError("Email Salah");
                } else {
                    hobi = "";
                    for (int i = 0; i < elistHobi.size(); i++) {
                        hobi = hobi + " " + elistHobi.get(i);
                    }
                    // textView.setText("Welcome "+editText +", kamu " +selectedGender+" suka"+hobi + " tinggal di "+selectedCity);
                    AlertDialog.Builder alertDia = new AlertDialog.Builder(this);
                    alertDia.setTitle("Confirmation").setCancelable(false).setMessage("Are you sure?").
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(EditActivity.this, HasilActivity.class);

                                    intent.putExtra(Constant.KEY_NAME, enameET.getText().toString());
                                    intent.putExtra(Constant.KEY_ADDRESS, eaddressET.getText().toString());
                                    intent.putExtra(Constant.KEY_TEMPAT, etempatET.getText().toString());
                                    intent.putExtra(Constant.KEY_TANGGAL, ebirthdayTV.getText().toString());
                                    intent.putExtra(Constant.KEY_EMAIL, eemailET.getText().toString());
                                    String gender = eselectedGender + "";
                                    String hobi = elistHobi + "";
                                    String pendidikan = "" + eselectedSchool;
                                    intent.putExtra(Constant.KEY_GENDER, gender);
                                    intent.putExtra(Constant.KEY_HOBI, hobi);
                                    intent.putExtra(Constant.KEY_PENDIDIKAN, pendidikan);


                                    startActivity(intent);
                                }
                            }).setNegativeButton("No", null).show();
                }
                break;
            case R.id.edit_imageView_date:
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        ebirthdayTV.setText(date);
    }
}
