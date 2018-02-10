package com.andocmdo.ebsa;

public interface Gene {

    Object getGeneValue();

    GeneType getGeneType();

}

enum GeneType {
    INTEGER, DOUBLE, CHARACTER, STRING, BOOLEAN
}
