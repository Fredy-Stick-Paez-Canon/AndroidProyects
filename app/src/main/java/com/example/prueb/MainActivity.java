package com.example.prueb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.plattysoft.leonids.ParticleSystem;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage, contadorSeg;

    TextView conPlayer1, conPlayer2,txvWinner1;

    int btnc = 1,band = 0, conWinFresa = 0, conWinNaranja = 0,botonContador=1;
    //int fruit1 = 0, fruit2 = 0, fruit3 = 0, fruit11 = 0, fruit22 = 0, fruit33 = 0, fruit111 = 0, fruit222 = 0, fruit333 = 0;

    boolean isRunning = false, matGame[][];

    public int counter=0;
    ImageButton boton11, boton12, boton13, boton21, boton22, boton23, boton31, boton32, boton33, botonClic;
    ImageButton reini, back;

    CountDownTimer cdt;
    long min =0;

    private MediaPlayer mpClic;
    private MediaPlayer mpReinicio;
    private MediaPlayer mpFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            boton11 = findViewById(R.id.button11);
            boton12 = findViewById(R.id.button12);
            boton13 = findViewById(R.id.button13);
            boton21 = findViewById(R.id.button21);
            boton22 = findViewById(R.id.button22);
            boton23 = findViewById(R.id.button23);
            boton31 = findViewById(R.id.button31);
            boton32 = findViewById(R.id.button32);
            boton33 = findViewById(R.id.button33);
            reini = findViewById(R.id.imbReiniciar);
            //back = findViewById(R.id.imbReiniciar);
         //   contadorSeg = findViewById(R.id.txvTimer);
            conPlayer1 = findViewById(R.id.conta1);
            conPlayer2 = findViewById(R.id.conta2);
            txvWinner1 = findViewById(R.id.crono);

            mpClic = MediaPlayer.create(this, R.raw.clic);
            mpReinicio = MediaPlayer.create(this, R.raw.reinicio);
            mpFondo = MediaPlayer.create(this, R.raw.fondo);
            mpFondo.setLooping(true);
            mpFondo.start();

            reini.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    habilitarJuego();
                }
            });

            boton11.setOnClickListener(this);
            boton12.setOnClickListener(this);
            boton13.setOnClickListener(this);
            boton21.setOnClickListener(this);
            boton22.setOnClickListener(this);
            boton23.setOnClickListener(this);
            boton31.setOnClickListener(this);
            boton32.setOnClickListener(this);
            boton33.setOnClickListener(this);

        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    @Override
    public void onClick(View v) {
        try {
            mpClic.start();
            botonClic = (ImageButton) v;
            btnc++;
            botonContador++;
            cambio_ic(botonClic, btnc);

            int resID = v.getId();
            String nombre = getResources().getResourceEntryName(resID);


            Toast te = Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_SHORT);
            te.show();

        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void cambio_ic(ImageButton botonClic, int btncc) {

        try {
            girarImReloj(0);
            cronometro();

            if (btncc % 2 != 0) {
                botonClic.setBackgroundResource(R.mipmap.fresa);
            } else {
                botonClic.setBackgroundResource(R.mipmap.naranja);
            }
            botonClic.setEnabled(false);
            //llenarMatriz(botonClic);
         //   validarReglas();
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void llenarMatriz(ImageButton botonClic){
        int fila, columna;

    }

    protected void girarImReloj(int seg) {

        ImageView imagenReloj = findViewById(R.id.reloj);

        RotateAnimation animation = new RotateAnimation( 0,360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        if (seg <= 5 && seg != 0)
            animation.setDuration(900);
        else
            animation.setDuration(2000);

        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.INFINITE);
        imagenReloj.startAnimation(animation);
    }

    /*protected void validarReglas() {
        try {
            if ((fruit1 == 1 && fruit2 == 1 && fruit3 == 1)||
                    (fruit11 == 1 && fruit22 == 1 && fruit33 == 1)||
                    (fruit111 == 1 && fruit222 == 1 && fruit333 == 1)||
                    (fruit1 == 1 && fruit11 == 1 && fruit111 == 1)||
                    (fruit2 == 1 && fruit22 == 1 && fruit222 == 1)||
                    (fruit3 == 1 && fruit33 == 1 && fruit333 == 1)||
                    (fruit1 == 1 && fruit22 == 1 && fruit333 == 1)||
                    (fruit111 == 1 && fruit22 == 1 && fruit3 == 1)){
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }


            if ((fruit1 == 2 && fruit2 == 2 && fruit3 == 2)||
                    (fruit11 == 2 && fruit22 == 2 && fruit33 == 2)||
                    (fruit111 == 2 && fruit222 == 2 && fruit333 == 2)||
                    (fruit1 == 2 && fruit11 == 2 && fruit111 == 2)||
                    (fruit2 == 2 && fruit22 == 2 && fruit222 == 2)||
                    (fruit3 == 2 && fruit33 == 2 && fruit333 == 2)||
                    (fruit1 == 2 && fruit22 == 2 && fruit333 == 2)||
                    (fruit111 == 2 && fruit22 == 2 && fruit3 == 2)){
                conWinNaranja++;
                toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            conPlayer1.setText(String.valueOf(conWinFresa));
            conPlayer2.setText(String.valueOf(conWinNaranja));



            if (botonContador > 9 && band != 1) {
                toastColor(3);
                inhabilitarBotones();
            }

            if (btnc > 9 && band != 1) {
                toastColor(3);
               // btnc = btnc - 2;
                inhabilitarBotones();
            }


            if (conWinNaranja == 3 ) {

                incluir popup
                popup("Naranja","Fresa",conWinNaranja,conWinFresa);
            }

            if (conWinFresa == 3 ) {

                incluir popup
                popup("Fresa","Naranja",conWinFresa,conWinNaranja);
            }

        } catch (Exception e) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();
        }
    }*/

    protected void inhabilitarBotones() {
        try {
            boton11.setEnabled(false);
            boton12.setEnabled(false);
            boton13.setEnabled(false);
            boton21.setEnabled(false);
            boton22.setEnabled(false);
            boton23.setEnabled(false);
            boton31.setEnabled(false);
            boton32.setEnabled(false);
            boton33.setEnabled(false);
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void habilitarJuego() {
        try {

            mpReinicio.start();
            boton11.setEnabled(true);
            boton12.setEnabled(true);
            boton13.setEnabled(true);
            boton21.setEnabled(true);
            boton22.setEnabled(true);
            boton23.setEnabled(true);
            boton31.setEnabled(true);
            boton32.setEnabled(true);
            boton33.setEnabled(true);

            boton11.setBackgroundResource(0);
            boton12.setBackgroundResource(0);
            boton13.setBackgroundResource(0);
            boton21.setBackgroundResource(0);
            boton22.setBackgroundResource(0);
            boton23.setBackgroundResource(0);
            boton31.setBackgroundResource(0);
            boton32.setBackgroundResource(0);
            boton33.setBackgroundResource(0);

            boton11.setBackgroundResource(R.drawable.stylebuttonprin);
            boton12.setBackgroundResource(R.drawable.stylebuttonprin);
            boton13.setBackgroundResource(R.drawable.stylebuttonprin);
            boton21.setBackgroundResource(R.drawable.stylebuttonprin);
            boton22.setBackgroundResource(R.drawable.stylebuttonprin);
            boton23.setBackgroundResource(R.drawable.stylebuttonprin);
            boton31.setBackgroundResource(R.drawable.stylebuttonprin);
            boton32.setBackgroundResource(R.drawable.stylebuttonprin);
            boton33.setBackgroundResource(R.drawable.stylebuttonprin);

            btnc = 1;
        /*    fruit1 = 0;
            fruit2 = 0;
            fruit3 = 0;
            fruit11 = 0;
            fruit22 = 0;
            fruit33 = 0;
            fruit111 = 0;
            fruit222 = 0;
            fruit333 = 0;*/
            band = 5;
            botonContador=1;

        //    cdt.cancel();
            txvWinner1.setText("10");
            txvWinner1.setTextColor(Color.rgb(53,234,99));

        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    public void popup(String Pwin,String Pperd,int win1, int perd1){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Pwin+ " "+win1+" VS "+perd1+" "+Pperd)
                .setTitle(Pwin+"  Gano")
                .setCancelable(false)
                .setPositiveButton("Reiniciar Juego",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                habilitarJuego();
                                conPlayer1.setText("0");
                                conPlayer2.setText("0");
                                conWinNaranja =0;
                                conWinFresa =0;
                                txvWinner1.setTextColor(Color.rgb(53,234,99));
                                cdt.cancel();
                                txvWinner1.setText("10");
                            }
                        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void toastColor(int color){

        if(color ==2){
            SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)

                    .setText("Punto para Naranja")
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_ORANGE))
                    .setAnimations(Style.ANIMATIONS_POP).show();
        }

        if(color==1){
            SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                    .setText("Punto para Fresa")
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                    .setAnimations(Style.ANIMATIONS_POP).show();
        }

        if(color==3){
            SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                    .setText("Empate")
                    .setDuration(Style.DURATION_MEDIUM)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE))
                    .setAnimations(Style.ANIMATIONS_POP).show();
        }

        if(color==4){
            SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                    .setText("Siguiente Turno")
                    .setDuration(Style.DURATION_MEDIUM)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE_GREY))
                    .setAnimations(Style.ANIMATIONS_POP).show();

        }
    }

    public void cronometro(){
        txvWinner1.setTextColor(Color.rgb(53,234,99));
        //girarCrono(2);

        if(!isRunning) {
            cdt = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {

                    txvWinner1.setText("" + millisUntilFinished / 1100);
                    min = millisUntilFinished / 1000;

                    if(min==3){
                        txvWinner1.setTextColor(Color.rgb(255, 0, 0));
                        //girarCrono(1);
                    }
                    if(min <= 5){
                        girarImReloj((int)min);
                    }
                }

                public void onFinish() {

                    btnc = btnc +1;
                    toastColor(4);
                }
            }.start();
            isRunning = true;
        }else if(min!=10){
            cdt.cancel();
            cdt.start();
           // isRunning = false;
        }
    }

    /*protected void girarCrono(int fla) {

        RotateAnimation animation = new RotateAnimation(0, 40,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        if(fla ==1) {
            animation.setDuration(2000);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.INFINITE);
            txvWinner1.startAnimation(animation);
        }else{
            animation.cancel();
        }
    }*/

    protected void ganaste(int winner) {

        if (winner==2) {

            new ParticleSystem(this, 150, R.drawable.ic_confe1, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_bola, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_star, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta1), 80);

        }

        if (winner==1) {

            new ParticleSystem(this, 80, R.drawable.ic_confe2, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta2), 80);

            new ParticleSystem(this, 80, R.drawable.ic_bola2, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta2), 80);

            new ParticleSystem(this, 80, R.drawable.ic_star1, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(R.id.conta2), 80);

        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(mpFondo.isPlaying()){
            mpFondo.stop();
            mpFondo.release();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mpFondo.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mpFondo.pause();
    }
}