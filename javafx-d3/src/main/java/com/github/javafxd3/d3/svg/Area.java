package com.github.javafxd3.d3.svg;

import com.github.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Generates path data for a closed piecewise linear curve, or polygon, as in an
 * area chart:
 * <p>
 * <img src="https://github.com/mbostock/d3/wiki/area.png"/>
 * <p>
 * Conceptually, the polygon is formed using two lines: the top line is formed
 * using the x- and y1-accessor functions, and proceeds from left-to-right; the
 * bottom line is added to this line, using the x- and y0-accessor functions,
 * and proceeds from right-to-left. By setting the transform attribute to rotate
 * the path element by 90 degrees, you can also generate vertical areas. By
 * changing the interpolation, you can also generate splines and step functions.
 * <p>
 * The area generator is designed to work in conjunction with the line
 * generator. For example, when producing an area chart, you might use an area
 * generator with a fill style, and a line generator with a stroke style to
 * emphasize the top edge of the area. Since the area generator is only used to
 * set the d attribute, you can control the appearance of the area using
 * standard SVG styles and attributes, such as fill.
 * <p>
 * To create streamgraphs (stacked area charts), use the stack layout. This
 * layout sets the y0 attribute for each value in a series, which can be used
 * from the y0- and y1-accessors. Note that each series must have the same
 * number of values per series, and each value must have the same x-coordinate;
 * if you have missing data or inconsistent x-coordinates per series, you must
 * resample and interpolate your data before computing the stacked layout.
 * <p>
 */
