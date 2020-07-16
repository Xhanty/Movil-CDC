package com.admin.cdc.movil.Models;

public class Forestales {

    //FORESTALES SIMPLE
    String Numero_Campo;
    String Nombre_Regional;
    String Especie;
    String Familia;
    String Imagen;
    Long CAP_1, CAP_2, CAP_3, CAP_4, CAP_5;
    Long Altura_Total, Altura_Comercial;


    //FORMULARIO DETALLADO
    String PS;
    String RN;
    String Clase_Diam;
    Long Coor_X, Coor_Y;
    Long CAP, DAP;
    Long Area_Basa, Volumen_To, Volumen_Co;

    public Forestales(){

    }

    public String getNumero_Campo() {
        return Numero_Campo;
    }


    public void setNumero_Campo(String numero_Campo) {
        Numero_Campo = numero_Campo;
    }

    public String getNombre_Regional() {
        return Nombre_Regional;
    }

    public void setNombre_Regional(String nombre_Regional) {
        Nombre_Regional = nombre_Regional;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public Long getCAP_1() {
        return CAP_1;
    }

    public void setCAP_1(Long CAP_1) {
        this.CAP_1 = CAP_1;
    }

    public Long getCAP_2() {
        return CAP_2;
    }

    public void setCAP_2(Long CAP_2) {
        this.CAP_2 = CAP_2;
    }

    public Long getCAP_3() {
        return CAP_3;
    }

    public void setCAP_3(Long CAP_3) {
        this.CAP_3 = CAP_3;
    }

    public Long getCAP_4() {
        return CAP_4;
    }

    public void setCAP_4(Long CAP_4) {
        this.CAP_4 = CAP_4;
    }

    public Long getCAP_5() {
        return CAP_5;
    }

    public void setCAP_5(Long CAP_5) {
        this.CAP_5 = CAP_5;
    }

    /*
    public Long getAltura_Total() {
        return Altura_Total;
    }

    public void setAltura_Total(Long altura_Total) {
        Altura_Total = altura_Total;
    }

    public Long getAltura_Comercial() {
        return Altura_Comercial;
    }

    public void setAltura_Comercial(Long altura_Comercial) {
        Altura_Comercial = altura_Comercial;
    }

     */

    public String getPS() {
        return PS;
    }

    public void setPS(String PS) {
        this.PS = PS;
    }

    public String getRN() {
        return RN;
    }

    public void setRN(String RN) {
        this.RN = RN;
    }

    public String getClase_Diam() {
        return Clase_Diam;
    }

    public void setClase_Diam(String clase_Diam) {
        Clase_Diam = clase_Diam;
    }

    /*
    public Long getCoor_X() {
        return Coor_X;
    }

    public void setCoor_X(Long coor_X) {
        Coor_X = coor_X;
    }

    public Long getCoor_Y() {
        return Coor_Y;
    }

    public void setCoor_Y(Long coor_Y) {
        Coor_Y = coor_Y;
    }

    public Long getCAP() {
        return CAP;
    }

    public void setCAP(Long CAP) {
        this.CAP = CAP;
    }

    public Long getDAP() {
        return DAP;
    }

    public void setDAP(Long DAP) {
        this.DAP = DAP;
    }

    public Long getArea_Basa() {
        return Area_Basa;
    }

    public void setArea_Basa(Long area_Basa) {
        Area_Basa = area_Basa;
    }

    public Long getVolumen_To() {
        return Volumen_To;
    }

    public void setVolumen_To(Long volumen_To) {
        Volumen_To = volumen_To;
    }

    public Long getVolumen_Co() {
        return Volumen_Co;
    }

    public void setVolumen_Co(Long volumen_Co) {
        Volumen_Co = volumen_Co;
    }
     */
}
