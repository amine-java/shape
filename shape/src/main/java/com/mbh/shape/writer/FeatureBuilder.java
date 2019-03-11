package com.mbh.shape.writer;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Interface FeatureBuilder
 * 
 * @author boufatah
 *
 * @param <E>
 * @param <F>
 */
public interface FeatureBuilder<E , F extends SimpleFeatureType> {

	/**
	 * 
	 * Cette méthode transforme un objet métier en SimpleFeature.
	 * Un simpleFeature se base sur un schéma donnée, ce schéma est définit dans SimpleFeatureType
	 * 
	 * @param theObject: L'objet métier à transformer
	 * @param theFeatureType: Objet contentant le schéma de données. 
	 * 
	 * @return SimpleFeature
	 */
	public SimpleFeature build(E theObject , F theFeatureType) ; 

}
