package arrase.privatefiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import arrase.privatefiles.constants.SecureFilesConstants;
import arrase.privatefiles.dialogs.SelectSourceDialog;

public class PrivateFilesActivity extends AppCompatActivity {
    private Boolean isFabOpen = false;
    private FloatingActionButton create_fab, import_fab;
    private Animation fab_open, fab_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton add_fab = (FloatingActionButton) findViewById(R.id.add_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        create_fab = (FloatingActionButton) findViewById(R.id.create_fab);
        create_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Bundle arguments = new Bundle();
                arguments.putString(
                        SecureFilesConstants.VOLUME_ACTION, SecureFilesConstants.VOLUME_CREATE
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.show(getFragmentManager(), "SelectSource");
            }
        });

        import_fab = (FloatingActionButton) findViewById(R.id.import_fab);
        import_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Bundle arguments = new Bundle();
                arguments.putString(
                        SecureFilesConstants.VOLUME_ACTION, SecureFilesConstants.VOLUME_IMPOT
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.show(getFragmentManager(), "SelectSource");
            }
        });

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
    }


    private void animateFAB() {

        if (isFabOpen) {

            create_fab.startAnimation(fab_close);
            import_fab.startAnimation(fab_close);
            create_fab.setClickable(false);
            import_fab.setClickable(false);
            isFabOpen = false;

        } else {

            create_fab.startAnimation(fab_open);
            import_fab.startAnimation(fab_open);
            create_fab.setClickable(true);
            import_fab.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_private_files, menu);
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
