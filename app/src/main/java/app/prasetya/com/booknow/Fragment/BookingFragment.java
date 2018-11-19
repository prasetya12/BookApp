package app.prasetya.com.booknow.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import app.prasetya.com.booknow.InformationBooking;
import app.prasetya.com.booknow.MainActivity;
import app.prasetya.com.booknow.Model.DataModelRoom;
import app.prasetya.com.booknow.R;


public class BookingFragment extends Fragment {
    private TextInputEditText mName,mEmail,mPhone,mAddress;
    private EditText mTimePicker,mDatePicker;
    private TextView mTittle,mPrice;
    private Button mBook;
    private TimePickerDialog timePickerDialog;

    final Calendar myCalendar = Calendar.getInstance();



    static BookingFragment instance;

    public static BookingFragment getInstance(){
        if(instance == null)
            instance = new BookingFragment();

        return  instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bookingfragment,container,false);
        int i = MainActivity.currentItem;

        setTittleBook(v, i);
        showDatePicker(v);
        showTimePicker(v);
        init(v);


        mBook = (Button) v.findViewById(R.id.buttonbook);

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String date = mDatePicker.getText().toString();
                    String time = mTimePicker.getText().toString();
                    String name = mName.getText().toString();
                    String email = mEmail.getText().toString();
                    String phone = mPhone.getText().toString();
                    String address = mAddress.getText().toString();

                    Intent intent = new Intent(getActivity(), InformationBooking.class);
                    intent.putExtra("ROOM",mTittle.getText());
                    intent.putExtra("PRICE",mPrice.getText());
                    intent.putExtra("TIME",time);
                    intent.putExtra("DATE",date);
                    intent.putExtra("NAME",name);
                    intent.putExtra("PHONE",phone);

                    startActivity(intent);


                }
            }
        });




        return v;
    }

    private void init(View v) {
        mName = (TextInputEditText) v.findViewById(R.id.namebook);
        mEmail = (TextInputEditText) v.findViewById(R.id.emailbook);
        mPhone = (TextInputEditText) v.findViewById(R.id.phonebook);
        mAddress = (TextInputEditText) v.findViewById(R.id.addressbook);
    }

    private void showTimePicker(View v) {
        mTimePicker = (EditText) v.findViewById(R.id.timepicker);
        mTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimePicker();
            }
        });
    }

    private void showDatePicker(View v) {
        mDatePicker = (EditText) v.findViewById(R.id.datepicker);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();

            }
        };

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setTittleBook(View v, int i) {
        mTittle = (TextView) v.findViewById(R.id.tittlebook);
        mPrice = (TextView) v.findViewById(R.id.pricebook);

        mTittle.setText(DataModelRoom.TittleRoom[i]);
        mPrice.setText(DataModelRoom.priceRoom[i]);
    }

    private void updateLabel() {
    String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        mDatePicker.setText(sdf.format(myCalendar.getTime()));

    }

    private void setTimePicker(){
        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                timePicker.setIs24HourView(true);
                mTimePicker.setText(String.format("%02d : %02d",hourOfDay,minutes));
            }
        },0,0,false);
        timePickerDialog.show();


    }

    private Boolean validate(){
        boolean valid = false;
        String date = mDatePicker.getText().toString();
        String time = mTimePicker.getText().toString();
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String Address = mAddress.getText().toString();

        if(date.isEmpty()){
            mDatePicker.setError("invalid");
            valid = false;
        }else if(time.isEmpty()){
            mTimePicker.setError("Invalid");
            valid = false;
        }else if(name.isEmpty()){
            mName.setError("Invalid");
            valid = false;
        }else if(email.isEmpty()){
            mEmail.setError("Invalid");
            valid = false;
        }else if(phone.isEmpty()){
            mPhone.setError("Invalid");
            valid = false;
        }else{
            valid = true;
        }



                return valid;
    }


}
