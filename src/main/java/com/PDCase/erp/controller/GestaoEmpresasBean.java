package com.PDCase.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.PDCase.erp.model.Empresa;
import com.PDCase.erp.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa =  new Empresa();
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public TipoEmpresa[] getTiposEmpresas() {
		return TipoEmpresa.values();
	}
	
	public void salvar() {
		System.out.println("Razão Social: " + empresa.getRazaoSocial() + 
				"- Nome Fantasia: " + empresa.getNomeFantasia() + 
				"- Tipo empresa: " + empresa.getTipo());
	}
}
