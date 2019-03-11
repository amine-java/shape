package com.mbh.shape.reader;

import org.opengis.feature.simple.SimpleFeature;

import com.mbh.shape.ElementGeo;
import com.vividsolutions.jts.geom.Geometry;

public class ElementGeoRowMapper implements RowMapper<ElementGeo>{

	public ElementGeo map(SimpleFeature feature) {
		ElementGeo element = new ElementGeo();
		Geometry geometry = (Geometry) feature.getAttribute(0);
		System.out.println(geometry.getClass());
		element.setGeometry(geometry);
		element.setType((String) feature.getAttribute(0).toString());
		element.setIdBdcm((String) feature.getAttribute("id_bdcm"));
		element.setDateDebAp((String) feature.getAttribute("ap_date_deb"));
		element.setDateFinAp((String) feature.getAttribute("ap_date_fin"));
		element.setDateDeb((String) feature.getAttribute("date_deb"));
		element.setDateFin((String) feature.getAttribute("date_fin"));
		element.setSurfaceExploitation((String) feature.getAttribute("surf_expl"));
		element.setSurfaceTotale((String) feature.getAttribute("surf_total"));
		element.setExploitant((String) feature.getAttribute("exploitant"));
		element.setPuissance((String) feature.getAttribute("puissance"));
		element.setStatut((String) feature.getAttribute("statut"));
		element.setRegion((String) feature.getAttribute("region"));
		element.setDepartement((String) feature.getAttribute("dept"));
		
		return element;
	}



}
