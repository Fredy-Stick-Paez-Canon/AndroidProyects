package com.example.prueb;

import android.annotation.SuppressLint;
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

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView conPlayer1, conPlayer2,txvCronometro;

    private int shiftCounter = 1;
    public int band = 0;
    public int conWinFresa = 0;
    public int conWinNaranja = 0;
    public int botonContador=1;
    private int[][] arrayGame;
    public int numFreGame = 0;
    public int numNarGame = 0;
    public int resID1;
    public int resIdFruta1;
    public int resIdFruta2;
    public int numberSpaces = 0;
    public int resMpBackground;
    public int fruit1color;
    public int fruit2color;

    float mpVolMax = (float)0.3;

    String nombreBoton, nombreFruta1, nombreFruta2, nameMpBackground;

    boolean isRunning = false, isRoting = false;

    ImageView viewFruta1, viewFruta2, imagenReloj;

    ImageButton boton00, boton01, boton02, boton10, boton11, boton12, boton20, boton21, boton22, botonClic;
    ImageButton reini;

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

            setArrayGame(new int[3][3]);
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

            mpFlash = MediaPlayer.create(this,  R.raw.flash);
            mpFlash.setVolume(mpVolMax,(mpVolMax));

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
                fruit1color = (int)(Math.random() * 25);
                fruit2color = (int)(Math.random() * 25);

                nombreFruta1 = "im" + fruit1color;
                nombreFruta2 = "im" + fruit2color;
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
            isRoting=false;
            girarImReloj();
            mpClic.start();
            botonClic = (ImageButton) v;
            botonContador++;
            changeIcon(botonClic, getShiftCounter());

            int resID = v.getId();
            nombreBoton = getResources().getResourceEntryName(resID);
            llenarMatriz(nombreBoton, getShiftCounter());
            resID1 = getResources().getIdentifier(nombreBoton, "id", getPackageName());
            setShiftCounter(getShiftCounter() + 1);
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
            getArrayGame()[fila][columna] = 1;
        } else {
            getArrayGame()[fila][columna] = 2;
        }
    }

    protected void girarImReloj() {

        imagenReloj = findViewById(R.id.reloj);

        Animation animation;

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
            for (int[] ints : getArrayGame()) {
                for (int j = 0; j < getArrayGame().length; j++) {

                    if (ints[j] == 1) {
                        numFreGame++;
                    } else if (ints[j] == 2) {
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
            for (int j = 0; j < getArrayGame().length; j++) {
                for (int i = 0; i < getArrayGame().length; i++) {

                    if (getArrayGame()[i][j] == 1) {
                        numFreGame++;
                    } else if (getArrayGame()[i][j] == 2) {
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
            for (int i = 0; i < getArrayGame().length; i++) {
                if (getArrayGame()[i][i] == 1) {
                    numFreGame++;
                } else if (getArrayGame()[i][i] == 2) {
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

            int j = getArrayGame().length-1;

            for(int i = 0; i < getArrayGame().length; i++){
                if (getArrayGame()[i][j] == 1) {
                    numFreGame++;
                } else if (getArrayGame()[i][j] == 2) {
                    numNarGame++;
                }
                j--;
            }

            if (numFreGame >= 3 || numNarGame >= 3) {
                winValidate();
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
                ganaste(fruit1color, resID1);
                toastColor(1);
                band = 1;
                conWinFresa++;
                inhabilitarBotones();
            }
            else if(numNarGame == 3){
                ganaste(fruit2color, resID1);
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

    @SuppressLint("ShowToast")
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

            setShiftCounter(1);
            setArrayGame(new int[3][3]);
            band = 0;
            botonContador=1;
            cdt.cancel();

            //cdt.cancel();
            txvCronometro.setText("3");
            txvCronometro.setTextColor(Color.WHITE);

            isRoting = true;
            girarImReloj();
            //===================================================== P
            mpBackgroun.stop();

            nameMpBackground = "background" + (int) (Math.random() * 3);
            Toast.makeText(getApplicationContext(),nameMpBackground,Toast.LENGTH_SHORT).show();
            resMpBackground = getResources().getIdentifier(nameMpBackground, "raw", getPackageName());
            mpBackgroun = MediaPlayer.create(this, resMpBackground);
            mpBackgroun.setVolume(mpVolMax,(mpVolMax));
            mpBackgroun.setLooping(true);
            mpBackgroun.start();

            do{
                fruit1color = (int)(Math.random() * 25);
                fruit2color = (int)(Math.random() * 25);

                nombreFruta1 = "im" + fruit1color;
                nombreFruta2 = "im" + fruit2color;
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
                                txvCronometro.setTextColor(Color.WHITE);
                                txvCronometro.setText("3");
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
                mpFlash.start();

                if (band != 1 && numberSpaces > 0) {
                    playIA();
                }
                break;
        }

        isRunning = false;
        cdt.cancel();
    }

    public void cronometro(){
        txvCronometro.setTextColor(Color.WHITE);

        if(!isRunning) {
            cdt = new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {

                    min = millisUntilFinished / 1000;
                    txvCronometro.setText(""+min);


                    if(min==1){
                        txvCronometro.setTextColor(Color.RED);
                    }
                    /*if(min <= 3){
                        girarImReloj((int)min);
                    }*/
                }

                public void onFinish() {
                    setShiftCounter(getShiftCounter() + 1);
                    toastColor(4);
                }
            }.start();
            isRunning = true;
        }else if(min!=5){
            cdt.cancel();
            cdt.start();
        }
    }

    protected void ganaste(int fruitWinner, int resID1) {
        cdt.cancel();
        Vector<ParticleSystem> arrayParticleSystem = new Vector<>();
        ParticleSystem oParticleSystem = null;

        if(fruitWinner >= 0 && fruitWinner < 5){
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_yellow_star, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_yellow_ball, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_yellow_confe, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
        }else if(fruitWinner >= 5 && fruitWinner < 10){
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_red_star, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_red_ball, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_red_confe, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
        }else if(fruitWinner >= 9 && fruitWinner < 15){
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_green_star, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_green_ball, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_green_confe, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
        }else if(fruitWinner >= 14 && fruitWinner < 20){
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_orange_star, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_orange_ball, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_orange_confe, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
        }else{
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_darkgreen_star, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_darkgreen_ball, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
            oParticleSystem = new ParticleSystem(this, 80, R.drawable.ic_darkgreen_confe, 10000);
            arrayParticleSystem.addElement(oParticleSystem);
        }

       for(int i = 0; i < arrayParticleSystem.size(); i++){
           arrayParticleSystem.elementAt(i)
                   .setSpeedRange(0.2f, 0.5f)
                   .setRotationSpeed(144)
                   .oneShot(findViewById(resID1),80);
       }

        isRoting = true;
        girarImReloj();
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
                                nombreBoton = oSIA.buscarJugada(getArrayGame(), getShiftCounter());
                                //wait(200);
                                llenarMatriz(nombreBoton, getShiftCounter());
                                resID1 = getResources().getIdentifier(nombreBoton, "id", getPackageName());
                                mpClic.start();
                                validarReglas();

                                botonClic = findViewById(resID1);
                                changeIcon(botonClic, getShiftCounter());

                                checkSpacesGame();
                                if(numberSpaces == 0 && band != 1){
                                    toastColor(3);
                                }

                                //Toast.makeText(getApplicationContext(), "Pos a Jugar: " + nombreBoton + "\nContador " + shiftCounter, Toast.LENGTH_SHORT).show();
                                setShiftCounter(getShiftCounter() + 1);
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

        for(int i = 0; i < getArrayGame().length; i++){
            if(numberSpaces <= 0){
                for (int j = 0; j < getArrayGame().length; j++) {
                    if(getArrayGame()[i][j] != 1 && getArrayGame()[i][j] != 2){
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

        for(int i = 0; i < getArrayGame().length; i++){
            for(int j = 0; j < getArrayGame().length; j++){
                if (0 == getArrayGame()[i][j]) {
                    buttonId = "button"+i+j;
                    nameButton = getResources().getIdentifier(buttonId, "id", getPackageName());
                    buttonAux1 = findViewById(nameButton);
                    buttonAux1.setEnabled(true);
                }
            }


        }
    }

    public int[][] getArrayGame() {
        return arrayGame;
    }

    public void setArrayGame(int[][] arrayGame) {
        this.arrayGame = arrayGame;
    }

    public int getShiftCounter() {
        return shiftCounter;
    }

    public void setShiftCounter(int shiftCounter) {
        this.shiftCounter = shiftCounter;
    }
}