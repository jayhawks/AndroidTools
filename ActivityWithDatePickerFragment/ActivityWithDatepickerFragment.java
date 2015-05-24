package innov.ernest.android.tools;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class ActivityWithDatepickerFragment extends ActionBarActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "ActivityWDtPickerFrag";

    private TextView mPurchasedDateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");

        setContentView(R.layout.layout_add_new_home_item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume()");

        // Get and store reference to the TextView
        mPurchasedDateTextView = (TextView) findViewById(R.id.purchase_date);

        // Setup listener for click action
        mPurchasedDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Field was clicked");
                showDatePickerDialog(v);
            }
        });
    }


    /**
     * Show the DatePicker Dialog
     * @param v the View
     */
    public void showDatePickerDialog(View v) {
        Log.i(TAG, "In showDatePickerDialog");

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * Implement the DatePickerDialog.OnDateSetListener interface to listen
     * for the date set event
     *
     * @param view The view
     * @param year The year
     * @param month The month
     * @param day The day
     */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        StringBuilder sb = new StringBuilder()
                .append(String.valueOf(month))
                .append("/")
                .append(String.valueOf(day))
                .append("/")
                .append(String.valueOf(year));

        // Set the text of the purchased date field to the value in the date
        mPurchasedDateTextView.setText(sb.toString());
    }


    /**
     * Internal class representing the DatePicker
     */
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (ActivityWithDatepickerFragment) getActivity(), year, month, day);
        }



    }

}