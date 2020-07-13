package com.admin.cdc.movil.Models;

public class Forestales {

    //FORESTALES SIMPLE
    String Numero_Campo;
    String Nombre_Regional;
    String Especie;
    String Familia;
    String CAP_1; String CAP_2; String CAP_3; String CAP_4; String CAP_5;
    String Altura_Total;
    String Altura_Comercial;
    String Imagen;


    //FORMULARIO DETALLADO
    String Coor_X; String Coor_Y;
    String CAP;
    String DAP;
    String Area_Basa;
    String Volumen_To;
    String Volumen_Co;
    String PS;
    String RN;
    String Clase_Diam;

    public Forestales(){

    }

    public Forestales(String numero_Campo, String nombre_Regional, String especie, String familia,
                      String CAP_1, String CAP_2, String CAP_3, String CAP_4, String CAP_5,
                      String altura_Total, String altura_Comercial, String imagen, String coor_X,
                      String coor_Y, String CAP, String DAP, String area_Basa, String volumen_To,
                      String volumen_Co, String PS, String RN, String clase_Diam) {

        Numero_Campo = numero_Campo;
        Nombre_Regional = nombre_Regional;
        Especie = especie;
        Familia = familia;
        this.CAP_1 = CAP_1;
        this.CAP_2 = CAP_2;
        this.CAP_3 = CAP_3;
        this.CAP_4 = CAP_4;
        this.CAP_5 = CAP_5;
        Altura_Total = altura_Total;
        Altura_Comercial = altura_Comercial;
        Imagen = imagen;
        Coor_X = coor_X;
        Coor_Y = coor_Y;
        this.CAP = CAP;
        this.DAP = DAP;
        Area_Basa = area_Basa;
        Volumen_To = volumen_To;
        Volumen_Co = volumen_Co;
        this.PS = PS;
        this.RN = RN;
        Clase_Diam = clase_Diam;
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

    public String getCAP_1() {
        return CAP_1;
    }

    public void setCAP_1(String CAP_1) {
        this.CAP_1 = CAP_1;
    }

    public String getCAP_2() {
        return CAP_2;
    }

    public void setCAP_2(String CAP_2) {
        this.CAP_2 = CAP_2;
    }

    public String getCAP_3() {
        return CAP_3;
    }

    public void setCAP_3(String CAP_3) {
        this.CAP_3 = CAP_3;
    }

    public String getCAP_4() {
        return CAP_4;
    }

    public void setCAP_4(String CAP_4) {
        this.CAP_4 = CAP_4;
    }

    public String getCAP_5() {
        return CAP_5;
    }

    public void setCAP_5(String CAP_5) {
        this.CAP_5 = CAP_5;
    }

    public String getAltura_Total() {
        return Altura_Total;
    }

    public void setAltura_Total(String altura_Total) {
        Altura_Total = altura_Total;
    }

    public String getAltura_Comercial() {
        return Altura_Comercial;
    }

    public void setAltura_Comercial(String altura_Comercial) {
        Altura_Comercial = altura_Comercial;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getCoor_X() {
        return Coor_X;
    }

    public void setCoor_X(String coor_X) {
        Coor_X = coor_X;
    }

    public String getCoor_Y() {
        return Coor_Y;
    }

    public void setCoor_Y(String coor_Y) {
        Coor_Y = coor_Y;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getDAP() {
        return DAP;
    }

    public void setDAP(String DAP) {
        this.DAP = DAP;
    }

    public String getArea_Basa() {
        return Area_Basa;
    }

    public void setArea_Basa(String area_Basa) {
        Area_Basa = area_Basa;
    }

    public String getVolumen_To() {
        return Volumen_To;
    }

    public void setVolumen_To(String volumen_To) {
        Volumen_To = volumen_To;
    }

    public String getVolumen_Co() {
        return Volumen_Co;
    }

    public void setVolumen_Co(String volumen_Co) {
        Volumen_Co = volumen_Co;
    }

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
}
