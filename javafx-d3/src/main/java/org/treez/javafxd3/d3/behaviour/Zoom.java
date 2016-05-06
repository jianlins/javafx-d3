package org.treez.javafxd3.d3.behaviour;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.QuantitativeScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * This behavior automatically creates event listeners to handle scroll gestures
 * and pan gestures on an element. Events including mouse, touch, and scroll are
 * supported.
 * <p>
 * Usage:
 * 
 * <pre>
 * {
 * 	&#064;code
 * 	Zoom zoom = D3.behavior.zoom().on(ZoomEventType.Zoom, new MyZoomListener());
 * 	mySelection.call(zoom);
 * }
 * </pre>
 * 
 * 
 * <a href="https://github.com/augbog">Augustus Yuan</a>
 * 
 */
public class Zoom extends JavaScriptObject implements JsFunction {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Zoom(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Type of scroll event to listen to.
	 */
	public static enum ZoomEventType {

		/**
		 * at the start of a zoom gesture (e.g., touchstart).
		 */
		ZOOMSTART,

		/**
		 * when the view changes (e.g., touchmove).
		 */
		ZOOM,

		/**
		 * at the end of the current zoom gesture (e.g., touchend).
		 */
		ZOOMEND;
	}

	/**
	 * Registers the specified listener to receive events of the specified type
	 * from the zoom behavior.
	 * <p>
	 * See {@link ZoomEventType} for more information.
	 * <p>
	 * If an event listener was already registered for the same type, the
	 * existing listener is removed before the new listener is added. TODO: To
	 * register multiple listeners for the same event type, the type may be
	 * followed by an optional namespace, such as "zoom.foo" and "zoom.bar". To
	 * remove a listener, pass null as the listener.
	 * <p>
	 * For mousewheel events, which happen discretely with no explicit start and
	 * end reported by the browser, events that occur within 50 milliseconds of
	 * each other are grouped into a single zoom gesture. If you want more
	 * robust interpretation of these gestures, please petition your browser
	 * vendor of choice for better touch event support.
	 * <p>
	 * 
	 * @param type
	 * @param listener
	 * @return the current zoom instance
	 */
	public Zoom on(ZoomEventType type, DatumFunction<Void> listener) {
		
		assertObjectIsNotAnonymous(listener);

		String listenerName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(listenerName, listener);

		String eventName = type.name().toLowerCase();
		
		String command = "this.on('" + eventName + "', " + "function(d, index) { " //				
				+ "d3." + listenerName + ".apply(this,{datum:d},index);" //
				+ " });";		
		
		JSObject result = evalForJsObject(command);
		return new Zoom(webEngine, result);
	}

	/**
	 * Return the current x-scale that is automatically adjusted when zooming,
	 * or null if no scale have been specified.
	 * <p>
	 * 
	 * @return the x-scale
	 */
	public QuantitativeScale<?> x() {
		JSObject result = call("x");
		return new LinearScale(webEngine, result);
	}

	/**
	 * Specifies an x-scale whose domain should be automatically adjusted when
	 * zooming.
	 * <p>
	 * If the scale's domain or range is modified programmatically, this
	 * function should be called again.
	 * 
	 * @param scale
	 *            the scale
	 * @return Zoom the current Zoom object
	 */
	public Zoom x(QuantitativeScale<?> scale) {
		JSObject jsScale = scale.getJsObject();
		JSObject result = call("x", jsScale);
		return new Zoom(webEngine, result);
	}

	/**
	 * Return the current y-scale that is automatically adjusted when zooming,
	 * or null if no scale have been specified.
	 * <p>
	 * 
	 * @return the y-scale
	 */
	public QuantitativeScale<?> y() {
		JSObject result = call("y");
		return new LinearScale(webEngine, result);
	}

	/**
	 * Specifies an y-scale whose domain should be automatically adjusted when
	 * zooming.
	 * <p>
	 * If not specified, returns the current y-scale, which defaults to null. If
	 * the scale's domain or range is modified programmatically, this function
	 * should be called again
	 * <p>
	 * 
	 * @param scale
	 * @return the current zoom object
	 */
	public Zoom y(QuantitativeScale<?> scale) {
		JSObject jsScale = scale.getJsObject();
		JSObject result = call("y", jsScale);
		return new Zoom(webEngine, result);
	}

	/**
	 * Return the zoom scale's allowed range as a two-element array, [*minimum*,
	 * maximum].
	 * <p>
	 * 
	 * @return the zoom scale's allowed range as a two-element array
	 */
	public Array<Double> scaleExtent() {
		JSObject result = call("scaleExtent");
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Specifies the zoom scale's allowed range as a two-element array,
	 * [*minimum*, maximum]. If not specified, returns the current scale extent,
	 * which defaults to [0, Infinity].
	 * <p>
	 * 
	 * @param scale
	 * 
	 *            zoom scale's allowed range as a two-element array
	 * @return the current zoom object
	 */
	public Zoom scaleExtent(Double[] scale) {
		String arrayString = ArrayUtils.createArrayString(scale);
		String command = "this.scaleExtent(" + arrayString + ")";
		JSObject result = evalForJsObject(command);
		return new Zoom(webEngine, result);
	}

	/**
	 * If center is specified, sets the
	 * <a href="http://bl.ocks.org/mbostock/6226534"> focal point</a> [x, y] for
	 * mouse wheel zooming and returns this zoom behavior.
	 * <p>
	 * A null center indicates that mouse wheel zooming should zoom in and out
	 * around the current mouse location.
	 * <p>
	 * 
	 * @param x
	 * @param y
	 * 
	 * @return the
	 */
	public Zoom center(double x, double y) {
		String command = "this.center([" + x + "," + y + "])";
		JSObject result = evalForJsObject(command);
		return new Zoom(webEngine, result);
	}

	/**
	 * Returns the current focal point, which defaults to null.
	 * <p>
	 * A null center indicates that mouse wheel zooming should zoom in and out
	 * around the current mouse location.
	 * <p>
	 * 
	 * @return the array of double
	 */
	public Array<Double> center() {
		JSObject result = call("center");
		if (result == null) {
			return null;
		}
		return new Array<Double>(webEngine, result);
	}

	/**
	 * If size is specified, sets the viewport size to the specified dimensions
	 * [width, height] and returns this zoom behavior.
	 * <p>
	 * A size is needed to support smooth zooming during transitions.
	 * 
	 * @param width
	 *            the width of the viewport
	 * @param height
	 *            the height of the viewport
	 * @return the current zoom instance
	 */
	public Zoom size(int width, int height) {
		String command = "this.size([" + width + "," + height + "])";
		JSObject result = evalForJsObject(command);
		return new Zoom(webEngine, result);
	}

	/**
	 * Returns the current viewport size which defaults to [960, 500].
	 * <p>
	 * 
	 * @return the size
	 */
	public Array<Double> size() {
		JSObject result = call("size");
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Returns the current zoom scale, which defaults to 1.
	 * <p>
	 * 
	 * @return the current zoom scale
	 */
	public double scale() {
		Double result = callForDouble("scale");
		return result;
	}

	/**
	 * Specifies the current zoom scale.
	 * <p>
	 * 
	 * @param scale
	 *            the zoom scale factor
	 * @return the current zoom object
	 */
	public Zoom scale(double scale) {
		JSObject result = call("scale", scale);
		return new Zoom(webEngine, result);
	}

	/**
	 * Returns the current translation vector, which defaults to [0, 0].
	 * <p>
	 * 
	 * @return the current translation vector
	 */
	public Array<Double> translate() {
		JSObject result = call("translate");
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Specifies the current zoom translation vector.
	 * <p>
	 * 
	 * @param vector
	 *            current zoom translation vector
	 * @return the current zoom object
	 */
	public Zoom translate(Double[] vector) {
		String arrayString = ArrayUtils.createArrayString(vector);
		String command = "this.translate(" + arrayString + ");";
		JSObject result = evalForJsObject(command);
		return new Zoom(webEngine, result);
	}

	/**
	 * Immediately dispatches a zoom gesture to registered listeners, as the
	 * three event sequence {@link ZoomEventType#ZOOMSTART},
	 * {@link ZoomEventType#ZOOM} and {@link ZoomEventType#ZOOMEND}.
	 * <p>
	 * This can be useful in triggering listeners after setting the
	 * #translate(Array) or {@link #scale(double)} programmatically.
	 * <p>
	 * 
	 * @param selection
	 *            the selection to triggers the events to.
	 * @return the current zoom
	 */
	public Zoom event(Selection selection) {
		JSObject jsObject = selection.getJsObject();
		JSObject result = call("event", jsObject);
		return new Zoom(webEngine, result);
	}

	/**
	 * Registers the appropriate tweens so that the zoom behavior dispatches
	 * events over the course of the transition: a
	 * {@link ZoomEventType#ZOOMSTART} event when the transition starts from the
	 * previously-set view, {@link ZoomEventType#ZOOM} events for each tick of
	 * the transition, and finally a {@link ZoomEventType#ZOOMEND} event when
	 * the transition ends.
	 * <p>
	 * Note that the transition will be interrupted if the user starts zooming
	 * before the transition ends.
	 * <p>
	 * 
	 * @param selection
	 * @return
	 */
	public Zoom event(Transition selection) {
		JSObject jsObject = selection.getJsObject();
		JSObject result = call("event", jsObject);
		return new Zoom(webEngine, result);
	}

	/**
	 * Provide access to the properties of a zoom event.
	 * <p>
	 * Use {@link D3#zoomEvent()} from within a
	 * {@link Zoom#on(ZoomEventType, DatumFunction)} listener.
	 * <p>
	 * 
	 * 
	 * 
	 */
	public static class ZoomEvent extends JavaScriptObject {

		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param webEngine
		 * @param wrappedJsObject
		 */
		public ZoomEvent(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine);
			setJsObject(wrappedJsObject);
		}

		//#end region

		//#region METHODS

		/**
		 * The scale of the zoom
		 * <p>
		 * 
		 * @return the scale of the zoom
		 */

		public double scale() {
			Double result = getMemberForDouble("scale");
			return result;
		}

		/**
		 * A two-element array representing the current translation vector.
		 * 
		 * @return the translation vector
		 */

		public Array<Double> translate() {
			JSObject result = getMember("translate");
			return new Array<Double>(webEngine, result);
		}

		/**
		 * Shortcut to translate().getNumber(0).
		 * 
		 * @return the translation x coord
		 */

		public double translateX() {
			Double result = evalForDouble("this.translate[0];");
			return result;
		}

		/**
		 * Shortcut to translate().getNumber(1).
		 * 
		 * @return the translation y coord
		 */

		public double translateY() {
			Double result = evalForDouble("this.translate[1];");
			return result;
		}

		//#end region
	}

	//#end region

}
