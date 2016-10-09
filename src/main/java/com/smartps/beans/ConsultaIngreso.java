package com.smartps.beans;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.smartps.dao.AlumnoDAO;
import com.smartps.dao.PSDao;
import com.smartps.model.Alumno;
import com.smartps.model.PS;


@ManagedBean
@RequestScoped
public class ConsultaIngreso {

	private AlumnoDAO aldao = new AlumnoDAO();
	private PSDao psdao = PSDao.getInstance();

	private List<Alumno> alumnlist;
	private List<PS> pslist;
	private List<PS> pss;
	
	private int ingreso;
	
	
	private boolean panelPS;
	private boolean panelNo;
	
	
	@PostConstruct
	public void init(){
		alumnlist = new ArrayList<Alumno>();
		pslist = new ArrayList<PS>();
		pss = new ArrayList<PS>();
	}
	
	
	public void consultaByParametro(){
		
		alumnlist = aldao.findByIngreso(ingreso);
		
		if (alumnlist.isEmpty()){
			panelPS = false;
			panelNo = true;
		}else{

			panelPS = true;
			panelNo = false;
			
			for (int x=0; x<alumnlist.size(); x++){
				pslist = psdao.buscarPorLegajo(alumnlist.get(x).getLegajo());
				pss.addAll(pslist);
			}
			
		}

		
	}


	public List<Alumno> getAlumnlist() {
		return alumnlist;
	}

	public void setAlumnlist(List<Alumno> alumnlist) {
		this.alumnlist = alumnlist;
	}
	
	public List<PS> getPslist() {
		return pslist;
	}

	public void setPslist(List<PS> pslist) {
		this.pslist = pslist;
	}

	public List<PS> getPss() {
		return pss;
	}

	public void setPss(List<PS> pss) {
		this.pss = pss;
	}

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}

	public boolean isPanelPS() {
		return panelPS;
	}

	public void setPanelPS(boolean panelPS) {
		this.panelPS = panelPS;
	}

	public boolean isPanelNo() {
		return panelNo;
	}

	public void setPanelNo(boolean panelNo) {
		this.panelNo = panelNo;
	}

}
