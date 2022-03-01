package ru.specialist.demo.entity;

public class NumberCharacteristic {

    private boolean isEven;
    private boolean isComposite;

    public NumberCharacteristic(boolean isEven, boolean isComposite) {
        this.isEven = isEven;
        this.isComposite = isComposite;
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }

    public boolean isComposite() {
        return isComposite;
    }

    public void setComposite(boolean composite) {
        isComposite = composite;
    }
}
