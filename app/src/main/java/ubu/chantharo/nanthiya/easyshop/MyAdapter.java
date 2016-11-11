package ubu.chantharo.nanthiya.easyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Bee Nanthiya on 11/11/2559.
 */

public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] nameString, detailStrings, phoneStrings, iconStrings;
    private TextView nameTextView, detailTextView, phoneTextView;
    private ImageView imageView;

    public MyAdapter(Context context,
                     String[] nameString,
                     String[] detailStrings,
                     String[] phoneStrings,
                     String[] iconStrings) {
        this.context = context;
        this.nameString = nameString;
        this.detailStrings = detailStrings;
        this.phoneStrings = phoneStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return nameString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_layout, viewGroup, false);

        //Bind Widget
        nameTextView = (TextView) view1.findViewById(R.id.textView5);
        detailTextView = (TextView) view1.findViewById(R.id.textView6);
        phoneTextView = (TextView) view1.findViewById(R.id.textView7);
        imageView = (ImageView) view1.findViewById(R.id.imvIcon);

        //Show View
        nameTextView.setText(nameString[i]);
        detailTextView.setText(detailStrings[i]);
        phoneTextView.setText(phoneStrings[i]);

        Picasso.with(context).load(iconStrings[i]).into(imageView);


        return view1;
    }
}//Main Class
