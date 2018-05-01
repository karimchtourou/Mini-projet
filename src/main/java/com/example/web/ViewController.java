package com.example.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.example.dao.ActiviteRepository;
import com.example.dao.DemandeRepository;
import com.example.dao.EnfantRepository;
import com.example.dao.PrioriteRepository;
import com.example.dao.SejourRepository;
import com.example.dao.UtilisateurRepository;
import com.example.entities.Activite;
import com.example.entities.Adresse;
import com.example.entities.Demande;
import com.example.entities.Enfant;
import com.example.entities.Priorite;
import com.example.entities.Salarier;
import com.example.entities.Secretaire;
import com.example.entities.Sejour;
import com.example.entities.Utilisateur;
import com.example.metier.ActiviteMetier;
import com.example.metier.AdresseMetier;
import com.example.metier.EnfantMetier;
import com.example.metier.SejourMetier;
import com.lowagie.text.Document;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ViewController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ActiviteMetier activiteMetier;

	@Autowired
	private SejourMetier sejourMetier;
	@Autowired
	private AdresseMetier adrMetier;
	@Autowired
	private EnfantMetier enfMetier;
	@Autowired
	private ActiviteRepository actRep;
	@Autowired
	private SejourRepository sejRep;
	@Autowired
	private UtilisateurRepository utilRep;
	@Autowired
	private EnfantRepository enfRep;
	@Autowired
	private DemandeRepository demRep;
	@Autowired
	private PrioriteRepository priRep;

	public Model printUser(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur util = utilRep.findOne(user.getUsername());
		if (util != null)
			model.addAttribute("utilisateur", util);

		String s = utilRep.getUserRole(util.getLogin());
		model.addAttribute("role", s);

		return model;

	}

	@RequestMapping("/index")
	public String index(Model model) {
		model = printUser(model);

		return "index";
	}

	@RequestMapping("/demande")
	public String AjoutDemande(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur util = utilRep.findOne(user.getUsername());
		List<Enfant> enfants = new ArrayList<>();
		enfants = enfRep.listEnfant(new Date(), util.getLogin());
		List<Sejour> sejours = new ArrayList<>();
		sejours = sejRep.listSejour(new Date());

		model = printUser(model);
		model.addAttribute("enfants", enfants);
		model.addAttribute("sejours", sejours);
		return "AjoutDemande";
	}

	@RequestMapping("/sejour")
	public String AjoutSejour(Model model) {
		List<Activite> activites = new ArrayList<>();
		activites = actRep.findAll();
		model = printUser(model);
		model.addAttribute("activites", activites);
		return "AjoutSejour";
	}

	@RequestMapping("/enfant")
	public String AjoutEnfant(Model model) {
		model = printUser(model);
		return "AjoutEnfant";
	}

	@RequestMapping("/activite")
	public String AjoutActivite(Model model) {
		List<Activite> activites = new ArrayList<>();
		activites = actRep.findAll();
		model = printUser(model);
		model.addAttribute("activites", activites);

		return "AjoutActivite";
	}

	@RequestMapping("/profile")
	public String profile(Model model) {
		model = printUser(model);
		return "Profile";
	}

	@RequestMapping("/salarier")
	public String AjoutSalarie(Model model) {
		model = printUser(model);
		return "AjoutSalarie";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public String inscription(Model model, String login, String mdp, String nom, String prenom, String tel,
			String email, String rue, String ville, int cod, String matricule, String role) {
		try {

			Adresse adr = new Adresse(rue, ville, cod);
			adrMetier.ajoutAdr(adr);
			if (role.equals("SALARIER")) {
				Utilisateur u = new Salarier(login, mdp, nom, prenom, tel, email, adr, matricule);
				utilRep.save(u);
			} else {
				Utilisateur u = new Secretaire(login, mdp, nom, prenom, tel, email, adr, matricule);
				utilRep.save(u);
			}

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/salarier?error=" + e.getMessage();

		}
		return "redirect:/salarier?message";

	}

	@RequestMapping(value = "/ajoutSejour", method = RequestMethod.POST)
	public String ajoutSejour(Model model, String datedb, String datefin, String desc, String lieu, double prix,
			int nbPlace, String[] activites) {
		try {
			Collection<Activite> m = new ArrayList<Activite>();

			for (String s : activites) {
				Long id = Long.valueOf(s);
				Activite act = actRep.findOne(id);
				m.add(act);
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datedeb = format.parse(datedb);
			Date datefn = format.parse(datefin);
			Sejour sej = new Sejour(datedeb, datefn, desc, lieu, prix, 0, nbPlace, m);
			sejRep.save(sej);

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/salarier?error=" + e.getMessage();

		}
		return "redirect:/sejour?message";

	}

	@RequestMapping(value = "/ajoutEnfant", method = RequestMethod.POST)
	public String ajoutEnfant(Model model, String nom, String prenom, String birth, String sexe, String username) {
		try {
			model = printUser(model);

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(birth);
			Utilisateur util = utilRep.findOne(username);
			Enfant enf = new Enfant(nom, prenom, date, sexe, util);
			enfMetier.ajoutEnfant(enf);
			model.addAttribute("compte", enf);
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/enfant?error=" + e.getMessage();

		}
		return "redirect:/enfant?message";

	}

	@RequestMapping(value = "/ajoutActivite", method = RequestMethod.POST)
	public String ajoutActivte(Model model, String desc, String libelle) {
		try {
			Activite act = new Activite(desc, libelle);
			actRep.save(act);
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/activite&error=" + e.getMessage();

		}
		return "redirect:/activite?message";

	}

	@RequestMapping(value = "/ajoutDemande", method = RequestMethod.POST)
	public String ajoutDemande(Model model, String date, String ville, String enfant, String choix1, String choix2,
			String choix3) {
		try {

			Enfant enf = enfRep.findOne(Long.valueOf(enfant));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datedep = format.parse(date);

			Sejour sej1 = sejRep.findOne(Long.valueOf(choix1));
			Sejour sej2 = sejRep.findOne(Long.valueOf(choix2));
			Sejour sej3 = sejRep.findOne(Long.valueOf(choix3));
			Demande dem = new Demande(datedep, ville, "en attente", enf);

			if (sej1.getNbPlacesMax() > sej1.getNbplaces()) {
				dem.setEtat("accepté");
				demRep.save(dem);
				sej1.setNbplaces(sej1.getNbplaces() + 1);
				sejRep.save(sej1);
				Priorite p1 = new Priorite(1, dem, sej1);

				priRep.save(p1);
			} else if (sej2.getNbPlacesMax() > sej2.getNbplaces()) {
				dem.setEtat("accepté");

				demRep.save(dem);
				sej2.setNbplaces(sej2.getNbplaces() + 1);
				sejRep.save(sej2);
				Priorite p2 = new Priorite(2, dem, sej2);
				priRep.save(p2);
			}

			else if (sej3.getNbPlacesMax() > sej3.getNbplaces()) {
				dem.setEtat("accepté");

				demRep.save(dem);
				sej3.setNbplaces(sej3.getNbplaces() + 1);
				sejRep.save(sej3);
				Priorite p3 = new Priorite(3, dem, sej3);
				priRep.save(p3);
			} else {
				model.addAttribute("exeception", " il y a pas de places dans les sejours selectionné");
			}

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/demande?error=" + e.getMessage();

		}
		return "redirect:/demande?message";

	}

	@RequestMapping("/modifierProfil")
	public String modifierProfil(Model model, String nom, String prenom, String tel, String email, String login,
			String mdp) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur u = utilRep.findOne(user.getUsername());

		u.setNom(nom);
		u.setPrenom(prenom);
		u.setTel(tel);
		u.setEmail(email);
		u.setMot_pass(mdp);
		utilRep.save(u);
		model.addAttribute("utilisateur", u);

		return ("Profile");

	}

	@RequestMapping("/supActivite")
	public String supprimerActivite(Model model, String id) {
		Long idd = Long.valueOf(id);
		Activite act = actRep.findOne(idd);
		actRep.delete(act);
		return "redirect:/activite";
	}

	// jasper report generation method
	@RequestMapping("/report")
	public File report() throws JRException, IOException {
	/*	JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/report.jrxml");
		view.setApplicationContext(applicationContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", activiteMetier.report());
		return new ModelAndView(view, params);
		Map<String, Object> dataSource = new HashMap<String, Object>();
		dataSource.put("datasource", activiteMetier.report());
		
		List<Object> list = new ArrayList<Object>(dataSource.values());		
		*/
		

		
	
	List<Activite> list =actRep.findAll();
		// C'est la data source qui va être passé en paramétre
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		//  xmlFileName c'est le nom du fichier jrxml
					InputStream is = classloader.getResourceAsStream("classpath:/reports/report.jrxml");
					JasperReport jasperReport = JasperCompileManager.compileReport(is);
					// pour l'objet map vous pouvez passe null pour le moment 
					//Map<String, Object> map = new HashMap<String, Object>();
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);
					File pdf = File.createTempFile("output.", ".pdf");
					JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));

		// retour de la méthode  pdf  (ce qui est de type File)
		    return pdf;
	}

}
