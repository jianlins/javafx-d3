package com.github.javafxd3.d3.interpolators;

import netscape.javascript.JSObject;

/**
 * Represents an object that can create an {@link Interpolator}.
 * <p>
 * A factory should return null when no interpolators support the input arguments.
 * 
 * 
 * @param <O> 
 * 
 */
public interface InterpolatorFactory<O> {

	/**
	 * Create an interpolator appropriate for interpolating the input value, or return null if the factory does not support the two
	 * specified input values.
	 * <p>
	 * 
	 * @param a
	 *            the start input param
	 * @param b
	 *            the end input param
	 * 
	 * @return an appropriate interpolator or null if the factory does not support the input values.
	 */
	<I> Interpolator<O> create(I a, I b);

	/**
	 * Return a one-arg JS function wrapping the interpolation.
	 * <p>
	 * Consider using {@link CallableInterpolator} for your own interpolation.
	 * <p>
	 * 
	 * @return the JS function
	 */
	public JSObject asJSOFunction();
}
