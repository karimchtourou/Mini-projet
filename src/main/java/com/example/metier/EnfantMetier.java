package com.example.metier;

import java.util.Date;
import java.util.List;

import com.example.entities.Enfant;
import com.example.entities.Utilisateur;

public interface EnfantMetier {
void ajoutEnfant(Enfant enf);

List<Enfant> findAll();
}
