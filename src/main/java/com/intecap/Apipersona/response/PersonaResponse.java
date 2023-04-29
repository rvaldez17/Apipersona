package com.intecap.Apipersona.response;

import com.intecap.Apipersona.model.Persona;

import java.util.List;

public class PersonaResponse {

    private List<Persona> personas;

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
