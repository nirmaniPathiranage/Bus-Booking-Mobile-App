package nilfars.uee.bus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JourneyAdapter  extends RecyclerView.Adapter<JourneyAdapter.ImageViewHolder> {
   private int[]images;

    public JourneyAdapter(int[] image) {
        this.images = image;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_suggestions,parent,false);
        ImageViewHolder imageViewHolder =new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        int image_id=images[position];
        holder.Album.setImageResource(image_id);
        holder.AlbumTitle.setText("Image  :"+position);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView Album;
        TextView AlbumTitle;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Album=itemView.findViewById(R.id.album);
            AlbumTitle=itemView.findViewById(R.id.title);
        }




    }



}
