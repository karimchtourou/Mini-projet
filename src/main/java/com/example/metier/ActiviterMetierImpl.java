package com.example.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ActiviteRepository;
import com.example.entities.Activite;
@Service
@Transactional
public class ActiviterMetierImpl implements ActiviteMetier {
	
	@Autowired
	private ActiviteRepository actRep;

	@Override
	public List<Map<String, Object>> report() {
		List<Map<String, Object>> result= new ArrayList<Map<String, Object>>();
		for (Activite activite :actRep.findAll()){
		Map<String, Object> item= new HashMap<String, Object>();
		//item.put("code" , activite.getId_activite());
		item.put("nom", activite.getDescription());
		item.put("libelle", activite.getLibelle());
		result.add(item);
		}
		return result;
	}
	
	

}
