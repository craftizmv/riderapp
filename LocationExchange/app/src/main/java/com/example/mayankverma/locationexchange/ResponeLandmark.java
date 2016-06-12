package com.example.mayankverma.locationexchange;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mayankverma on 12/06/16.
 */
public class ResponeLandmark implements Serializable {
    public String latitude = "";
    public ArrayList<String> landmarks = new ArrayList<>();
    public String longitude = "";
    public String ref_id = "";
}
