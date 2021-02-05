package com.example.gsbvisitevrai.controller;

import android.content.Context;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.MedicamentDAO;
import com.example.gsbvisitevrai.model.Praticien;
import com.example.gsbvisitevrai.model.PraticienDao;

import java.util.ArrayList;

public final class PraticienController {

    private static PraticienController praticienController = null;
    private static ArrayList<Praticien> praticiens;

    private PraticienController(){
        super();
    }

    /**
     * Accesseur
     * @return : une instance unique de MedicamentController (singleton)
     */
    public static final PraticienController getInstance(Context context){
        if(PraticienController.praticienController == null) {
            PraticienController.praticienController = new PraticienController();
            praticiens = new ArrayList<>();
            PraticienDao praticienDao = new PraticienDao(context);
            praticiens = praticienDao.read();

        }
        return PraticienController.praticienController;
    }

    /**
     * Trouver un médicament par son indice dans l'arraylist
     * @param index
     * @return le médicament
     */

    public Praticien getPraticien(int index) {
        return praticiens.get(index);
    }

    /**
     * Liste des médicaments
     * @return : l'arraylist de médicaments
     */

    public ArrayList<Praticien> praticiens () {
        return praticiens;
    }
}
