package com.intecap.Apipersona.service;

import com.intecap.Apipersona.model.Persona;
import com.intecap.Apipersona.model.dao.IPersonaDao;
import com.intecap.Apipersona.response.PersonaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service

public class PersonaServiceImpl implements IPersonaService {

    private static final Logger log = Logger.getLogger(PersonaServiceImpl.class.getName());

    @Autowired
    private IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)

    public ResponseEntity<PersonaResponseRest> buscarPersonas() {
        log.info("Inicio metodo buscarPersonas()");
        PersonaResponseRest response = new PersonaResponseRest();

        try {
            List<Persona> listPersona = (List<Persona>) personaDao.findAll();
            response.getPersonaResponse().setPersonas(listPersona);
            response.setMetadata("Respuesta OK", "200", "Lista de personas");


        } catch (Exception e) {
            log.info("Error al consultar las personas " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Repuesta Incorrecta");
            return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PersonaResponseRest> buscarPersonaId(Long id) {
        PersonaResponseRest response = new PersonaResponseRest();
        List<Persona> list = new ArrayList<>();

        try {
            Optional<Persona> persona = personaDao.findById(id);

            if (persona.isPresent()) {
                list.add(persona.get());
                response.getPersonaResponse().setPersonas(list);
                response.setMetadata("Respuesta OK", "200", "Persona encontrada");
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Persona no encontrada");
                return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.info("Error al consultar la persona " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al consultar la persona");
            return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional

    public ResponseEntity<PersonaResponseRest> crear(Persona persona) {
        log.info("Inicio metodo crear()");

        PersonaResponseRest response = new PersonaResponseRest();
        List<Persona> list = new ArrayList<>();

        try {
            Persona personaGuardada = personaDao.save(persona);

            if (personaGuardada != null) {
                list.add(personaGuardada);
                response.getPersonaResponse().setPersonas(list);
                response.setMetadata("Respuesta OK", "200", "Persona creada");
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Error al crear la Persona");
                return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.info("Error al crear la Persona " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al crear la Persona");
            return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional

    public ResponseEntity<PersonaResponseRest> actualizar(Long id, Persona persona) {
        log.info("Inicio metodo actualizar()");
        PersonaResponseRest response = new PersonaResponseRest();
        List<Persona> list = new ArrayList<>();

        try {
            Optional<Persona> personaBuscada = personaDao.findById(id);

            if (personaBuscada.isPresent()) {
                personaBuscada.get().setNombre(persona.getNombre());
                personaBuscada.get().setCorreo(persona.getCorreo());
                personaBuscada.get().setGenero(persona.getGenero());
                personaBuscada.get().setTelefono(persona.getTelefono());
                personaBuscada.get().setEdad(persona.getEdad());

                Persona personaActualizada = personaDao.save(personaBuscada.get());

                if (personaActualizada != null) {
                    list.add(personaActualizada);
                    response.getPersonaResponse().setPersonas(list);
                    response.setMetadata("Respuesta OK", "200", "Persona actualizada");

                } else {
                    response.setMetadata("Respuesta nOK ", "-1", "Error al actualizar la Persona");
                    return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Persona no encontrada");
                return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error al actualizar la Persona " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al actualizar la Persona");
            return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PersonaResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar()");
        PersonaResponseRest response = new PersonaResponseRest();
        List<Persona> list = new ArrayList<>();

        try {
            Optional<Persona> personaBuscada = personaDao.findById(id);

            if (personaBuscada.isPresent()) {
                personaDao.delete(personaBuscada.get());
                list.add(personaBuscada.get());
                response.getPersonaResponse().setPersonas(list);
                response.setMetadata("Respuesta OK", "200", "Persona eliminada");
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Persona no encontrada");
                return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error al eliminar la persona " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al eliminar la Persona");
            return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);

    }
}
