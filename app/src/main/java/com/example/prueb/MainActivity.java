package com.example.prueb;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage, contadorSeg, conPlkayer1, conPlkayer2;

    int btnc = 1,fruit1=0,fruit2=0,fruit3=0,fruit11=0,fruit22=0,fruit33=0,fruit111=0,fruit222=0,
            fruit333=0,band=0, conWinFresa, conWinNaranja;

    ImageButton boton1,boton2,boton3,boton11,boton22,boton33,boton111,boton222,boton333;
    ImageButton reini, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = findViewById(R.id.button1);
        boton2 = findViewById(R.id.button2);
        boton3 = findViewById(R.id.button3);
        boton11 = findViewById(R.id.button11);
        boton22 = findViewById(R.id.button22);
        boton33 = findViewById(R.id.button33);
        boton111 = findViewById(R.id.button111);
        boton222 = findViewById(R.id.button222);
        boton333 = findViewById(R.id.button333);
        reini = findViewById(R.id.imbReiniciar);
        //back = findViewById(R.id.imbReiniciar);
        contadorSeg = findViewById(R.id.txvTimer);
        conPlkayer1 = findViewById(R.id.conta1);
        conPlkayer2 = findViewById(R.id.conta2);

        reini.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                habilitarJuego();

            }
        });

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton11.setOnClickListener(this);
        boton22.setOnClickListener(this);
        boton33.setOnClickListener(this);
        boton111.setOnClickListener(this);
        boton222.setOnClickListener(this);
        boton333.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                btnc = btnc +1;
                cambio_ic(boton1,btnc);
                break;

            case R.id.button2:
                btnc = btnc +1;
                cambio_ic(boton2,btnc);
                break;

            case R.id.button3:
                btnc = btnc +1;
                cambio_ic(boton3,btnc);
                break;

            case R.id.button11:
                btnc = btnc +1;
                cambio_ic(boton11,btnc);
                break;

            case R.id.button22:
                btnc = btnc +1;
                cambio_ic(boton22,btnc);
                break;

            case R.id.button33:
                btnc = btnc +1;
                cambio_ic(boton33,btnc);
                break;

            case R.id.button111:
                btnc = btnc +1;
                cambio_ic(boton111,btnc);
                break;

            case R.id.button222:
                btnc = btnc +1;
                cambio_ic(boton222,btnc);
                break;

            case R.id.button333:
                btnc = btnc +1;
                cambio_ic(boton333,btnc);
                break;
        }
    }

    protected void cambio_ic(ImageButton btn, int btncc){


    //
        //    girarImReloj();

        if(btncc%2!=0){

            btn.setBackgroundResource(R.mipmap.fresa);
            if(btn == boton1){
                fruit1 =1;
                boton1.setEnabled(false);

            }
            if(btn == boton2){
                fruit2 =1;
                boton2.setEnabled(false);
            }
            if(btn == boton3){
                fruit3 =1;
                boton3.setEnabled(false);
            }

            if(btn == boton11){
                fruit11 =1;
                boton11.setEnabled(false);
            }
            if(btn == boton22){
                fruit22 =1;
                boton22.setEnabled(false);
            }
            if(btn == boton33){
                fruit33 =1;
                boton33.setEnabled(false);
            }

            if(btn == boton111){
                fruit111 =1;
                boton111.setEnabled(false);
            }
            if(btn == boton222){
                fruit222 =1;
                boton222.setEnabled(false);
            }
            if(btn == boton333){
                fruit333 =1;
                boton333.setEnabled(false);
            }

        }else{

            btn.setBackgroundResource(R.mipmap.naranja);
            if(btn == boton1){
                fruit1 =2;
                boton1.setEnabled(false);
            }
            if(btn == boton2){
                fruit2 =2;
                boton2.setEnabled(false);
            }
            if(btn == boton3){
                fruit3 =2;
                boton3.setEnabled(false);
            }

            if(btn == boton11){
                fruit11 =2;
                boton11.setEnabled(false);
            }
            if(btn == boton22){
                fruit22 =2;
                boton22.setEnabled(false);
            }
            if(btn == boton33){
                fruit33 =2;
                boton33.setEnabled(false);
            }

            if(btn == boton111){
                fruit111 =2;
                boton111.setEnabled(false);
            }
            if(btn == boton222){
                fruit222 =2;
                boton222.setEnabled(false);
            }
            if(btn == boton333){
                fruit333 =2;
                boton333.setEnabled(false);
            }
        }

        validarReglas();
    }

    protected void girarImReloj() throws InterruptedException {
        Thread.sleep(1000);
        int segundos = 6;
        contadorSeg.setText(segundos);
        segundos--;

        ImageView imagenReloj = findViewById(R.id.reloj);

        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.INFINITE);
        imagenReloj.startAnimation(animation);
    }

    protected void validarReglas(){

        if( fruit1 ==1 && fruit2==1 && fruit3==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }
        if( fruit11 ==1 && fruit22==1 && fruit33==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }
        if( fruit111 ==1 && fruit222==1 && fruit333==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }
        if( fruit1 ==1 && fruit11==1 && fruit111==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit2 ==1 && fruit22==1 && fruit222==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit3 ==1 && fruit33==1 && fruit333==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        ///// diagonales

        if( fruit1 ==1 && fruit22==1 && fruit333==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }


        if( fruit111 ==1 && fruit22==1 && fruit3==1 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Fresa ", Toast.LENGTH_SHORT);
            conWinFresa++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        ///Naranja-----------------------


        if( fruit1 ==2 && fruit2==2 && fruit3==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja ", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit11 ==2 && fruit22==2 && fruit33==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja ", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit111 ==2 && fruit222==2 && fruit333==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit1 ==2 && fruit11==2 && fruit111==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit2 ==2 && fruit22==2 && fruit222==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit3 ==2 && fruit33==2 && fruit333==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        ///// diagonales

        if( fruit1 ==2 && fruit22==2 && fruit333==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        if( fruit111 ==2 && fruit22==2 && fruit3==2 ){

            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Naranja", Toast.LENGTH_SHORT);
            conWinNaranja++;

            toast1.show();
            inhabilitarBotones();
            band =1;
        }

        conPlkayer1.setText(conWinFresa);
        conPlkayer1.setText(conWinNaranja);

        if (btnc >9 && band !=1){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Empate", Toast.LENGTH_SHORT);

            toast1.show();
            inhabilitarBotones();
        }



    }


    protected void inhabilitarBotones(){

        boton1.setEnabled(false);
        boton2.setEnabled(false);
        boton3.setEnabled(false);
        boton11.setEnabled(false);
        boton22.setEnabled(false);
        boton33.setEnabled(false);
        boton111.setEnabled(false);
        boton222.setEnabled(false);
        boton333.setEnabled(false);
    }


    protected void habilitarJuego(){

        boton1.setEnabled(true);
        boton2.setEnabled(true);
        boton3.setEnabled(true);
        boton11.setEnabled(true);
        boton22.setEnabled(true);
        boton33.setEnabled(true);
        boton111.setEnabled(true);
        boton222.setEnabled(true);
        boton333.setEnabled(true);

        boton1.setBackgroundResource(0);
        boton2.setBackgroundResource(0);
        boton3.setBackgroundResource(0);
        boton11.setBackgroundResource(0);
        boton22.setBackgroundResource(0);
        boton33.setBackgroundResource(0);
        boton111.setBackgroundResource(0);
        boton222.setBackgroundResource(0);
        boton333.setBackgroundResource(0);

        boton1.setBackgroundResource(R.drawable.stylebuttonprin);
        boton2.setBackgroundResource(R.drawable.stylebuttonprin);
        boton3.setBackgroundResource(R.drawable.stylebuttonprin);
        boton11.setBackgroundResource(R.drawable.stylebuttonprin);
        boton22.setBackgroundResource(R.drawable.stylebuttonprin);
        boton33.setBackgroundResource(R.drawable.stylebuttonprin);
        boton111.setBackgroundResource(R.drawable.stylebuttonprin);
        boton222.setBackgroundResource(R.drawable.stylebuttonprin);
        boton333.setBackgroundResource(R.drawable.stylebuttonprin);


        btnc = 1;
        fruit1=0;
        fruit2=0;
        fruit3=0;
        fruit11=0;
        fruit22=0;
        fruit33=0;
        fruit111=0;
        fruit222=0;
        fruit333=0;
        band =5;
    }


}
