package commonantimony.expenses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addbutton, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addItem(MenuItem M) {
        final EditText name = new EditText(this);
        final EditText val = new EditText(this);
        final ImageView close = new ImageView(this);
        final TextView sNo = new TextView(this);
        int itemID = View.generateViewId();
        int imgID = close.generateViewId();
        int sNoID = sNo.generateViewId();

        int no = 1;

        RelativeLayout.LayoutParams lpForSNo = new RelativeLayout.LayoutParams(80,100);
        lpForSNo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lpForSNo.addRule(RelativeLayout.BELOW,itemID-1);

        final RelativeLayout rL = (RelativeLayout) findViewById(R.id.scrollViewRL);
        RelativeLayout.LayoutParams lPForName = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lPForName.addRule(RelativeLayout.LEFT_OF, R.id.divider);
        lPForName.addRule(RelativeLayout.RIGHT_OF,sNoID);
        lPForName.addRule(RelativeLayout.BELOW, itemID-1);

        RelativeLayout.LayoutParams lPForVal = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lPForVal.addRule(RelativeLayout.RIGHT_OF, R.id.divider);
        lPForVal.addRule(RelativeLayout.LEFT_OF,imgID);
        lPForVal.addRule(RelativeLayout.BELOW, itemID-1);

        RelativeLayout.LayoutParams lpForClose = new RelativeLayout.LayoutParams(100,100);
        lpForClose.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lpForClose.addRule(RelativeLayout.BELOW, itemID-1);


        sNo.setId(sNoID);
        sNo.setText(no+".");
        sNo.setTextSize(18);
        sNo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        sNo.setGravity(Gravity.BOTTOM);

        name.setId(itemID);
        name.setSingleLine(true);

        val.setInputType(InputType.TYPE_CLASS_NUMBER);
        val.setSingleLine(true);

        close.setImageResource(R.drawable.ic_close_black_48dp);
        close.setId(imgID);


        sNo.setLayoutParams(lpForSNo);
        name.setLayoutParams(lPForName);
        val.setLayoutParams(lPForVal);
        close.setLayoutParams(lpForClose);

        rL.addView(sNo);
        rL.addView(name);
        rL.addView(val);
        rL.addView(close);
        addSerial();

        val.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addSerial();
                sumTotal();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rL.removeView(name);
                rL.removeView(val);
                rL.removeView(close);
                rL.removeView(sNo);
                sumTotal();
            }
        });
    }

    public void sumTotal() {

        int sum = 0;
        String text ;
        RelativeLayout rL = (RelativeLayout) findViewById(R.id.scrollViewRL);
        for (int i = 0; i < rL.getChildCount(); i++) {
            if (rL.getChildAt(i) instanceof EditText) {
                text = ((EditText) rL.getChildAt(i)).getText().toString();
                try {
                    sum += Integer.parseInt(text);
                } catch (Exception E) {
                    //Log.v("EmptyText", E.toString());
                }
            }
            TextView total = (TextView) findViewById(R.id.amountTextView);
            total.setText(String.valueOf(sum));
        }
    }

    public void addSerial(){
        int number = 1 ;
        String text;
        RelativeLayout rL = (RelativeLayout) findViewById(R.id.scrollViewRL);
        for (int i = 0; i < rL.getChildCount(); i++) {
            if (rL.getChildAt(i) instanceof TextView && !(rL.getChildAt(i) instanceof EditText) ) {
                    ((TextView) rL.getChildAt(i)).setText(number+".");
                    number++;
                }
            }
        }
}
