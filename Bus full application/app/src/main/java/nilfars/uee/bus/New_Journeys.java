package nilfars.uee.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class New_Journeys extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int [] image={R.drawable.rootb,R.drawable.busbc,R.drawable.bs,R.drawable.busbc};

    private JourneyAdapter journeyAdapter;


    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__journeys);

        recyclerView=findViewById(R.id.recyclerViewjournry);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        journeyAdapter=new JourneyAdapter(image);
        recyclerView.setAdapter(journeyAdapter);




    }
}
