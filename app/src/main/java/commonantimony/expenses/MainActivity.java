package commonantimony.expenses;

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
        this.name = "Item";
        this.val = 00;
    }
}


public class MainActivity extends AppCompatActivity {

    ArrayList<items> listItemsArray = new ArrayList<>();
    int list_count = 0;
    ArrayAdapter lI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lI = new customListViewAdapter(this, listItemsArray);
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

    public void addItem(MenuItem M) {
        list_count++;
        lI.add(new items());
        lI.notifyDataSetChanged();
    }
}
