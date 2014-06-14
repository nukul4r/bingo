package nukul4r.de.bingo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CellAdapter extends BaseAdapter {
    private MainActivity context;
    private final List<String> list;

    public CellAdapter(MainActivity context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.cell, null);

            // set value into textview
            final TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(list.get(position));

            final SharedPreferences settings = context.getSharedPreferences(MainActivity.PREFS, 0);

            if (settings.getBoolean(Integer.toString(position), false)) {
                textView.setBackgroundColor(Color.rgb(50, 190, 15));
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = settings.edit();

                    if (!settings.getBoolean(Integer.toString(position), false)) {
                        textView.setBackgroundColor(Color.rgb(50, 190, 15));
                        editor.putBoolean(Integer.toString(position), true);
                        editor.commit();
                    } else {
                        textView.setBackgroundColor(Color.WHITE);
                        editor.putBoolean(Integer.toString(position), false);
                        editor.commit();
                    }
                    context.weHaveABingo();
                }
            });


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}