package ru.mikhail.determinatorapp.determination;

import java.util.Map;

import ru.mikhail.determinatorapp.common.LifeForm;

public class Node {
    int keyToGo;
    String description;
    Map<LifeForm.Taxon, String> parameters;

    public Node(int keyToGo, String description, Map<LifeForm.Taxon, String> parameters) {
        this.keyToGo = keyToGo;
        this.description = description;
        this.parameters = parameters;
    }

    public int getKeyToGo() {
        return keyToGo;
    }

    public void setKeyToGo(int keyToGo) {
        this.keyToGo = keyToGo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<LifeForm.Taxon, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<LifeForm.Taxon, String> parameters) {
        this.parameters = parameters;
    }
}
