package com.example.prueb;

import android.widget.Toast;
import java.util.*;

public class SuperIA {


    int numpPosibilidades;
    int conCeros = 0;
    String posJujar;
    Vector<String> posiciones;
    Random ran;

  //  SuperIA(int matriz[][]) {
  //      this.matriz = matriz;

   // }


    public String buscarJugada(int[][] matriz, int numeroTurno) {

            posiciones = new Vector();
            numpPosibilidades=0;

            for (int i = 0; i < (matriz.length); i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (0 == matriz[i][j]) {
                        posiciones.addElement(""+i+j);
                        numpPosibilidades++;
                    }
                }
            }

            posJujar = posiciones.elementAt((int) (Math.random() * posiciones.size()));

        return "button" + posJujar;
    }
  }

