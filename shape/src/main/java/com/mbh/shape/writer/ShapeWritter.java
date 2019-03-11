package com.mbh.shape.writer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.geotools.data.DefaultTransaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

/**
 * Class ShapeWritter: Class permettant la génération de fichiers shape à partir d'un jeu de données.
 *   
 * @author boufatah
 *
 */
public class ShapeWritter {

	/** Extension Fichier Shape */
	private static final String FILE_EXTENSION_SHAPEFILE = ".shp";

	/**
	 * Méthode 'EntyPoint': Génération d'un fichier shape, à partir d'un jeu de données.
	 * 
	 * @param geomType: le type de géométrie du jeu de données
	 * @param features: le jeu de données.
	 * @param file: le fichier output
	 * 
	 * @throws IOException
	 */
	public void writeEsriShapefile(Class<?> geomType, List<SimpleFeature> features, File file) throws IOException {
		String geomName = geomType.getSimpleName();
		String basename = file.getName();
		if (basename.endsWith(FILE_EXTENSION_SHAPEFILE)) {
			basename = basename.substring(0, basename.length() - 4);
		}
		File file1 = new File(file.getParentFile(), basename + "_" + geomName + FILE_EXTENSION_SHAPEFILE);

		SimpleFeature simpleFeature = features.get(0);
		SimpleFeatureType simpleFeatureType = changeGeometryType(simpleFeature.getType(), geomType);

		ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
		Map<String, URL> map = Collections.singletonMap("url", file1.toURI().toURL());
		ShapefileDataStore dataStore = (ShapefileDataStore) factory.createNewDataStore(map);
		dataStore.createSchema(simpleFeatureType);
		String typeName = dataStore.getTypeNames()[0];
		SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);
		DefaultTransaction transaction = new DefaultTransaction("X");
		if (featureSource instanceof SimpleFeatureStore) {
			SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
			SimpleFeatureCollection collection = new ListFeatureCollection(simpleFeatureType, features);
			featureStore.setTransaction(transaction);

			try {
				featureStore.addFeatures(collection);
				transaction.commit();
			} catch (Exception problem) {
				transaction.rollback();
				throw new IOException(problem);
			} finally {
				transaction.close();
			}
		} else {
			throw new IOException(typeName + " does not support read/write access");
		}
	}

	/**
	 * Méthode permettant de forcer le geometrieType
	 * 
	 * @param original
	 * @param geometryType
	 * @return SimpleFeatureType
	 */
	public SimpleFeatureType changeGeometryType(SimpleFeatureType original, Class<?> geometryType) {
		SimpleFeatureTypeBuilder sftb = new SimpleFeatureTypeBuilder();
		sftb.setCRS(original.getCoordinateReferenceSystem());
		sftb.setDefaultGeometry(original.getGeometryDescriptor().getLocalName());
		boolean defaultGeometryAdded = false;
		for (AttributeDescriptor descriptor : original.getAttributeDescriptors()) {
			if (original.getGeometryDescriptor().getLocalName().equals(descriptor.getLocalName())) {
				sftb.add(descriptor.getLocalName(), geometryType);
				defaultGeometryAdded = true;
			} else {
				sftb.add(descriptor);
			}
		}
		if (!defaultGeometryAdded) {
			sftb.add(original.getGeometryDescriptor().getLocalName(), geometryType);
		}
		sftb.setName("FT_" + geometryType.getSimpleName());
		return sftb.buildFeatureType();
	}

	/**
	 * Construction du jeu de données shape à partir d'un jeu de données 'métiers'. 
	 * 
	 * @param featureBuilder: Le featureBuilder de l'objet à traiter
	 * @param featureType: Le featureType à utiliser
	 * @param elements: Les objets métiers à transformer en SimpleFeature
	 * 
	 * @return List<SimpleFeature>
	 */
	public List<SimpleFeature> buildSimpleFeatures(final FeatureBuilder featureBuilder, SimpleFeatureType featureType,
			final List<? extends Object> elements) {
		List<SimpleFeature> theResults = new ArrayList<SimpleFeature>();
		for (Object element : elements) {
			theResults.add(buildSimpleFeature(featureBuilder, featureType, element));
		}
		return theResults;

	}

	private SimpleFeature buildSimpleFeature(final FeatureBuilder featureBuilder, SimpleFeatureType featureType,
			Object theObject) {
		return featureBuilder.build(theObject, featureType);
	}

}
