

package com.example.time;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private Spinner timezoneSpinner;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timezoneSpinner = findViewById(R.id.timezoneSpinner);
        timeText = findViewById(R.id.timeText);

        // Get the list of available time zones
        String[] timeZones = TimeZone.getAvailableIDs();

        // Create an adapter to display time zones in the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, timeZones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timezoneSpinner.setAdapter(adapter);

        // Set an item selected listener on the Spinner
        timezoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                String selectedTimeZone = (String) parentView.getItemAtPosition(position);
                updateTimeForTimeZone(selectedTimeZone);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Optionally handle the case when nothing is selected
            }
        });
    }

    // Method to update the time for the selected time zone
    private void updateTimeForTimeZone(String timeZoneId) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(timeZone);

        // Get the current date and time for the selected time zone
        String currentTime = dateFormat.format(new Date());

        // Update the TextView to display the current time for the selected time zone
        timeText.setText("Time in " + timeZoneId + ": " + currentTime);
    }
}
