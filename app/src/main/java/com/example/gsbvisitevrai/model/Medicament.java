package com.example.gsbvisitevrai.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Medicament implements Parcelable {
    private String MDepotLegal;
    private String MNomCommercial;
    private String MComposition;
    private String MEffet;
    private String MContreIndication;
    private Double MPrixEchant;


    public String getMEffet() {
        return MEffet;
    }

    public void setMEffet(String MEffet) {
        this.MEffet = MEffet;
    }

    public String getMContreIndication() {
        return MContreIndication;
    }

    public void setMContreIndication(String MContreIndication) {
        this.MContreIndication = MContreIndication;
    }

    private Medicament(Parcel in) {
        MDepotLegal = in.readString();
        MNomCommercial = in.readString();
        MComposition = in.readString();
        MEffet = in.readString();
        MContreIndication = in.readString();
        MPrixEchant = in.readDouble();
    }

    public Medicament(String MDepotLegal, String MNomCommercial, String MComposition, String effet, String contreindic, Double MPrixEchant) {
        this.MDepotLegal = MDepotLegal;
        this.MNomCommercial = MNomCommercial;
        this.MComposition = MComposition;
        this.MEffet = effet;
        this.MContreIndication = contreindic;
        this.MPrixEchant = MPrixEchant;
    }

    public String getMDepotLegal() {
        return MDepotLegal;
    }

    public void setMDepotLegal(String MDepotLegal) {
        this.MDepotLegal = MDepotLegal;
    }

    public String getMNomCommercial() {
        return MNomCommercial;
    }

    public void setMNomCommercial(String MNomCommercial) {
        this.MNomCommercial = MNomCommercial;
    }

    public String getMComposition() {
        return MComposition;
    }

    public void setMComposition(String MComposition) {
        this.MComposition = MComposition;
    }

    public Double getMPrixEchant() {
        return MPrixEchant;
    }

    public void setMPrixEchant(Double MPrixEchant) {
        this.MPrixEchant = MPrixEchant;
    }

    public static final Parcelable.Creator<Medicament> CREATOR = new Parcelable.Creator<Medicament>() {
        @Override
        public Medicament createFromParcel(Parcel source) {
            return new Medicament(source);
        }

        @Override
        public Medicament[] newArray(int size) {
            return new Medicament[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getMDepotLegal());
        dest.writeString(getMNomCommercial());
        dest.writeString(getMComposition());
        dest.writeString(getMEffet());
        dest.writeString(getMContreIndication());
        dest.writeDouble(getMPrixEchant());
    }
}
