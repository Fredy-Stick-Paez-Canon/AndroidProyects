package com.example.prueb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    int btnc = 0, band=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton boton1 = findViewById(R.id.button1);
        final ImageButton boton2 = findViewById(R.id.button2);
        ImageButton boton3 = findViewById(R.id.button3);
        ImageButton boton11 = findViewById(R.id.button11);
        ImageButton boton22 = findViewById(R.id.button22);
        ImageButton boton33 = findViewById(R.id.button33);
        ImageButton boton111 = findViewById(R.id.button111);
        ImageButton boton222 = findViewById(R.id.button222);
        ImageButton boton333 = findViewById(R.id.button333);
/*
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton1);
            }
        });

*/
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                giraImReloj();

              //  boton1.setImageResource(R.drawable.ic_star);
                boton1.setBackgroundResource(R.mipmap.naranja);
            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                girarImReloj();

                //  boton1.setImageResource(R.drawable.ic_star);
                boton2.setBackgroundResource(R.mipmap.fresa);
            }
        });



    }

    protected void girarImReloj(){
        ImageView imagenReloj = findViewById(R.id.reloj);

        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.INFINITE);
        imagenReloj.startAnimation(animation);
    }


    /*
    protected void cambio_ic(ImageButton btn){
        if(band==1){

            btn.setImageResource(R.drawable.ic_star);



        }else{

            btn.setImageResource(R.drawable.ic_bola);
          //  btn.setImageResource(R.mipmap.naranja);
        }

    }
*/
}
