package commonantimony.expenses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

class items {
    String name;
    float val;

    items() {
    }
}


public class MainActivity extends AppCompatActivity {

    ArrayList<items> listItemsArray = new ArrayList<>();
    int list_count = 0;
    customListViewAdapter lI;
    MediaPlayer mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lI = new customListViewAdapter(this, listItemsArray);
        mP = lI.getmPlay();
        ListView lV = (ListView) findViewById(R.id.listView);
        lV.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        lV.setAdapter(lI);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addbutton, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("unchecked")
    public void addItem(MenuItem M) {
        list_count++;
        lI.add(new items());
        lI.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mP != null){
            mP.release();
            mP = null;
        }

    }
}
