package pioneers.safwat.animals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.Tracker;

/**
 * Created by safwa on 2/25/2017.
 */

public class Present extends AppCompatActivity {
    private Tracker mTracker;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.present);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        Button arabic=(Button) findViewById(R.id.button_arabic);
        Button english=(Button)findViewById(R.id.button_english);

       arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Present.this, pioneers.safwat.animals.CardDemoActivitya.class));
            }
        });
        english.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                startActivity(new Intent(Present.this,pioneers.safwat.animals.CardDemoActivity.class));
            }
        });

    }
}
