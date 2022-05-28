package ru.mikhail.determinatorapp.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class LifeForm implements Parcelable {
    Map<Taxon, String> descriptionMap;

    public LifeForm() {
        descriptionMap = new HashMap<>();
    }

    public LifeForm(Map<Taxon, String> descriptionMap) {
        this.descriptionMap = descriptionMap;
    }

    protected LifeForm(Parcel in) {
        in.readMap(descriptionMap, HashMap.class.getClassLoader());
    }

    public Map<Taxon, String> getDescriptionMap() {
        return descriptionMap;
    }

    public void setDescriptionMap(Map<Taxon, String> descriptionMap) {
        this.descriptionMap = descriptionMap;
    }

    public static final Creator<LifeForm> CREATOR = new Creator<LifeForm>() {
        @Override
        public LifeForm createFromParcel(Parcel in) {
            return new LifeForm(in);
        }

        @Override
        public LifeForm[] newArray(int size) {
            return new LifeForm[size];
        }
    };

    public void addDescription(Taxon key, String description){
        if (descriptionMap.containsKey(key)){
            descriptionMap.replace(key, description);
            return;
        }
        descriptionMap.put(key, description);
    }

    public void addDescription(Map<Taxon,String> descriptionMap){
        this.descriptionMap.putAll(descriptionMap);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(descriptionMap);
    }

    public enum Taxon{
        CLASS, PODCLASS, OTRYAD, SEMEYSTVO, ROD, VID;
        public static Taxon fromString(String taxonStr){
            switch (taxonStr){
                case "define_class": return CLASS;
                case "define_podclass": return PODCLASS;
                case "define_otr": return OTRYAD;
                case "define_semeystvo": return SEMEYSTVO;
                case "define_rod": return ROD;
                case "define_vid": return VID;
                default: return null;
            }
        }

    }
}