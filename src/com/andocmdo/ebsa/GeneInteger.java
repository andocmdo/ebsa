package com.andocmdo.ebsa;

public class GeneInteger implements Gene {

    private final GeneType type;
    private Integer value;
    private final Integer max, min;

    {
        type = GeneType.INTEGER;
        value = 0;
        max = 0;
        min = 0;
    }

    @Override
    public Object getGeneValue() {
        return value;
    }

    @Override
    public GeneType getGeneType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GeneInteger)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        GeneInteger gene = (GeneInteger) obj;
        return this.getGeneValue().equals(gene.getGeneValue());
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
