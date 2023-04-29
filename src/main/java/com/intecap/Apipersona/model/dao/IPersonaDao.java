package com.intecap.Apipersona.model.dao;

import com.intecap.Apipersona.model.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaDao extends CrudRepository<Persona, Long> {
}
