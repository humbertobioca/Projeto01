package primeiro.pos.projeto.trabalhopos1;

import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends ListActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Configuration configuration= getResources().getConfiguration();
                int density = configuration.densityDpi;
                int orientation = configuration.orientation;
                int height = configuration.screenHeightDp;
                int width = configuration.screenWidthDp;
                int mcc = configuration.mcc;
                int mnc = configuration.mnc;

                Locale locale = configuration.locale;
                Log.d("NGVL", "densitivy:" + density);
                Log.d("NGVL","orientation" + orientation);
                Log.d("NGVL", "height" + height);
                Log.d("NGVL", "width" + width);
                Log.d("NGVL", "language:" +locale.getLanguage() + "." + locale.getCountry());
                Log.d("NGVL", "mcc" + mcc);
                Log.d("NGVL", "mnc" + mnc);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);




    }
}
