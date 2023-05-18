package com.example.proyectofinaldam.data.model;

public class TaskCategory {

    int type;

    public TaskCategory(Boolean isSelected){
        isSelected = true;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
