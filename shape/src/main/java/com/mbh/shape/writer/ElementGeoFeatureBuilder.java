package com.mbh.shape.writer;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.mbh.shape.ElementGeo;

/**
 * Implementation du FeatureBuilder pour l'objet métier ElementGeo
 * 
 * @author boufatah
 *
 */
public class ElementGeoFeatureBuilder implements FeatureBuilder<ElementGeo , SimpleFeatureType> {

	public SimpleFeature build(ElementGeo theObject, SimpleFeatureType theFeatureType) {
		SimpleFeatureBuilder simpleFeatureTypeBuilder = new SimpleFeatureBuilder(theFeatureType);
		
		simpleFeatureTypeBuilder.set("name", "test");
		simpleFeatureTypeBuilder.add(theObject.getGeometry());
		
		return simpleFeatureTypeBuilder.buildFeature(theObject.getIdBdcm() != null ? theObject.getIdBdcm().toString() : "test");
	}


}
