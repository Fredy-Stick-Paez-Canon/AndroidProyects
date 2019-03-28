package com.example.prueb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
    ImageButton boton1 = findViewById(R.id.button1);
    ImageButton boton2 = findViewById(R.id.button2);
    ImageButton boton3 = findViewById(R.id.button3);
    ImageButton boton11 = findViewById(R.id.button11);
    ImageButton boton22 = findViewById(R.id.button22);
    ImageButton boton33 = findViewById(R.id.button33);
    ImageButton boton111 = findViewById(R.id.button111);
    ImageButton boton222 = findViewById(R.id.button222);
    ImageButton boton333 = findViewById(R.id.button333);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton1);
            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 0;

                giraImReloj();
                cambio_ic(boton2);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton3);
            }
        });

        boton11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 0;

                giraImReloj();
                cambio_ic(boton11);
            }
        });


        boton22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton22);
            }
        });

        boton33.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 0;

                giraImReloj();
                cambio_ic(boton33);
            }
        });

        boton111.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton111);
            }
        });

        boton222.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 0;

                giraImReloj();
                cambio_ic(boton222);
            }
        });

        boton333.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                band = 1;

                giraImReloj();
                cambio_ic(boton333);
            }
        });


    }

    protected void giraImReloj(){
        ImageView imagenReloj = findViewById(R.id.reloj);

        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.INFINITE);
        imagenReloj.startAnimation(animation);
    }


    protected void cambio_ic(ImageButton btn){
        if(band==1){

            btn.setImageResource(R.drawable.ic_star);


        }else{

            btn.setImageResource(R.drawable.ic_bola);
          //  btn.setImageResource(R.mipmap.naranja);
        }

    }

}
