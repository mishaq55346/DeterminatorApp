package ru.mikhail.determinatorapp.determination;

import java.util.HashMap;
import java.util.Map;

public class LifeForm {
    Map<Taxon, String> descriptionMap;

    public LifeForm() {
        descriptionMap = new HashMap<>();
    }

    public void addDescription(Taxon key, String description){
        if (descriptionMap.containsKey(key)){
            descriptionMap.replace(key, description);
            return;
        }
        descriptionMap.put(key, description);
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