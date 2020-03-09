package com.example.prueb;

import android.widget.Toast;
import java.util.*;

public class SuperIA {


    int numpPosibilidades;
    String posJujar;
    Vector<String> posiciones;

  //  SuperIA(int matriz[][]) {
  //      this.matriz = matriz;

   // }


    public String buscarJugada(int[][] matriz, int numeroTurno) {

            posiciones = new Vector();
            numpPosibilidades=0;
            int i = 0, j = 0;
            int cPlayer = 0;

            for (i = 0; i < (matriz.length); i++) {
                for (j = 0; j < matriz.length; j++) {
                    if (0 == matriz[i][j]) {
                        posiciones.addElement(""+i+j);
                        numpPosibilidades++;
                    }else if(1 == matriz[i][j]){
                        cPlayer++;
                    }
                }

                if(numpPosibilidades == 1 && cPlayer >= 2){
                    posJujar = posiciones.lastElement();
                    break;
                }else{
                    numpPosibilidades = 0;
                    cPlayer = 0;
                    if(!posiciones.isEmpty())
                        posJujar = posiciones.elementAt((int) (Math.random() * posiciones.size()));
                }
            }



        return "button" + posJujar;
    }

    public void findHorizontal(){

    }
  }

