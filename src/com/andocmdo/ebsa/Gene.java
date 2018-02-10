package com.andocmdo.ebsa;

interface Gene {

    Object getGeneValue();

    GeneType getGeneType();

}

enum GeneType {
    INTEGER, DOUBLE, CHARACTER, STRING, BOOLEAN
}
