package com.example.examen_diseno_interfaces_primer_trimestre;

import com.example.examen_diseno_interfaces_primer_trimestre.models.Coche;

import java.util.ArrayList;

public class Sesion {
    private static Coche cocheActual = null;
    private static ArrayList<Coche> coches = new ArrayList<>(0);
    private static Integer pos = null;

    public static Coche getCocheActual() {
        return cocheActual;
    }

    public static void setCocheActual(Coche cocheActual) {
        Sesion.cocheActual = cocheActual;
    }

    public static ArrayList<Coche> getCoches() {
        return coches;
    }

    public static void setCoches(ArrayList<Coche> coches) {
        Sesion.coches = coches;
    }

    public static Integer getPos() {
        return pos;
    }

    public static void setPos(Integer pos) {
        Sesion.pos = pos;
    }
}
