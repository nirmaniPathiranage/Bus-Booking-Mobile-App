package nilfars.uee.bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Myclass> {


    Context context;
    ArrayList<FeedModel> fm;


    public FeedAdapter(Context context, ArrayList<FeedModel> fm) {
        this.context = context;
        this.fm = fm;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file,parent,false);


        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        FeedModel f=fm.get(position);
        holder.cname.setText(f.getCusName());
        holder.cmail.setText(f.getEmail());
        holder.ccomment.setText(f.getComment());

    }

    @Override
    public int getItemCount() {
        return fm.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{

        TextView cname,cmail,ccomment;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            cname=itemView.findViewById(R.id.uname);
            cmail=itemView.findViewById(R.id.uemail);
            ccomment=itemView.findViewById(R.id.ucomment);


        }
    }


}
