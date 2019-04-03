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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage, contadorSeg;

    TextView conPlayer1, conPlayer2,txvWinner1;

    int btnc = 1, fruit1 = 0, fruit2 = 0, fruit3 = 0, fruit11 = 0, fruit22 = 0, fruit33 = 0, fruit111 = 0, fruit222 = 0,
            fruit333 = 0, band = 0, conWinFresa = 0, conWinNaranja = 0;

    boolean isRunning = false;

    public int counter=0;ImageButton boton1, boton2, boton3, boton11, boton22, boton33, boton111, boton222, boton333;
    ImageButton reini, back;

    CountDownTimer cdt;
    long min =0;

    private MediaPlayer mp;
    private MediaPlayer mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
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
         //   contadorSeg = findViewById(R.id.txvTimer);
            conPlayer1 = findViewById(R.id.conta1);
            conPlayer2 = findViewById(R.id.conta2);
            txvWinner1 = findViewById(R.id.crono);

            mp = MediaPlayer.create(this, R.raw.clic);
            mp1 = MediaPlayer.create(this, R.raw.reinicio);

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

        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    @Override
    public void onClick(View v) {
        try {
            mp.start();
            switch (v.getId()) {
                case R.id.button1:
                    btnc = btnc + 1;
                    cambio_ic(boton1, btnc);
                    break;

                case R.id.button2:
                    btnc = btnc + 1;
                    cambio_ic(boton2, btnc);
                    break;

                case R.id.button3:
                    btnc = btnc + 1;
                    cambio_ic(boton3, btnc);
                    break;

                case R.id.button11:
                    btnc = btnc + 1;
                    cambio_ic(boton11, btnc);
                    break;

                case R.id.button22:
                    btnc = btnc + 1;
                    cambio_ic(boton22, btnc);
                    break;

                case R.id.button33:
                    btnc = btnc + 1;
                    cambio_ic(boton33, btnc);
                    break;

                case R.id.button111:
                    btnc = btnc + 1;
                    cambio_ic(boton111, btnc);
                    break;

                case R.id.button222:
                    btnc = btnc + 1;
                    cambio_ic(boton222, btnc);
                    break;

                case R.id.button333:
                    btnc = btnc + 1;
                    cambio_ic(boton333, btnc);
                    break;
            }
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void cambio_ic(ImageButton btn, int btncc) {

        try {
                girarImReloj(0);
                cronometro();

            if (btncc % 2 != 0) {

                btn.setBackgroundResource(R.mipmap.fresa);
                if (btn == boton1) {
                    fruit1 = 1;
                    boton1.setEnabled(false);

                }
                if (btn == boton2) {
                    fruit2 = 1;
                    boton2.setEnabled(false);
                }
                if (btn == boton3) {
                    fruit3 = 1;
                    boton3.setEnabled(false);
                }

                if (btn == boton11) {
                    fruit11 = 1;
                    boton11.setEnabled(false);
                }
                if (btn == boton22) {
                    fruit22 = 1;
                    boton22.setEnabled(false);
                }
                if (btn == boton33) {
                    fruit33 = 1;
                    boton33.setEnabled(false);
                }

                if (btn == boton111) {
                    fruit111 = 1;
                    boton111.setEnabled(false);
                }
                if (btn == boton222) {
                    fruit222 = 1;
                    boton222.setEnabled(false);
                }
                if (btn == boton333) {
                    fruit333 = 1;
                    boton333.setEnabled(false);
                }
            } else {
                btn.setBackgroundResource(R.mipmap.naranja);
                if (btn == boton1) {
                    fruit1 = 2;
                    boton1.setEnabled(false);
                }
                if (btn == boton2) {
                    fruit2 = 2;
                    boton2.setEnabled(false);
                }
                if (btn == boton3) {
                    fruit3 = 2;
                    boton3.setEnabled(false);
                }

                if (btn == boton11) {
                    fruit11 = 2;
                    boton11.setEnabled(false);
                }
                if (btn == boton22) {
                    fruit22 = 2;
                    boton22.setEnabled(false);
                }
                if (btn == boton33) {
                    fruit33 = 2;
                    boton33.setEnabled(false);
                }

                if (btn == boton111) {
                    fruit111 = 2;
                    boton111.setEnabled(false);
                }

                if (btn == boton222) {
                    fruit222 = 2;
                    boton222.setEnabled(false);
                }
                if (btn == boton333) {
                    fruit333 = 2;
                    boton333.setEnabled(false);
                }
            }

            validarReglas();
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
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

    protected void validarReglas() {
        try {
            if (fruit1 == 1 && fruit2 == 1 && fruit3 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }
            if (fruit11 == 1 && fruit22 == 1 && fruit33 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }
            if (fruit111 == 1 && fruit222 == 1 && fruit333 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }
            if (fruit1 == 1 && fruit11 == 1 && fruit111 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }
            if (fruit2 == 1 && fruit22 == 1 && fruit222 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }
            if (fruit3 == 1 && fruit33 == 1 && fruit333 == 1) {
                //toastColor(1);
                ganaste(2);
                conWinFresa++;
                inhabilitarBotones();
                band = 1;
            }

            ///// diagonales

            if (fruit1 == 1 && fruit22 == 1 && fruit333 == 1) {
                conWinFresa++;
                ganaste(2);
                //toastColor(1);
                inhabilitarBotones();
                band = 1;
            }
            if (fruit111 == 1 && fruit22 == 1 && fruit3 == 1) {
                conWinFresa++;
                //toastColor(1);
                ganaste(2);
                inhabilitarBotones();
                band = 1;
            }

            ///Naranja-----------------------


            if (fruit1 == 2 && fruit2 == 2 && fruit3 == 2) {
                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit11 == 2 && fruit22 == 2 && fruit33 == 2) {
                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit111 == 2 && fruit222 == 2 && fruit333 == 2) {
                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit1 == 2 && fruit11 == 2 && fruit111 == 2) {
                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit2 == 2 && fruit22 == 2 && fruit222 == 2) {

                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit3 == 2 && fruit33 == 2 && fruit333 == 2) {
                ganaste(1);
                conWinNaranja++;
                //toastColor(2);
                inhabilitarBotones();
                band = 1;
            }

            ///// diagonales

            if (fruit1 == 2 && fruit22 == 2 && fruit333 == 2) {

                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            if (fruit111 == 2 && fruit22 == 2 && fruit3 == 2) {

                conWinNaranja++;
                //toastColor(2);
                ganaste(1);
                inhabilitarBotones();
                band = 1;
            }

            conPlayer1.setText(String.valueOf(conWinFresa));
            conPlayer2.setText(String.valueOf(conWinNaranja));

            if (btnc > 9 && band != 1) {
                toastColor(3);
                inhabilitarBotones();
            }

            if (conWinNaranja == 3 ) {

                //incluir popup
                popup("Naranja","Fresa",conWinNaranja,conWinFresa);
            }

            if (conWinFresa == 3 ) {

                //incluir popup
                popup("Fresa","Naranja",conWinFresa,conWinNaranja);
            }

        } catch (Exception e) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    protected void inhabilitarBotones() {
        try {
            boton1.setEnabled(false);
            boton2.setEnabled(false);
            boton3.setEnabled(false);
            boton11.setEnabled(false);
            boton22.setEnabled(false);
            boton33.setEnabled(false);
            boton111.setEnabled(false);
            boton222.setEnabled(false);
            boton333.setEnabled(false);
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void habilitarJuego() {
        try {

            mp1.start();
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
            fruit1 = 0;
            fruit2 = 0;
            fruit3 = 0;
            fruit11 = 0;
            fruit22 = 0;
            fruit33 = 0;
            fruit111 = 0;
            fruit222 = 0;
            fruit333 = 0;
            band = 5;

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
                    .setDuration(Style.DURATION_MEDIUM)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_ORANGE))
                    .setAnimations(Style.ANIMATIONS_POP).show();
        }

        if(color==1){
            SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                    .setText("Punto para Fresa")
                    .setDuration(Style.DURATION_MEDIUM)
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
        girarCrono(2);

        if(!isRunning) {
            cdt = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {

                    txvWinner1.setText("" + millisUntilFinished / 1100);
                    min = millisUntilFinished / 1000;

                    if(min==3){
                        txvWinner1.setTextColor(Color.rgb(255, 0, 0));
                        girarCrono(1);
                    }
                    if(min <= 5){
                        girarImReloj((int)min);
                    }
                }

                public void onFinish() {
                    btnc = btnc + 1;
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

    protected void girarCrono(int fla) {


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
    }

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

}