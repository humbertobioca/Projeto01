package primeiro.pos.projeto.myapplication;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.os.PowerManager;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.Toast;

import static android.R.attr.id;


public class MainActivity extends ListActivity {
    private final String options[] = {"Tirar foto","mmarcar alarme", "Sair"};
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Intent mIntent;
    private static final int REQUEST_CAMERA_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, options));


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {

            case 0:

                int hasCamera = checkSelfPermission(Manifest.permission.CAMERA);
                if (hasCamera != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);

                } else {
                    mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);
                    break;

                }
            default:
                finish();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA_PERMISSION && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, PictureActivity.class);
            intent.putExtras(data.getExtras());
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read camera ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}