package com.intecap.Apipersona.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato) {
        HashMap<String, String> mapa = new HashMap<String,String>();
        mapa.put("tipo", tipo); // tipo: tipo de respuesta.
        mapa.put("codigo", codigo);
        mapa.put("dato", dato);
        metadata.add(mapa);
    }


}
