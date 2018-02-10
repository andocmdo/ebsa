package com.andocmdo.ebsa;

public interface Gene {

    Object getGene();

    GeneType getGeneType();

}

enum GeneType {
    INTEGER, DOUBLE, CHARACTER, STRING, BOOLEAN
}
