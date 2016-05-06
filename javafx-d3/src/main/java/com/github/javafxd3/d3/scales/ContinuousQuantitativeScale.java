package com.github.javafxd3.d3.scales;


import com.github.javafxd3.d3.arrays.ArrayUtils;
import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.time.TimeScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * {@link QuantitativeScale} with a continuous range:
 * <ul>
 * <li>{@link LinearScale} have a linear interpolator
 * <li>{@link LogScale} have apply a log function to the domain
 * <li>{@link PowScale}
 * <li>{@link IdentityScale}
 * <li>{@link TimeScale} are linear scale for timestamp
 * </ul>
 * 
 * 
 * @param <S> 
 * 
 */
public abstract class ContinuousQuantitativeScale<S extends ContinuousQuantitativeScale<S>>
		extends QuantitativeScale<S> {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public ContinuousQuantitativeScale(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	// =========== rangeRound ==========
	/**
	 * Sets the scale's output range to the specified array of values, while
	 * also setting the scale's interpolator to D3#interpolateRound().
	 * <p>
	 * This is a convenience routine for when the values output by the scale
	 * should be exact integers, such as to avoid antialiasing artifacts. It is
	 * also possible to round the output values manually after the scale is
	 * applied.
	 * <p>
	 * 
	 * @param values
	 *            the array of values.
	 * @param callingScale 
	 * @return the current scale for chaining
	 */
	public S rangeRound(JSObject values) {		
		JSObject result = call("rangeRound", values);
		S resultScale = createScale(webEngine, result);
		return resultScale;		
	}

	/**
	 * Factory method for creating a new scale
	 * @param webEngine
	 * @param result
	 * @return
	 */
	public abstract S createScale(WebEngine webEngine, JSObject result); 

	/**
	 * See #rangeRound(JSObject).
	 * 
	 * @param numbers
	 * @return the current scale for chaining
	 */
	public final S rangeRound(final double... numbers) {
		String arrayString = ArrayUtils.createArrayString(numbers);
		String command = "this.rangeRound(" + arrayString + ")";
		JSObject result = evalForJsObject(command);
		S resultScale = createScale(webEngine, result);
		return resultScale;	
		
		
	}

	/**
	 * See #rangeRound(JavaScriptObject).
	 * 
	 * @return the current scale for chaining
	 */
	public final S rangeRound(final String... strings) {
		String arrayString = ArrayUtils.createArrayString(strings);
		String command = "this.rangeRound(" + arrayString + ")";
		JSObject result = evalForJsObject(command);
		S resultScale = createScale(webEngine, result);
		return resultScale;
	}

	// =========== invert ==========

	/**
	 * Returns the value in the input domain x for the corresponding value in
	 * the output range y.
	 * <p>
	 * This represents the inverse mapping from range to domain. For a valid
	 * value y in the output range, apply(scale.invert(y)) equals y; similarly,
	 * for a valid value x in the input domain, scale.invert(apply(x)) equals x.
	 * <p>
	 * Equivalently, you can construct the invert operator by building a new
	 * scale while swapping the domain and range. The invert operator is
	 * particularly useful for interaction, say to determine the value in the
	 * input domain that corresponds to the pixel location under the mouse.
	 * <p>
	 * Note: the invert operator is only supported if the output range is
	 * numeric! D3 allows the output range to be any type; under the hood,
	 * d3.interpolate or a custom interpolator of your choice is used to map the
	 * normalized parameter t to a value in the output range. Thus, the output
	 * range may be colors, strings, or even arbitrary objects. As there is no
	 * facility to "uninterpolate" arbitrary types, the invert operator is
	 * currently supported only on numeric ranges.
	 * @param d 
	 * 
	 * @return
	 */
	public  Value invert(double d){
		String command = "this.invert(" + d + ")";
		Object result = eval(command);
		if (result==null){
			return null;
		}
		Value value = Value.create(webEngine, result);
		
		return value;		
	}

	// =========== clamp ==========

	/**
	 * returns whether or not the scale currently clamps values to within the
	 * output range.
	 * 
	 * @return true if clamping is enabled, false otherwise.
	 */
	public boolean clamp() {
		Boolean result = callForBoolean("clamp");
		return result;		
	}

	/**
	 * Enables or disables clamping accordingly.
	 * <p>
	 * By default, clamping is disabled, such that if a value outside the input
	 * domain is passed to the scale, the scale may return a value outside the
	 * output range through linear extrapolation.
	 * <p>
	 * For example, with the default domain and range of [0,1], an input value
	 * of 2 will return an output value of 2.
	 * <p>
	 * If clamping is enabled, the normalized domain parameter t is clamped to
	 * the range [0,1], such that the return value of the scale is always within
	 * the scale's output range.
	 * <p>
	 * @param clamping 
	 * 
	 * @return the current scale for chaining
	 */
	public S clamp(boolean clamping) {		
		JSObject result = call("clamp", clamping);
		S resultScale = createScale(webEngine, result);
		return resultScale;		
	}

	// ======== interpolate ========
	// TODO : WORK IN PROGRESS
	// /**
	// * If factory is specified, sets the scale's output interpolator using the
	// * specified factory.
	// * <p>
	// * The interpolator factory defaults to {@link Interpolators#interpolate},
	// * and is used to map the normalized domain parameter t in [0,1] to the
	// * corresponding value in the output range. The interpolator factory will
	// be
	// * used to construct interpolators for each adjacent pair of values from
	// the
	// * output range.
	// *
	// * @param factory
	// * the factory used to create an interpolator
	// * @return the scale
	// */
	// public final S interpolate(InterpolatorFactory<?> factory) {
	// return interpolate0(factory.asJSOFunction());
	// }
	//
	// /**
	// * Return the scale's interpolator factory.
	// *
	// * @return the factory
	// */
	// public final InterpolatorFactory<?> interpolate() {
	// return this.interpolate0();
	// }
	//
	// protected JSNIInterpolatorFactory<?> interpolate0(){
	// return this.interpolate();
	// }
	//
	// protected S interpolate0(JavaScriptObject factory){
	// return this.interpolate(factory);
	// }

	//#end region

}
