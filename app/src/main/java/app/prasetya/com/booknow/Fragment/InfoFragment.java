package app.prasetya.com.booknow.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.prasetya.com.booknow.MainActivity;
import app.prasetya.com.booknow.Model.DataModelRoom;
import app.prasetya.com.booknow.Model.RoomModel;
import app.prasetya.com.booknow.R;

public class InfoFragment extends Fragment {
    private ImageView imageDetail;
    private TextView tittleDetail, facilityDetail,priceDetail;
    private ArrayList<RoomModel> listModel;


    static InfoFragment instance;

    public static InfoFragment getInstance(){
        if(instance == null)
            instance = new InfoFragment();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.infofragment,container,false);

        int i = MainActivity.currentItem;

        imageDetail = (ImageView) v.findViewById(R.id.imageDetail);
        tittleDetail = (TextView) v.findViewById(R.id.tittleDetail);
        facilityDetail = (TextView) v.findViewById(R.id.facilityDetail);
        priceDetail = (TextView) v.findViewById(R.id.priceDetail);

        imageDetail.setImageResource(DataModelRoom.Image[i]);
        tittleDetail.setText(DataModelRoom.TittleRoom[i]);
        facilityDetail.setText(DataModelRoom.FacilityRoom[i]);
        priceDetail.setText(DataModelRoom.priceRoom[i]);





        return v;
    }
}
