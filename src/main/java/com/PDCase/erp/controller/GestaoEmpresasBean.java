package com.PDCase.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.PDCase.erp.model.Empresa;
import com.PDCase.erp.model.RamoAtividade;
import com.PDCase.erp.model.TipoEmpresa;
import com.PDCase.erp.repository.Empresas;
import com.PDCase.erp.repository.RamoAtividades;
import com.PDCase.erp.service.CadastroEmpresaService;
import com.PDCase.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	@Inject
	private FacesMessages messages;

	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private CadastroEmpresaService cadastroEmpresaService;

	private List<Empresa> listaEmpresas;

	private String termoPesquisa;

	private Converter ramoAtividadeConverter;

	@Inject
	private Empresa empresa;

	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}

	public void salvar() {
		cadastroEmpresaService.salvar(empresa);

		atualizarRegistro();

		messages.info("Empresa salva com sucesso!");

		RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));
	}

	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);

		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}
	
	public void prepararEdicao() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade())); 
	}
	
	public void excluir() {
		cadastroEmpresaService.excluir(empresa);
		
		empresa = null;
		
		atualizarRegistro();
		
		messages.info("Empresa excluida com sucesso!");
	}

	public boolean isEmpresaSelecionada() {
		return empresa != null && empresa.getId() != null;
	}

	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}

	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);

		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

		return listaRamoAtividades;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	private void atualizarRegistro() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}