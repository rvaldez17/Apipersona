package com.intecap.Apipersona.service;

import com.intecap.Apipersona.model.Persona;
import com.intecap.Apipersona.response.PersonaResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPersonaService { //Interface para acceder a los datos de la tabla categoria de la base de datos

    public ResponseEntity<PersonaResponseRest>  buscarPersonas(); //metodo para buscar todas las categorias de la base de datos
    public ResponseEntity<PersonaResponseRest> buscarPersonaId(Long id); //metodo para buscar una categoria por su id

    public ResponseEntity<PersonaResponseRest> crear(Persona persona); //metodo para crear una categoria

    public ResponseEntity<PersonaResponseRest> actualizar(Long id, Persona persona); //metodo para actualizar una categoria

    public ResponseEntity<PersonaResponseRest> eliminar(Long id); //metodo para eliminar una categoria
}
