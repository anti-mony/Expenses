package commonantimony.expenses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sushant on 12/30/2016.
 * Custom List View Adapter
 */

class customListViewAdapter extends ArrayAdapter<items> {

    ArrayList<items> listX = new ArrayList<>();
    TextView totalValue;
    float total;
    Context ctxt;

    public customListViewAdapter(Context context, ArrayList L) {
        super(context, R.layout.custom_listview, L);
        this.listX = L;
        ctxt = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater lI = LayoutInflater.from(getContext());
        View customView = lI.inflate(R.layout.custom_listview, parent, false);
        totalValue = (TextView) ((Activity) ctxt).findViewById(R.id.amountTextView);
        TextView sNo = (TextView) customView.findViewById(R.id.sNo);
        final EditText name = (EditText) customView.findViewById(R.id.itemNameList);
        name.setText(listX.get(position).name);
        name.setBackgroundColor(Color.TRANSPARENT);
        name.setInputType(InputType.TYPE_CLASS_TEXT);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listX.get(position).name = name.getText().toString();
                } catch (Exception E) {

                }

            }
        });

        final EditText val = (EditText) customView.findViewById(R.id.itemValList);
        try {
            val.setText(String.valueOf(listX.get(position).val));
        } catch (Exception E) {

        }
        val.setBackgroundColor(Color.TRANSPARENT);
        val.setInputType(InputType.TYPE_CLASS_NUMBER);
        val.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listX.get(position).val = Float.valueOf(val.getText().toString());
                    total = addValues();
                    totalValue.setText(String.valueOf(total));
                    //Toast.makeText(ctxt, "Total:"+total, Toast.LENGTH_SHORT).show()                    ;
                } catch (Exception E) {

                }
            }
        });

        ImageView close = (ImageView) customView.findViewById(R.id.removeItem);

        sNo.setText((position + 1) + ".");
        close.setImageResource(R.drawable.ic_delete_black_48dp);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listX.remove(position);
                notifyDataSetChanged();
                total = addValues();
                totalValue.setText(String.valueOf(total));

            }
        });
        return customView;
    }

    float addValues() {
        float sum = 0;
        int i = 0;
        for (i = 0; i < listX.size(); i++) {
            sum = sum + listX.get(i).val;
        }
//        Log.v("TAG","SUM = "+sum);
        return sum;
    }
}
