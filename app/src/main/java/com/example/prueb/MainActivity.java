package com.example.prueb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.*;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.plattysoft.leonids.ParticleSystem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage, contadorSeg;

    TextView conPlayer1, conPlayer2,txvWinner1;

    int btnc = 1,band = 0, conWinFresa = 0, conWinNaranja = 0,botonContador=1,matGame[][], numFreGame = 0,numNarGame = 0, resID1;

    String nombreBoton;

    boolean isRunning = false;

    public int counter=0;
    ImageButton boton00, boton01, boton02, boton10, boton11, boton12, boton20, boton21, boton22, botonClic;
    ImageButton reini, back;

    CountDownTimer cdt;
    long min =0;

    private MediaPlayer mpClic;
    private MediaPlayer mpReinicio;
    private MediaPlayer mpFondo;

    //public MainActivity(){ matGame = new int[3][3];}

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            matGame = new int[3][3];
            boton00 = findViewById(R.id.button00);
            boton01 = findViewById(R.id.button01);
            boton02 = findViewById(R.id.button02);
            boton10 = findViewById(R.id.button10);
            boton11 = findViewById(R.id.button11);
            boton12 = findViewById(R.id.button12);
            boton20 = findViewById(R.id.button20);
            boton21 = findViewById(R.id.button21);
            boton22 = findViewById(R.id.button22);
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

            boton00.setOnClickListener(this);
            boton01.setOnClickListener(this);
            boton02.setOnClickListener(this);
            boton10.setOnClickListener(this);
            boton11.setOnClickListener(this);
            boton12.setOnClickListener(this);
            boton20.setOnClickListener(this);
            boton21.setOnClickListener(this);
            boton22.setOnClickListener(this);

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
            botonContador++;
            cambio_ic(botonClic, btnc);

            int resID = v.getId();
            nombreBoton = getResources().getResourceEntryName(resID);
            llenarMatriz(nombreBoton,btnc);
            resID1 = getResources().getIdentifier(nombreBoton, "id", getPackageName());
            validarReglas();
            btnc++;

            //if(btnc > 1) {
            //    SuperIA oSIA = new SuperIA();
            //    nombreBoton = oSIA.buscarJugada(matGame, btnc);
             //   llenarMatriz(nombreBoton, btnc);


            /*    botonClic = (ImageButton) findViewById(resID1);
                cambio_ic(botonClic, btnc);

                Toast te = Toast.makeText(getApplicationContext(), "Pos a Jugar: " + nombreBoton + "\nContador " + btnc, Toast.LENGTH_SHORT);
                te.show();
            }*/

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

        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }


    protected void llenarMatriz(String posicionJugar, int btnc){
        //Toast te = Toast.makeText(getApplicationContext(), posicionJugar, Toast.LENGTH_SHORT);
        //te.show();
        /*matGame[0][0] = true;
        matGame[0][2] = true;*/

        int fila = Integer.valueOf(posicionJugar.substring(6,7));//button33
        int columna = Integer.valueOf(posicionJugar.substring(7,8));

        if (btnc % 2 != 0) {
            matGame [fila][columna] = 1;
        } else {
            matGame[fila][columna] = 2;
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
            numFreGame = 0;
            numNarGame = 0;

            validarHorizontales();

            if (numFreGame < 3 || numNarGame < 3) {
                validarVerticales();
            }

            if (numFreGame < 3 || numNarGame < 3) {
                validarDiagonales();
            }

            conPlayer1.setText(String.valueOf(conWinFresa));
            conPlayer2.setText(String.valueOf(conWinNaranja));

            if ((botonContador > 9 && band != 1)||
                    (btnc > 9 && band != 1)){
                toastColor(3);
                inhabilitarBotones();
            }

            /*if (btnc > 9 && band != 1) {
                toastColor(3);
               // btnc = btnc - 2;
                inhabilitarBotones();
            }*/

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

    protected void validarHorizontales() {
        try {
            for (int i = 0; i < matGame.length; i++) {
                for (int j = 0; j < matGame.length; j++) {

                    if (matGame[i][j] == 1) {
                        numFreGame++;
                    } else if (matGame[i][j] == 2) {
                        numNarGame++;
                    }
                }
                if (numFreGame >= 3 || numNarGame >= 3) {
                    winValidate();
                    break;
                } else {
                    numFreGame = 0;
                    numNarGame = 0;
                }
            }

        }catch (Exception e) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
        toast1.show();
    }
    }

    protected void validarVerticales(){
        try {
            for (int j = 0; j < matGame.length; j++) {
                for (int i = 0; i < matGame.length; i++) {

                    if (matGame[i][j] == 1) {
                        numFreGame++;
                    } else if (matGame[i][j] == 2) {
                        numNarGame++;
                    }
                }

                if (numFreGame >= 3 || numNarGame >= 3) {
                    winValidate();
                    break;
                } else {
                    numFreGame = 0;
                    numNarGame = 0;
                }
            }

        }catch (Exception e) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
        toast1.show();
    }
    }

    protected void validarDiagonales(){
        try {
            for (int i = 0; i < matGame.length; i++) {
                if (matGame[i][i] == 1) {
                    numFreGame++;
                } else if (matGame[i][i] == 2) {
                    numNarGame++;
                }
            }

            if (numFreGame >= 3 || numNarGame >= 3) {
                winValidate();
                return;
            } else {
                numFreGame = 0;
                numNarGame = 0;
            }

            for (int i = 0; i < matGame.length; i++) {
                for (int j = (matGame.length - 1); j < 0; j--) {

                    if (matGame[i][j] == 1) {
                        numFreGame++;
                    } else if (matGame[i][j] == 2) {
                        numNarGame++;
                    }

                    if (numFreGame >= 3 || numNarGame >= 3) {
                        winValidate();
                        break;
                    } else {
                        numFreGame = 0;
                        numNarGame = 0;
                    }
                }
            }
        }catch (Exception e) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
        toast1.show();
    }
    }

    protected void winValidate(){
        try{
            if(numFreGame == 3){
                ganaste(2, resID1);
                toastColor(1);
                inhabilitarBotones();
                band = 1;
                conWinFresa++;
            }
            else if(numNarGame == 3){
                ganaste(1, resID1);
                toastColor(2);
                inhabilitarBotones();
                band = 1;
                conWinNaranja++;
            }

            numFreGame = 0;
            numNarGame = 0;

        }catch (Exception e) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();
        }
        }

    protected void inhabilitarBotones() {
        try {
            boton00.setEnabled(false);
            boton01.setEnabled(false);
            boton02.setEnabled(false);
            boton10.setEnabled(false);
            boton11.setEnabled(false);
            boton12.setEnabled(false);
            boton20.setEnabled(false);
            boton21.setEnabled(false);
            boton22.setEnabled(false);
        } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
            toast1.show();

        }
    }

    protected void habilitarJuego() {
        try {

            mpReinicio.start();
            boton00.setEnabled(true);
            boton01.setEnabled(true);
            boton02.setEnabled(true);
            boton10.setEnabled(true);
            boton11.setEnabled(true);
            boton12.setEnabled(true);
            boton20.setEnabled(true);
            boton21.setEnabled(true);
            boton22.setEnabled(true);

            boton00.setBackgroundResource(0);
            boton01.setBackgroundResource(0);
            boton02.setBackgroundResource(0);
            boton10.setBackgroundResource(0);
            boton11.setBackgroundResource(0);
            boton12.setBackgroundResource(0);
            boton20.setBackgroundResource(0);
            boton21.setBackgroundResource(0);
            boton22.setBackgroundResource(0);

            boton00.setBackgroundResource(R.drawable.stylebuttonprin);
            boton01.setBackgroundResource(R.drawable.stylebuttonprin);
            boton02.setBackgroundResource(R.drawable.stylebuttonprin);
            boton10.setBackgroundResource(R.drawable.stylebuttonprin);
            boton11.setBackgroundResource(R.drawable.stylebuttonprin);
            boton12.setBackgroundResource(R.drawable.stylebuttonprin);
            boton20.setBackgroundResource(R.drawable.stylebuttonprin);
            boton21.setBackgroundResource(R.drawable.stylebuttonprin);
            boton22.setBackgroundResource(R.drawable.stylebuttonprin);

            btnc = 1;
            matGame = new int[3][3];
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
                .setTitle("¡"+Pwin+" Gana!")
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

    protected void ganaste(int winner, int resID1) {

        if (winner==2) {

            new ParticleSystem(this, 150, R.drawable.ic_confe1, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_bola, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_star, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

        }

        if (winner==1) {

            new ParticleSystem(this, 80, R.drawable.ic_confe2, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_bola2, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

            new ParticleSystem(this, 80, R.drawable.ic_star1, 10000)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeed(144)
                    .oneShot(findViewById(resID1), 80);

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

