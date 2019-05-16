package com.example.prueb;

import java.util.*;

public class SuperIA {


    int numpPosibilidades;
    int contUnos = 0;
    String posJujar;
    Vector<String> posiciones;

  //  SuperIA(int matriz[][]) {
  //      this.matriz = matriz;

   // }


    public String BuscarJugada(boolean[][] matriz) {
        posiciones = new Vector();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (true == matriz[i][j]) {
                    contUnos++;
                }
                else{
                    posiciones.add(String.valueOf(i)+String.valueOf(j));
                }
                if(posiciones.size() == 1){
                    posJujar = ""+posiciones.indexOf(Math.random());
                }
            }
        }
        return posJujar;
    }
  }

