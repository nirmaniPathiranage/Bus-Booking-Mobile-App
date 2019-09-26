package nilfars.uee.bus;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ModelAdapter adapter;
    List<Model>models;
    Integer[]colors=null;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();

    Button info;
    Button btngo;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        models=new ArrayList<>();
        models.add(new Model(R.drawable.pas,"Bus Stories","Stories of Buses will impress You exactly"));
        models.add(new Model(R.drawable.noth,"Experiences & News","Nothing can replace experiences"));
        models.add(new Model(R.drawable.about_swipe,"Who we are","We are TechGang crew of SLIIT"));
        models.add(new Model(R.drawable.contact_swipe,"Keep in touch","Contact us via E-mail & Phone"));
        models.add(new Model(R.drawable.feed_swipe,"Rate Us","We wil improve our product for you"));

        adapter=new ModelAdapter(models,this);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(40,0,50,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5)

        };

        colors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        slidr = Slidr.attach(this);

        btngo = findViewById(R.id.btngo);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, sidebar.class));
            }
        });


//        info = findViewById(R.id.btnOrder);
//        info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Coming soon more Information", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public void lockSlide(View v) {
        slidr.lock();
    }

    public void unlockSlide(View v) {
        slidr.unlock();
    }


}
