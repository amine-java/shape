package com.mbh.shape;

import com.vividsolutions.jts.geom.Geometry;

public class ElementGeo {
	
	private String type ;
	private String idBdcm;
	private String dateDebAp ;
	private String dateFinAp ;
	private String dateDeb ;
	private String dateFin ;
	private String surfaceExploitation ;
	private String surfaceTotale ;
	private String exploitant ;
	private String puissance ;
	private String statut ;
	private String region ;
	private String departement ;
	private Geometry geometry ;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdBdcm() {
		return idBdcm;
	}
	public void setIdBdcm(String idBdcm) {
		this.idBdcm = idBdcm;
	}
	public String getDateDebAp() {
		return dateDebAp;
	}
	public void setDateDebAp(String dateDebAp) {
		this.dateDebAp = dateDebAp;
	}
	public String getDateFinAp() {
		return dateFinAp;
	}
	public void setDateFinAp(String dateFinAp) {
		this.dateFinAp = dateFinAp;
	}
	public String getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public String getSurfaceExploitation() {
		return surfaceExploitation;
	}
	public void setSurfaceExploitation(String surfaceExploitation) {
		this.surfaceExploitation = surfaceExploitation;
	}
	public String getSurfaceTotale() {
		return surfaceTotale;
	}
	public void setSurfaceTotale(String surfaceTotale) {
		this.surfaceTotale = surfaceTotale;
	}
	public String getExploitant() {
		return exploitant;
	}
	public void setExploitant(String exploitant) {
		this.exploitant = exploitant;
	}
	public String getPuissance() {
		return puissance;
	}
	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	@Override
	public String toString() {
		return "ElementGeo [type=" + type + ", idBdcm=" + idBdcm + ", dateDebAp=" + dateDebAp + ", dateFinAp="
				+ dateFinAp + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", surfaceExploitation="
				+ surfaceExploitation + ", surfaceTotale=" + surfaceTotale + ", exploitant=" + exploitant
				+ ", puissance=" + puissance + ", statut=" + statut + ", region=" + region + ", departement="
				+ departement + "]";
	}
	
	
}
