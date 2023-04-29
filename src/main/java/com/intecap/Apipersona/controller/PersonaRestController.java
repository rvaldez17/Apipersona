package com.intecap.Apipersona.controller;

import com.intecap.Apipersona.model.Persona;
import com.intecap.Apipersona.response.PersonaResponseRest;
import com.intecap.Apipersona.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000","http://localhost:4200"})

@RestController
@RequestMapping("api/v1")
public class PersonaRestController {

    @Autowired
    private IPersonaService personaService;

        @GetMapping("/personas")
    public ResponseEntity<PersonaResponseRest> consultarPersonas(){
        return personaService.buscarPersonas();
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<PersonaResponseRest> consultarPersonaId(@PathVariable Long id){
        return personaService.buscarPersonaId(id);
    }

    @PostMapping("/personas")
    public ResponseEntity<PersonaResponseRest> crear(@RequestBody Persona request){
        return personaService.crear(request);
    }
    @PutMapping("/personas/{id}")
    public ResponseEntity<PersonaResponseRest> actualizar(@PathVariable Long id, @RequestBody Persona request){
        return personaService.actualizar(id,request);
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<PersonaResponseRest> eliminar(@PathVariable Long id){

        return personaService.eliminar(id);
    }
}
