package com.mbh.shape.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;

public class ShapeReader {

	private RowMapper<? extends Object> rowMapper;

	/**
	 * Constructeur avec un rowMapper
	 * 
	 * @param rowMapper
	 */
	public ShapeReader(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

	/**
	 * Constructeur par defaut, sans rowMapper
	 */
	public ShapeReader() {
	}

	public List read() throws IOException {
		String fileName = "C:\\\\downloadsig\\\\ensemble_param_export_sig.SHP";
		fileName = "C:\\downloadsig\\doss2\\Zones_d'aire_protegées.SHP";
		File inFile = new File(fileName);
		Map map = new HashMap();
		map.put("url", inFile.toURL());
		DataStore dataStore = DataStoreFinder.getDataStore(map);
		String inputTypeName = dataStore.getTypeNames()[0];
		SimpleFeatureSource featureSource = null;

		featureSource = dataStore.getFeatureSource(inputTypeName);
		SimpleFeatureCollection collection = featureSource.getFeatures();
		List<Object> theResults = new ArrayList<Object>();

		FeatureIterator<SimpleFeature> iterator = collection.features();
		try {
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				Object object = rowMapper != null ? rowMapper.map(feature) : feature;
				theResults.add(object);
			}
		} finally {
			iterator.close();
		}

		return theResults;
	}

	public RowMapper getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

}
