package org.treez.javafxd3.d3.svg;

import java.util.Arrays;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.functions.JsFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 *
 * A {@link PathDataGenerator} is an object containing information to simplify
 * the construction of the <code>d</code> attribute for SVG <code>path</code>
 * element, allowing users to generate complex shapes.
 * <p>
 * The <code>d<language of path
 * commands, such as moveto (M), lineto (L) and closepath (Z). {@link PathDataGenerator}s are Javascript functions that
 * generate these commands. <br>
 * From a Javascript point of view, a {@link PathDataGenerator} is both an object containing properties and a function
 * that can be called to generate the path.
 * <p>
 * When passing the generator to the <code>d</code> attribute of a
 * <code>path</code> selection, the function represented by the generator is
 * called for each datum of the selection data. The function takes the datum in
 * argument and return the path.
 * <p>
 * Each generator specifies a default way of using the datum to create the path,
 * but generally speaking, the default behaviour can be overriden by providing
 * {@link DatumFunction} for each generator attribute. Please refer to generator
 * subclass documention for more information.
 * <p>
 *
 * Usage:
 * <p>
 *
 * <pre>
 * {@code
 * PathDataGenerator pathGenerator = ...
 * Selection g = D3.select("body").append("svg").append("g");
 * g.append("path").attr("d", pathGenerator);
 *
 * }
 * </pre>
 * <p>
 *
 * @see <a href="https:generators">Official API</a>
 */
public abstract class PathDataGenerator extends JavaScriptObject implements JsFunction {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public PathDataGenerator(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Generate the path data as String using the given data object in argument.
	 * <p>
	 * The data object must contains valid attributes for the corresponding
	 * generator to work (see each subclass documentation).
	 * <p>
	 * 
	 * 
	 * @param data
	 *            an array of data
	 * @return the generated path data
	 */
	public String generate(JavaScriptObject data) {

		// get and store JSObject
		JSObject dataObj = data.getJsObject();
		
		//Inspector.inspect(dataObj);
		
		String memberName = createNewTemporaryInstanceName();
		JSObject d3JsObj = getD3();
		d3JsObj.setMember(memberName, dataObj);
		
		// execute command to generate data
		String command = "this(d3." + memberName + ")";
		
		String result = (String) eval(command);
		
		d3JsObj.removeMember(memberName);
		
		return result;
		
	}

	/**
	 * Generate the path data as String using the given data object in argument.
	 * <p>
	 * The data object must contains valid attributes for the corresponding
	 * generator to work (see each subclass documentation).
	 * <p>
	 * 
	 * Apply the function using the given object in argument.
	 * <p>
	 * The object argument must provide missing attributes expected by the path
	 * generator. For instance, if this path generator is a {@link Line}, the
	 * provided object could be an array of {x,y} objects. Please refer to
	 * subclass documentation for more information.
	 * 
	 * 
	 * @param data
	 *            the array of data
	 * @param index
	 *            an index to be passed to each accessor functions
	 * @return the generated path data
	 */
	public String generate(JavaScriptObject data, int index) {

		// get and store JSObject
		JSObject dataObj = data.getJsObject();
		String dataMemberName = createNewTemporaryInstanceName();		
		JSObject d3JsObject = getD3();	
		d3JsObject.setMember(dataMemberName, dataObj);

		// execute command to generate data
		String command = "this(d3." + dataMemberName + ", " + index + ")";
		String result = evalForString(command);	
		
		d3JsObject.removeMember(dataMemberName);

		return result;
	}

	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            the data
	 * @return generated path data
	 */
	public final String generate(final List<? extends JavaScriptObject> data) {
		Array<?> array = Array.fromList(webEngine, data);
		return generate(array);
	}	
	
	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            the data
	 * @return generated path data
	 */
	public final String generate(final JavaScriptObject[] data) {		
		List<JavaScriptObject> list = Arrays.asList(data); 		
		return generate(Array.fromList(webEngine, list));
	}	
	
	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            an array of data
	 * @return the generated path data
	 */
	public final String generate(final Double... data) {
		Array<Double> array = Array.fromDoubles(webEngine, data);
		String result = generate(array);
		return result;
	}

	//#end region
}
