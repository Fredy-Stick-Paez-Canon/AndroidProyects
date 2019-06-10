package com.example.prueb;

import android.widget.Toast;
import java.util.*;

public class SuperIA {


    int numpPosibilidades;
    int conTtrue = 0;
    String posJujar;
    Vector<String> posiciones;
    Random ran;

  //  SuperIA(int matriz[][]) {
  //      this.matriz = matriz;

   // }


    public String buscarJugada(boolean[][] matriz, int numeroTurno) {
            ran = new Random();
            posiciones = new Vector();
            numpPosibilidades=0;
            for (int i = 0; i < (matriz.length); i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (true == matriz[i][j]) {
                        conTtrue++;
                    } else {
                        posiciones.addElement(""+i+j);
                        numpPosibilidades++;
                    }
                }
            }

        if (posiciones.size() > 0) {
        }
        else{
            posJujar = "se jodio";
        }

        return "button" + posJujar;
    }
  }

