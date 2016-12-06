package com.smartps.model;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

public class PS implements Serializable {
	private static final long serialVersionUID = -6065549130439224352L;
		private int id;
		private int cicloLectivo;
		private int cuatrimestre;
		private String titulo;
		private String nroDisposicion;
		private Area area;
		private Estado estado;
		private Organizacion organizacion;
		private TipoActividad tipoActividad;
		private Alumno alumno;
		private Set<InformeFinal> informeFinal = new HashSet<InformeFinal>(0);
		private Set<PlanDeTrabajo> planDeTrabajo = new HashSet<PlanDeTrabajo>(0);
		public PS() {
			
		}
		
		
		
		public PS(int id, int cicloLectivo, int cuatrimestre, String titulo, String nroDisposicion, Area area,
				Estado estado, Organizacion organizacion, TipoActividad tipoActividad, Alumno alumno) {
			super();
			this.id = id;
			this.cicloLectivo = cicloLectivo;
			this.cuatrimestre = cuatrimestre;
			this.titulo = titulo;
			this.nroDisposicion = nroDisposicion;
			this.area = area;
			this.estado = estado;
			this.organizacion = organizacion;
			this.tipoActividad = tipoActividad;
			this.alumno = alumno;
		}
		

		//4 5 9 10 11
		public boolean estaVigente(){
			if(estado.getId()==4 || estado.getId()==5 ||estado.getId()==9||estado.getId()==10 || estado.getId()==11){
				return false;
			}
			return true;
		}
		
		public boolean puedeAprobar(){
			return this.getEstado().getId()==8;
		}
		
		public int getCicloLectivo() {
			return cicloLectivo;
		}
		public void setCicloLectivo(int cicloLectivo) {
			this.cicloLectivo = cicloLectivo;
		}
		public int getCuatrimestre() {
			return cuatrimestre;
		}
		public void setCuatrimestre(int cuatrimestre) {
			this.cuatrimestre = cuatrimestre;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getNroDisposicion() {
			return nroDisposicion;
		}
		public void setNroDisposicion(String nroDisposicion) {
			this.nroDisposicion = nroDisposicion;
		}
		public Area getArea() {
			return area;
		}
		public void setArea(Area area) {
			this.area = area;
		}
		public Estado getEstado() {
			return estado;
		}
		public void setEstado(Estado estado) {
			this.estado = estado;
		}
		public Organizacion getOrganizacion() {
			return organizacion;
		}
		public void setOrganizacion(Organizacion organizacion) {
			this.organizacion = organizacion;
		}
		public TipoActividad getTipoActividad() {
			return tipoActividad;
		}
		public void setTipoActividad(TipoActividad tipoActividad) {
			this.tipoActividad = tipoActividad;
		}
		public Set<InformeFinal> getInformeFinal() {
			return informeFinal;
		}
		public void setInformeFinal(Set<InformeFinal> informeFinal) {
			this.informeFinal = informeFinal;
		}
		public Set<PlanDeTrabajo> getPlanDeTrabajo() {
			return planDeTrabajo;
		}
		public void setPlanDeTrabajo(Set<PlanDeTrabajo> planDeTrabajo) {
			this.planDeTrabajo = planDeTrabajo;
		}
		public Alumno getAlumno() {
			return alumno;
		}
		public void setAlumno(Alumno alumno) {
			this.alumno = alumno;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void addPlan(PlanDeTrabajo plan) {
			// TODO Auto-generated method stub
			this.planDeTrabajo.add(plan);
		}
		public void addInforme(InformeFinal informe) {
			// TODO Auto-generated method stub
			this.informeFinal.add(informe);
		}
}
