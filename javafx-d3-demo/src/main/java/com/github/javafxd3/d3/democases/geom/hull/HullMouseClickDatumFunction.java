package com.github.javafxd3.d3.democases.geom.hull;

import java.util.List;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.democases.geom.hull.HullDemo.MyCoords;
import com.github.javafxd3.d3.functions.DatumFunction;
import com.github.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullMouseClickDatumFunction implements DatumFunction<Void> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	private List<MyCoords> vertices;

	private D3 d3;
	
	private Runnable redrawRunnable;

	//#end region

	//#region CONSTRUCTORS

	public HullMouseClickDatumFunction(
				WebEngine webEngine, 
				List<MyCoords> vertices, 
				D3 d3,
				Runnable redrawRunnable
		){
			this.webEngine=webEngine;
			this.vertices = vertices;
			this.d3 = d3;
			this.redrawRunnable=redrawRunnable;
		}

	//#end region

	//#region METHODS

	@Override
	public Void apply(final Object context, final Object d, final int index) {

		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);
		MyCoords coords = new MyCoords(webEngine, d3.mouseX(element), d3.mouseY(element));
		vertices.add(coords);
		redrawRunnable.run();
		return null;	
		
	}

	//#end region

}
