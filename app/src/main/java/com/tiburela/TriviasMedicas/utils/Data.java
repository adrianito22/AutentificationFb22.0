package com.tiburela.TriviasMedicas.utils;

import com.google.firebase.database.Exclude;

public class Data {

    @Exclude
    private String id;//el id para eliminar y actualizar a futuro
    @Exclude
    private int posicion;//el valor solo existira localmente
    private String foto;
    private String nombresCompletos;
    private int puntaje;

    public Data() {
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public int getPosicion() {
        return posicion;
    }

    @Exclude
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Exclude
    public String getNombresMaxTwo(){
        if(nombresCompletos==null){
            return "";
        }
        String[] nombreSplit = nombresCompletos.split(" ");
        if(nombreSplit.length <= 1) {
            return nombresCompletos;
        }else{
            return nombreSplit[0] + nombreSplit[1];
        }
    }

}
