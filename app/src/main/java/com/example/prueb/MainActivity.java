package com.example.prueb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.*;
import android.widget.*;
import com.github.johnpersano.supertoasts.library.*;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.plattysoft.leonids.ParticleSystem;
import android.os.Vibrator;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView conPlayer1, conPlayer2,txvCronometro;

    public int shiftCounter = 1,band = 0, conWinFresa = 0, conWinNaranja = 0,botonContador=1,arrayGame[][], numFreGame = 0,numNarGame = 0, resID1, resIdFruta1, resIdFruta2, numberSpaces = 0 , resMpBackground;

    float mpVolMax = (float)0.3;

    String nombreBoton, nombreFruta1, nombreFruta2, nameMpBackground;

    boolean isRunning = false, isRoting = false;

    ImageView viewFruta1, viewFruta2, imagenReloj;

    public int counter=0;
    ImageButton boton00, boton01, boton02, boton10, boton11, boton12, boton20, boton21, boton22, botonClic;
    ImageButton reini, back;

    CountDownTimer cdt;
    long min =0;

    private MediaPlayer mpClic;
    private MediaPlayer mpReinicio;
    private MediaPlayer mpBackgroun;
    private MediaPlayer mpFlash;
    Vibrator vibraWinner;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            arrayGame = new int[3][3];
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
            conPlayer1 = findViewById(R.id.conta1);
            conPlayer2 = findViewById(R.id.conta2);
            txvCronometro = findViewById(R.id.crono);
            viewFruta1 = findViewById(R.id.imvFruta1);
            viewFruta2 = findViewById(R.id.imvFruta2);

            mpClic = MediaPlayer.create(this, R.raw.clic);
            mpClic.setVolume(mpVolMax,(mpVolMax));

            mpReinicio = MediaPlayer.create(this, R.raw.reinicio);
            mpReinicio.setVolume(mpVolMax,(mpVolMax));

            nameMpBackground = "background" + (int) (Math.random() * 3);
            Toast.makeText(getApplicationContext(),nameMpBackground,Toast.LENGTH_SHORT).show();
            resMpBackground = getResources().getIdentifier(nameMpBackground, "raw", getPackageName());
            mpBackgroun = MediaPlayer.create(this, resMpBackground);
            mpBackgroun.setVolume(mpVolMax,(mpVolMax));
            mpBackgroun.setLooping(true);
            mpBackgroun.start();

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

            do{
                nombreFruta1 = "im" + (int) (Math.random() * 9);
                nombreFruta2 = "im" + (int) (Math.random() * 9);
            }while(nombreFruta1.equals(nombreFruta2));

            resIdFruta1 = getResources().getIdentifier(nombreFruta1, "mipmap", getPackageName());
            resIdFruta2 = getResources().getIdentifier(nombreFruta2, "mipmap", getPackageName());

            viewFruta1.setImageResource(resIdFruta1);
            viewFruta2.setImageResource(resIdFruta2);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            girarImReloj(0);
            mpClic.start();
            botonClic = (ImageButton) v;
            botonContador++;
            changeIcon(botonClic, shiftCounter);

            int resID = v.getId();
            nombreBoton = getResources().getResourceEntryName(resID);
            llenarMatriz(nombreBoton,shiftCounter);
            resID1 = getResources().getIdentifier(nombreBoton, "id", getPackageName());
            shiftCounter++;
            numberSpaces = 0;
            checkSpacesGame();
            validarReglas();
            if (band != 1 && numberSpaces > 0) {
                disableButtons();
                playIA();
                validarReglas();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void changeIcon(ImageButton botonClic, int shiftCounter) {

        try {
            if(band == 1){
                isRunning = false;
                cdt.cancel();
            }else {
                cronometro();
            }

            if (shiftCounter % 2 != 0) {
                botonClic.setBackgroundResource(resIdFruta1);
            } else {
                botonClic.setBackgroundResource(resIdFruta2);
            }
            botonClic.setEnabled(false);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }


    protected void llenarMatriz(String posicionJugar, int shiftCounter){
        int fila = Integer.valueOf(posicionJugar.substring(6,7));//button33
        int columna = Integer.valueOf(posicionJugar.substring(7,8));

        if (shiftCounter % 2 != 0) {
            arrayGame [fila][columna] = 1;
        } else {
            arrayGame[fila][columna] = 2;
        }
    }

    protected void girarImReloj(int seg) {

        imagenReloj = findViewById(R.id.reloj);

        Animation animation = null;

        if(!isRoting) {

            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotateanimator);

            imagenReloj.startAnimation(animation);

            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.INFINITE);

            imagenReloj.startAnimation(animation);
            animation.setDuration(7000);

        }else{
            imagenReloj.clearAnimation();
        }

        /*if (seg <= 3 && seg != 0)
            animation.setDuration(4000);
        else*/
    }

    protected void validarReglas() {
        try {
            numFreGame = 0;
            numNarGame = 0;

            validarHorizontales();

            if (numFreGame < 3 && numNarGame < 3) {
                validarVerticales();
            }

            if (numFreGame < 3 && numNarGame < 3) {
                validarDiagonales();
            }

            conPlayer1.setText(String.valueOf(conWinFresa));
            conPlayer2.setText(String.valueOf(conWinNaranja));

            if (band != 1 && numberSpaces <= 0){
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
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    protected void validarHorizontales() {
        try {
            for (int i = 0; i < arrayGame.length; i++) {
                for (int j = 0; j < arrayGame.length; j++) {

                    if (arrayGame[i][j] == 1) {
                        numFreGame++;
                    } else if (arrayGame[i][j] == 2) {
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
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    protected void validarVerticales(){
        try {
            for (int j = 0; j < arrayGame.length; j++) {
                for (int i = 0; i < arrayGame.length; i++) {

                    if (arrayGame[i][j] == 1) {
                        numFreGame++;
                    } else if (arrayGame[i][j] == 2) {
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
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    protected void validarDiagonales(){
        try {
            for (int i = 0; i < arrayGame.length; i++) {
                if (arrayGame[i][i] == 1) {
                    numFreGame++;
                } else if (arrayGame[i][i] == 2) {
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

            int j = arrayGame.length-1;

            for(int i = 0; i < arrayGame.length; i++){
                if (arrayGame[i][j] == 1) {
                    numFreGame++;
                } else if (arrayGame[i][j] == 2) {
                    numNarGame++;
                }
                j--;
            }

            if (numFreGame >= 3 || numNarGame >= 3) {
                winValidate();
                return;
            } else {
                numFreGame = 0;
                numNarGame = 0;
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    protected void winValidate(){
        try{
            if(numFreGame == 3){
                ganaste(2, resID1);
                toastColor(1);
                band = 1;
                conWinFresa++;
                inhabilitarBotones();
            }
            else if(numNarGame == 3){
                ganaste(1, resID1);
                toastColor(2);
                band = 1;
                conWinNaranja++;
                inhabilitarBotones();
            }

        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
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

            shiftCounter = 1;
            arrayGame = new int[3][3];
            band = 0;
            botonContador=1;
            cdt.cancel();

            //cdt.cancel();
            txvCronometro.setText("5");
            txvCronometro.setTextColor(R.string.number_colors);

            isRoting = false;
            //===================================================== P
            mpBackgroun.stop();

            Random ran = new Random();
            nameMpBackground = "background" + (int) (Math.random() * 3);
            Toast.makeText(getApplicationContext(),nameMpBackground,Toast.LENGTH_SHORT).show();
            resMpBackground = getResources().getIdentifier(nameMpBackground, "raw", getPackageName());
            mpBackgroun = MediaPlayer.create(this, resMpBackground);
            mpBackgroun.setVolume(mpVolMax,(mpVolMax));
            mpBackgroun.setLooping(true);
            mpBackgroun.start();

            do{
                ran = new Random();
                nombreFruta1 = "im" + (int) (Math.random() * 9);
                nombreFruta2 = "im" + (int) (Math.random() * 9);
            }while(nombreFruta1.equals(nombreFruta2));

            resIdFruta1 = getResources().getIdentifier(nombreFruta1, "mipmap", getPackageName());
            resIdFruta2 = getResources().getIdentifier(nombreFruta2, "mipmap", getPackageName());

            viewFruta1.setImageResource(resIdFruta1);
            viewFruta2.setImageResource(resIdFruta2);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT);
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
                                txvCronometro.setTextColor(R.string.number_colors);
                                txvCronometro.setText("5");
                                cdt.cancel();
                            }
                        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void toastColor(int color){

        SuperActivityToast toastPoint;

        if(color == 4){
            toastPoint = new SuperActivityToast(this, new Style(), Style.TYPE_PROGRESS_BAR);
        }else{
             toastPoint = new SuperActivityToast(this, new Style(), Style.TYPE_STANDARD);
        }

        toastPoint
            .setDuration(Style.DURATION_SHORT)
            .setFrame(Style.FRAME_KITKAT)
            .setAnimations(Style.ANIMATIONS_FLY);

        switch(color){
            case 1:
                toastPoint.setText("Punto Jugador " + color)
                   .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED)).show();
                break;
            case 2:
                toastPoint
                   .setText("Punto Jugador " + color)
                   .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_ORANGE)).show();
                break;
            case 3:
                toastPoint
                   .setText("Empate")
                   .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE)).show();
                cdt.cancel();
                break;
            case 4:
                toastPoint
                   .setText("Pierdes Turno")
                   .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIME)).show();
                if (band != 1 && numberSpaces > 0) {
                    playIA();
                }
                break;
        }

        isRunning = false;
        cdt.cancel();
    }

    public void cronometro(){
        txvCronometro.setTextColor(R.string.number_colors);

        if(!isRunning) {
            cdt = new CountDownTimer(6000, 1000) {

                public void onTick(long millisUntilFinished) {

                    txvCronometro.setText("" + millisUntilFinished / 1100);
                    min = millisUntilFinished / 1000;

                    if(min==3){
                        txvCronometro.setTextColor(Color.RED);
                    }
                    /*if(min <= 3){
                        girarImReloj((int)min);
                    }*/
                }

                public void onFinish() {
                    shiftCounter++;
                    toastColor(4);
                }
            }.start();
            isRunning = true;
        }else if(min!=5){
            cdt.cancel();
            cdt.start();
        }
    }

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
        isRoting = true;
        girarImReloj(0);
        vibraWinner = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibraWinner.vibrate(500);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(mpBackgroun.isPlaying()){
            mpBackgroun.stop();
            cdt.cancel();
            mpBackgroun.release();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mpBackgroun.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mpBackgroun.pause();
        cdt.cancel();
    }

    void playIA(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (numberSpaces > 0) {
                                SuperIA oSIA = new SuperIA();
                                nombreBoton = oSIA.buscarJugada(arrayGame, shiftCounter);
                                //wait(200);
                                llenarMatriz(nombreBoton, shiftCounter);
                                resID1 = getResources().getIdentifier(nombreBoton, "id", getPackageName());
                                mpClic.start();
                                validarReglas();

                                botonClic = (ImageButton) findViewById(resID1);
                                changeIcon(botonClic, shiftCounter);

                                checkSpacesGame();
                                if(numberSpaces == 0 && band != 1){
                                    toastColor(3);
                                }

                                //Toast.makeText(getApplicationContext(), "Pos a Jugar: " + nombreBoton + "\nContador " + shiftCounter, Toast.LENGTH_SHORT).show();
                                shiftCounter++;
                                if (band != 1)
                                    enabledButtonsAviables();
                            }
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();
    }

    public void checkSpacesGame(){

        numberSpaces = 0;

        for(int i = 0; i < arrayGame.length; i++){
            if(numberSpaces <= 0){
                for (int j = 0; j < arrayGame.length; j++) {
                    if(arrayGame[i][j] != 1 && arrayGame[i][j] != 2){
                        numberSpaces++;
                    }
                }
            }
        }
    }

    public void disableButtons(){
        boton00.setEnabled(false);
        boton01.setEnabled(false);
        boton02.setEnabled(false);
        boton10.setEnabled(false);
        boton11.setEnabled(false);
        boton12.setEnabled(false);
        boton20.setEnabled(false);
        boton21.setEnabled(false);
        boton22.setEnabled(false);
    }

    public void enabledButtonsAviables(){
        int nameButton;
        ImageButton buttonAux1;
        String buttonId;

        for(int i = 0; i < arrayGame.length; i++){
            for(int j = 0; j < arrayGame.length; j++){
                if (0 == arrayGame[i][j]) {
                    buttonId = "button"+i+j;
                    nameButton = getResources().getIdentifier(buttonId, "id", getPackageName());
                    buttonAux1 = findViewById(nameButton);
                    buttonAux1.setEnabled(true);
                }
            }


        }
    }
}