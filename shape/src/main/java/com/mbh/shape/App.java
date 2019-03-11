package com.mbh.shape;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.feature.simple.SimpleFeatureTypeBuilder;

import com.mbh.shape.exceptions.ShapeReadException;
import com.mbh.shape.reader.ElementGeoRowMapper;
import com.mbh.shape.reader.ShapeReader;
import com.mbh.shape.writer.ElementFeatureTypeBuilder;
import com.mbh.shape.writer.ElementGeoFeatureBuilder;
import com.mbh.shape.writer.FeatureBuilder;
import com.mbh.shape.writer.ShapeWritter;
import com.vividsolutions.jts.geom.MultiPolygon;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ShapeReadException
    {
        ShapeReader sh = new ShapeReader(new ElementGeoRowMapper());
        ShapeWritter shw = new ShapeWritter();
        List<ElementGeo> results = new ArrayList<ElementGeo>();
        FeatureBuilder featureBuilder = new ElementGeoFeatureBuilder();
        SimpleFeatureTypeBuilder simpleFeatureTypeBuilder = new ElementFeatureTypeBuilder();
		try {
			results = sh.read();
			File file = new File("C:\\test2.shp");
			shw.writeEsriShapefile(MultiPolygon.class,
					shw.buildSimpleFeatures(featureBuilder,
					simpleFeatureTypeBuilder.buildFeatureType(),
					results),
					file);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Taille: " +results.size());
		for (ElementGeo elem : results) {
			System.out.println(elem);
		}
    }
}
