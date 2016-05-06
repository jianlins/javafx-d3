package org.treez.javafxd3.d3.layout;



import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Layout extends JavaScriptObject {
	
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Layout(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}    
    
    //#end region
    
    //#region METHODS

    /**
     * @return
     */
	public  Chord chord() {
		JSObject result = call("chord");
		return new Chord(webEngine, result);		
	}

	/**
	 * Creates a new tree layout with the default settings: the default sort
	 * order is null; the default children accessor assumes each input data is
	 * an object with a children array; the default separation function uses one
	 * node width for siblings, and two node widths for non-siblings; the
	 * default size is 1×1.
	 * 
	 * @return the tree layout object
	 */
	public  Tree tree() {
		JSObject result = call("tree");
		return new Tree(webEngine, result);
	}

	/**
	 * Creates a new cluster layout with the default settings: the default sort
	 * order is null; the default children accessor assumes each input data is
	 * an object with a children array; the default separation function uses one
	 * node width for siblings, and two node widths for non-siblings; the
	 * default size is 1×1.
	 * 
	 * @return the new cluster generator
	 */
	public  Cluster cluster(){
		JSObject result = call("cluster");
		return new Cluster(webEngine, result);
	}

    /**
     * Constructs a new force-directed layout with the default settings: size
     * 1×1, link strength 1, friction 0.9, distance 20, charge strength -30,
     * gravity strength 0.1, and theta parameter 0.8. The default nodes and
     * links are the empty array, and when the layout is started, the internal
     * alpha cooling parameter is set to 0.1. The general pattern for
     * constructing force-directed layouts is to set all the configuration
     * properties, and then call start():
     * 
     * @return the new force layout object
     */
    public  Force force(){
    	JSObject result = call("force");
		return new Force(webEngine, result);
	}
}
