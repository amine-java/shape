package com.mbh.shape.reader;

import org.opengis.feature.simple.SimpleFeature;

public interface RowMapper<E> {
	
	/**
	 * Méthode permettant de mapper un SimpleFeature vers E
	 * 
	 * @param object
	 * @return E transformé
	 */
	public E map (SimpleFeature object); 
}
