package fr.oms.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.oms.activities.R;

public class FragmentActus extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView tv = (TextView) v.findViewById(R.id.text);
        tv.setText(this.getTag() + " Content");
        return v;
    }

}