public class Area extends PathDataGenerator {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Area(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Returns the current interpolation mode.
	 * 
	 * @return the current interpolation mode.
	 */
	public InterpolationMode interpolate() {
		String mode = callForString("interpolate");
		InterpolationMode result = InterpolationMode.fromValue(mode);
		return result;
	}

	/**
	 * Set the current interpolation mode.
	 * 
	 * @param i
	 *            the interpolation mode
	 * @return the current area
	 */
	public Area interpolate(final InterpolationMode mode) {

		String modeString = mode.getValue();
		JSObject result = call("interpolate", modeString);
		return new Area(webEngine, result);
	}

	/**
	 * Set the function used to compute x coordinates of points generated by
	 * this area generator. The function is invoked for each element in the data
	 * array passed to the area generator.
	 * <p>
	 * The default accessor assumes that each input element is a two-element
	 * array of numbers.
	 * 
	 * @param datumFunction
	 * @return
	 */
	public Area x(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.x(function(d, i) { return d3." + memberName + ".apply(this,{datum:d},i); });";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);
	}

	/**
	 * Set the x coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area x(double d) {
		JSObject result = call("x", d);
		return new Area(webEngine, result);
	}

	/**
	 * Set the x0 coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area x0(double d) {
		JSObject result = call("x0", d);
		return new Area(webEngine, result);
	}

	/**
	 * @see #x(DatumFunction).
	 * @param callback
	 * @return
	 */
	public Area x0(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.x0(function(d, i) { "//
				+ "return d3." + memberName + ".apply(this,{datum:d},i); "//
				+ "});";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);

	}

	/**
	 * Set the x1 coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area x1(double d) {
		JSObject result = call("x1", d);
		return new Area(webEngine, result);

	}

	/**
	 * @see #x(DatumFunction).
	 * @param callback
	 * @return
	 */
	public Area x1(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.x1(function(d, i) { "//
				+ "return d3." + memberName + ".apply(this,{datum:d},i); "//
				+ "});";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);
	}

	/**
	 * Set the y coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area y(double d) {
		JSObject result = call("y", d);
		return new Area(webEngine, result);
	}

	/**
	 * Set the y0 coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area y0(double d) {
		JSObject result = call("y0", d);
		return new Area(webEngine, result);
	}

	/**
	 * Set the y1 coordinates of points generated by this generator.
	 * 
	 * @param d
	 * @return
	 */
	public Area y1(double d) {
		JSObject result = call("y1", d);
		return new Area(webEngine, result);
	}

	/**
	 * See {@link #x(DatumFunction)}.
	 * <p>
	 * Note that, like most other graphics libraries, SVG uses the top-left
	 * corner as the origin and thus higher values of y are lower on the screen.
	 * For visualization we often want the origin in the bottom-left corner
	 * instead; one easy way to accomplish this is to invert the range of the
	 * y-scale by using range([h, 0]) instead of range([0, h]).
	 * 
	 * @param callback
	 * @return the current area
	 */
	public Area y(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.y(function(d, i) { "//
				+ "return d3." + memberName + ".apply(this,{datum:d},i); "//
				+ "});";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);
	}

	/**
	 * See {@link #y(DatumFunction)}.
	 * <p>
	 * Note that, like most other graphics libraries, SVG uses the top-left
	 * corner as the origin and thus higher values of y are lower on the screen.
	 * For visualization we often want the origin in the bottom-left corner
	 * instead; one easy way to accomplish this is to invert the range of the
	 * y-scale by using range([h, 0]) instead of range([0, h]).
	 * 
	 * @param callback
	 * @return the current area
	 */
	public Area y0(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.y0(function(d, i) { return d3." + memberName + ".apply(this,{datum:d},i); });";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);
	}

	/**
	 * See {@link #y(DatumFunction)}.
	 * <p>
	 * Note that, like most other graphics libraries, SVG uses the top-left
	 * corner as the origin and thus higher values of y are lower on the screen.
	 * For visualization we often want the origin in the bottom-left corner
	 * instead; one easy way to accomplish this is to invert the range of the
	 * y-scale by using range([h, 0]) instead of range([0, h]).
	 * 
	 * @param callback
	 * @return the current area
	 */
	public Area y1(final DatumFunction<Double> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.y1(function(d, i) { return d3." + memberName + ".apply(this,{datum:d},i); });";
		JSObject result = evalForJsObject(command);
		return new Area(webEngine, result);

	}

	/**
	 * FIXME: D3 bug ??? Does not seem to work...
	 * 
	 * Sets the function used to controls where the area is defined.
	 * <p>
	 * The defined accessor can be used to define where the area is defined and
	 * undefined, which is typically useful in conjunction with missing data;
	 * the generated path data will automatically be broken into multiple
	 * distinct subpaths, skipping undefined data.
	 * <p>
	 * 
	 * @param callback
	 * @return
	 */
	public Line defined(final DatumFunction<Boolean> callback) {
		
		assertObjectIsNotAnonymous(callback);

		String memberName = createNewTemporaryInstanceName();
		JSObject d3jsObj = getD3();
		d3jsObj.setMember(memberName, callback);

		String command = "this.defined(function(d) { " //
				+ "alert('yo'); " //
				+ "var result = d3." + memberName + ".apply(null,{datum:d}, 0); "//
				+ "alert(result); "//
				+ "return result; "//
				+ "});";

		JSObject result = evalForJsObject(command);
		return new Line(webEngine, result);

	}

	/**
	 * Returns the current tension
	 * 
	 * @return the current tension
	 */
	public double tension() {
		Double result = callForDouble("tension");
		return result;
	}

	/**
	 * Sets the Cardinal spline interpolation tension to the specified number in
	 * the range [0, 1].
	 * <p>
	 * The tension only affects the Cardinal interpolation modes:
	 * {@link InterpolationMode#CARDINAL},
	 * {@link InterpolationMode#CARDINAL_OPEN} and
	 * {@link InterpolationMode#CARDINAL_CLOSED}.
	 * <p>
	 * The default tension is 0.7.
	 * <p>
	 * In some sense, this can be interpreted as the length of the tangent; 1
	 * will yield all zero tangents, and 0 yields a Catmull-Rom spline.
	 * 
	 * @see <a href="http://bl.ocks.org/1016220">live version</a>
	 * @param tension
	 *            the tension in the range [0, 1].
	 * @return the current area generator
	 */
	public Area tension(double tension) {
		JSObject result = call("tension", tension);
		return new Area(webEngine, result);
	}

	/**
	 * Generate a piecewise linear area, as in an area chart.
	 * <p>
	 * Data must be an array-like structure. the type of the array elements
	 * depends on the x and y functions. the default x and y functions assumes
	 * that each input element is a two-element array of numbers.
	 * 
	 * @param data
	 * 
	 * @return
	 */
	public <T> String apply(T data) {
		String result = callThisForString(data);
		return result;
	}

	/**
	 * Generate a piecewise linear area, as in an area chart.
	 * <p>
	 * Data must be an array-like structure. the type of the array elements
	 * depends on the x and y functions. the default x and y functions assumes
	 * that each input element is a two-element array of numbers.
	 * <p>
	 * The index will be passed through to the line's accessor functions.
	 * <p>
	 * 
	 * @param data
	 * @param index
	 * @return
	 */
	public <T> String apply(T data, int index) {
		String result = callForString("this", data, index);
		return result;
	}

	//#end region

	//#region ENUM

	/**
	 * Represents the mode of interpolation
	 *
	 */
	public static enum InterpolationMode {

		//#region VALUES

		/**
		 * piecewise linear segments, as in a polyline.
		 */
		LINEAR("linear"),

		/**
		 * alternate between horizontal and vertical segments, as in a step
		 * function
		 */
		STEP("step"),

		/**
		 * alternate between vertical and horizontal segments, as in a step
		 * function.
		 */
		STEP_BEFORE("step-before"),

		/**
		 * alternate between horizontal and vertical segments, as in a step
		 * function
		 */
		STEP_AFTER("step-after"),

		/**
		 * a B-spline, with control point duplication on the ends.
		 */
		BASIS("basis"),
		/**
		 * an open B-spline; may not intersect the start or end.
		 */
		BASIS_OPEN("basis-open"),
		/**
		 * a Cardinal spline, with control point duplication on the ends.
		 */
		CARDINAL("cardinal"),
		/**
		 * an open Cardinal spline; may not intersect the start or end, but will
		 * intersect other control points.
		 */
		CARDINAL_OPEN("cardinal-open"),
		/**
		 * cubic interpolation that preserves monotonicity in y.
		 */
		MONOTONE("monotone");

		//#end region

		//#region ATTRIBUTES

		private final String value;

		//#end region

		//#region CONSTRUCTORS

		private InterpolationMode(final String value) {
			this.value = value;
		}

		//#end region

		//#region METHODS

		/**
		 * @param value
		 * @return
		 */
		public static InterpolationMode fromValue(final String value) {
			return valueOf(value.toUpperCase().replace('-', '_'));
		}

		//#end region

		//#region ACCESSORS

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		//#end region

	}

	//#end region

}
