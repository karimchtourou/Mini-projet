package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dao.AdresseRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.Administrateur;
import com.example.entities.Adresse;
import com.example.entities.Salarier;
import com.example.entities.Utilisateur;

@SpringBootApplication
public class ProjetJpaApplication implements CommandLineRunner {
	@Autowired
	private UtilisateurRepository utilisateurRep;
	@Autowired
	private AdresseRepository adresseRep;

	public static void main(String[] args) {
		SpringApplication.run(ProjetJpaApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
	/*	Adresse adr1 = adresseRep.save(new Adresse("mahdia", "sfax", 3011));
		Adresse adr2 = adresseRep.save(new Adresse("tunis", "sfax", 3022));
		Utilisateur u1 = utilisateurRep.save(
				new Administrateur("karim", "123", "chtourou", "karim", "52098086", "karim.cht2@gmail.com", adr1));
		Utilisateur u2 = utilisateurRep.save(
				new Salarier("131457", "123", "krichen", "houcine", "27250080", "houcine@gmail.com", adr2, 131457));
*/
	}

}
