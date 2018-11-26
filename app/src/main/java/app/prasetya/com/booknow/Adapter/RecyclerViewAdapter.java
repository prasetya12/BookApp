package app.prasetya.com.booknow.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;


import app.prasetya.com.booknow.MainActivity;
import app.prasetya.com.booknow.Model.RoomModel;
import app.prasetya.com.booknow.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder> {

    ArrayList<RoomModel> listRoom;

    public RecyclerViewAdapter(ArrayList<RoomModel> listRoom){
        this.listRoom = listRoom;

    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carditem,parent,false);

        v.setOnClickListener(MainActivity.mainOnclickListener);

        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        ImageView imageRoom = (ImageView) holder.imageRoom;
        TextView tittleRoom = (TextView) holder.tittleRoom;
        TextView priceRoom = (TextView) holder.priceRoom;

        imageRoom.setImageResource(listRoom.get(position).getImageRoom());
        tittleRoom.setText(listRoom.get(position).getTittleRoom());
        priceRoom.setText(listRoom.get(position).getPriceRoom());

    }

    @Override
    public int getItemCount() {
        return listRoom.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageRoom;
        private TextView tittleRoom,priceRoom;

        public MainViewHolder(View itemView) {
            super(itemView);
            imageRoom = (ImageView) itemView.findViewById(R.id.imageroom);
            tittleRoom = (TextView) itemView.findViewById(R.id.tittleroom);
            priceRoom = (TextView) itemView.findViewById(R.id.priceroom);
        }
    }
}
