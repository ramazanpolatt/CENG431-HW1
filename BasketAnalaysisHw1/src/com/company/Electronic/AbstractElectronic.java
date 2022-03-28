package com.company.Electronic;

import com.company.Application.IProduct;

public  abstract class AbstractElectronic implements IProduct {

    private String id;
    private String title;


    public AbstractElectronic(String id,String title) {
        this.id = id;
        this.title=title;
    }

    @Override
    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return
                id + ' ' +
                " " + title + ' ';
    }
}
