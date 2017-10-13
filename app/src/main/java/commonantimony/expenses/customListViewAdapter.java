package commonantimony.expenses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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

    private ArrayList<items> listX = new ArrayList<>();
    private TextView totalValue;
    private float total;
    private Context ctxt;
    MediaPlayer mPlay;

    customListViewAdapter(Context context, ArrayList<items> L) {
        super(context, R.layout.custom_listview, L);
        this.listX = L;
        ctxt = context;
    }

    public MediaPlayer getmPlay() {
        return mPlay;
    }

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
        name.setHint("Item Name");
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listX.get(position).name = name.getText().toString();
            }
        });

        final EditText val = (EditText) customView.findViewById(R.id.itemValList);
        val.setHint("Price");
        try {
            val.setText(String.valueOf(listX.get(position).val));
        } catch (Exception E) {
            Log.v("TAG", "Empty EditText");
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
                } catch (Exception E) {
                    Log.v("TAG", "Cannot convert to float / SUM failed");
                }
            }
        });

        ImageView close = (ImageView) customView.findViewById(R.id.removeItem);

        sNo.setText(String.valueOf(position + 1));
        close.setImageResource(R.drawable.ic_delete_black_48dp);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listX.remove(position);
                notifyDataSetChanged();
                total = addValues();
                totalValue.setText(String.valueOf(total));
//                mPlay = MediaPlayer.create(ctxt,R.raw.ringingbell);
//                mPlay.start();
//                mPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        if(mPlay != null){
//                            mPlay.release();
//                            mPlay = null;
//                        }
//                    }
//                });

            }
        });
        return customView;
    }

    private float addValues() {
        float sum = 0;
        for (int i = 0; i < listX.size(); i++) {
            sum = sum + listX.get(i).val;
        }
//        Log.v("TAG","SUM = "+sum);
        return sum;
    }
}
