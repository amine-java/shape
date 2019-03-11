package com.mbh.shape.writer;

import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.MultiPolygon;

/**
 * Class ElementFeatureTypeBuilder: Chargement du schéma de données.
 * 
 * @author boufatah
 *
 */
public class ElementFeatureTypeBuilder extends SimpleFeatureTypeBuilder {

	/**
	 * Méthode de surcharge, et permettant de charger le schéma de données.  
	 */
	public SimpleFeatureType buildFeatureType() {
		setName("TheName");
		add("geom", MultiPolygon.class, DefaultGeographicCRS.WGS84);
		add("name", String.class);
		return super.buildFeatureType();
	}

}
