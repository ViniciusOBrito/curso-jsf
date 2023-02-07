package com.PDCase.erp.repository;


import com.PDCase.erp.model.RamoAtividade;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import java.io.Serializable;
import java.util.List;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public RamoAtividades() {

	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}

	public List<RamoAtividade> pesquisar(String descricao) {
		
		String jpql = "from RamoAtividade where descricao like :descricao";

		TypedQuery<RamoAtividade> query = manager.createQuery(jpql, RamoAtividade.class);

		query.setParameter("nomeFantasia", descricao + "%");

		return query.getResultList();
	}
}