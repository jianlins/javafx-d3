package org.treez.javafxd3.d3.arrays;

/**
 * Convenient methods to deal with arrays.
 * <p>
 *
 *
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 *
 */
public class Arrays {

	/**
	 * For each adjacent pair of elements in the specified array, returns a new
	 * array of tuples of element i and element i - 1. For example:
	 * <p>
	 * <code>
	 * d3.pairs([1, 2, 3, 4]); // returns [[1, 2], [2, 3], [3, 4]]
	 * </code>
	 * <p>
	 * If the specified array has fewer than two elements, returns the empty
	 * array.
	 *
	 * @param array
	 * @return
	 */
	// public static <T> Array<Array<T>> pairs(Array<T> array){
	// return $wnd.d3.pairs(array);
	// };

	/**
	 * Returns the maximum value in the given array using natural order.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#max}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 * In addition, elements are compared using natural order rather than
	 * numeric order. For example, the maximum of ["20", "3"] is "3", while the
	 * maximum of [20, 3] is 20.
	 *
	 * @param array
	 *            the array to be evaluated
	 * @return the maximum as a {@link Value} object
	 */
	// public static Value max(JavaScriptObject array) {
	// return {
	// datum : $wnd.d3.max(array)
	// };
	// };

	/**
	 * Transform the values in the given array using the specified
	 * {@link ForEachCallback} and returns the maximum value in the transformed
	 * values using natural order. For example, the maximum of ["20", "3"] is
	 * "3", while the maximum of [20, 3] is 20. If you want to ensure the
	 * numeric order, please consider using
	 * {@link #max(JavaScriptObject, NumericForEachCallback)}.
	 * <p>
	 * The given {@link ForEachCallback} is equivalent to calling
	 * array.map(accessor) before computing the maximum value.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#max}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 *
	 * @param array
	 *            the array to be transformed
	 * @param accessor
	 *            the function used to convert each element in the original
	 *            array to a transformed value
	 * @return the maximum of the transformed values as a {@link Value} object
	 */
	// public static final native Value max(JavaScriptObject array,
	// ForEachCallback<?> accessor) {
	// var rs = $wnd.d3
	// .max(
	// array,
	// function(d, i, a) {
	// return
	// accessor.@com.github.gwtd3.api.arrays.ForEachCallback::forEach(Ljava/lang/Object;Lcom/github/gwtd3/api/core/Value;ILcom/github/gwtd3/api/arrays/Array;)(this,{datum:d},i,a);
	// });
	// return {
	// datum : rs
	// };
	// };

	/**
	 * Transform the values in the given array using the specified
	 * {@link ForEachCallback} and returns the maximum value in the transformed
	 * values using natural order.
	 * <p>
	 * The given {@link NumericForEachCallback} is equivalent to calling
	 * array.map(accessor) before computing the maximum value.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#max}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 * In addition, elements are compared using numeric order.
	 *
	 * @param array
	 *            the array to be transformed
	 * @param accessor
	 *            the function used to convert each element in the original
	 *            array to a transformed value
	 * @return the maximum of the transformed values as a {@link Value} object
	 */
	// public static Value max(WebEngine webEngine, JavaScriptObject array,
	// NumericForEachCallback accessor) {
	// D3 d3 = new D3(webEngine);
	//
	// var rs = $wnd.d3
	// .max(
	// array,
	// function(d, i, a) {
	// return accessor.forEach(this,{datum:d},i,a);
	// });
	// return {
	// datum : rs
	// };
	// };

	/**
	 * Returns the minimum value in the given array using natural order.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#min}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 * In addition, elements are compared using natural order rather than
	 * numeric order. For example, the minimum of ["20", "3"] is "20", while the
	 * minimum of [20, 3] is 3.
	 *
	 * @param array
	 *            the array to be evaluated
	 * @return the minimum as a {@link Value} object
	 */
	// public static final native Value min(JavaScriptObject array) {
	// return {
	// datum : $wnd.d3.min(array)
	// };
	// };

	/**
	 * Transform the values in the given array using the specified
	 * {@link ForEachCallback} and returns the minimum value in the transformed
	 * values using natural order. For example, the minimum of ["20", "3"] is
	 * "20", while the minimum of [20, 3] is 3. If you want to ensure the
	 * numeric order, please consider using
	 * {@link #max(JavaScriptObject, NumericForEachCallback)}.
	 * <p>
	 * The given {@link ForEachCallback} is equivalent to calling
	 * array.map(accessor) before computing the minimum value.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#min}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 * In addition, elements are compared using natural order rather than
	 * numeric order.
	 *
	 * @param array
	 *            the array to be transformed
	 * @param accessor
	 *            the function used to convert each element in the original
	 *            array to a transformed value
	 * @return the minimum of the transformed values as a {@link Value} object
	 */
	// public static final native Value min(JavaScriptObject array,
	// ForEachCallback<?> accessor) {
	// var rs = $wnd.d3
	// .min(
	// array,
	// function(d, i, a) {
	// return
	// accessor.@com.github.gwtd3.api.arrays.ForEachCallback::forEach(Ljava/lang/Object;Lcom/github/gwtd3/api/core/Value;ILcom/github/gwtd3/api/arrays/Array;)(this,{datum:d},i,a);
	// });
	// return {
	// datum : rs
	// };
	// };

	/**
	 * Transform the values in the given array using the specified
	 * {@link ForEachCallback} and returns the minimum value in the transformed
	 * values using numeric order.
	 * <p>
	 * The given {@link NumericForEachCallback} is equivalent to calling
	 * array.map(accessor) before computing the minimum value.
	 * <p>
	 * If the array is empty, returns undefined.
	 * <p>
	 * Unlike the built-in {@link Math#min}, this method ignores undefined
	 * values; this is useful for computing the domain of a scale while only
	 * considering the defined region of the data.
	 * <p>
	 *
	 * @param array
	 *            the array to be transformed
	 * @param accessor
	 *            the function used to convert each element in the original
	 *            array to a transformed value
	 * @return the minimum of the transformed values as a {@link Value} object
	 */
	// public static final native Value min(JavaScriptObject array,
	// NumericForEachCallback accessor) {
	// var rs = $wnd.d3
	// .min(
	// array,
	// function(d, i, a) {
	// return
	// accessor.@com.github.gwtd3.api.arrays.NumericForEachCallback::forEach(Ljava/lang/Object;Lcom/github/gwtd3/api/core/Value;ILcom/github/gwtd3/api/arrays/Array;)(this,{datum:d},i,a);
	// });
	// return {
	// datum : rs
	// };
	// };

	/**
	 * Find the minimum and maximum value in an array. This is equivalent to
	 * calling d3.min and d3.max simultaneously.
	 *
	 * @param array
	 *            the given array.
	 * @return the minimum and maximum value in the given array using natural
	 *         order.
	 */
	// public static final native JsArrayMixed extent(JavaScriptObject array) {
	// return $wnd.d3.extent(array);
	// };

	/**
	 * Find the minimum and maximum value in an array. This is equivalent to
	 * calling d3.min and d3.max simultaneously.
	 *
	 * @param array
	 *            the given array.
	 * @return the minimum and maximum value in the given array using natural
	 *         order.
	 */
	// public static final native <D, R> JsArrayMixed extent(
	// JavaScriptObject array, ObjectAccessor<D, R> accessor) {
	// return $wnd.d3
	// .extent(
	// array,
	// function(d, i) {
	// return
	// accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(d,
	// i);
	// });
	// };

	/**
	 * Find the minimum and maximum value in an array. This is equivalent to
	 * calling d3.min and d3.max simultaneously.
	 *
	 * @param array
	 *            the given array.
	 * @return the minimum and maximum value in the given array using natural
	 *         order.
	 */
	// public static final native <D, R> double median(
	// JavaScriptObject array, ObjectAccessor<D, R> accessor) {
	// return $wnd.d3
	// .median(
	// array,
	// function(d, i) {
	// return
	// accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(d,
	// i);
	// });
	// };
	//
	// public static final native <D, R> double mean(
	// JavaScriptObject array, ObjectAccessor<D, R> accessor) {
	// return $wnd.d3
	// .mean(
	// array,
	// function(d, i) {
	// return
	// accessor.@com.github.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(d,
	// i);
	// });
	// };

	/**
	 * Compare two values for sorting.
	 * <p>
	 * This is the comparator function for natural order.
	 *
	 * @return
	 */
	// public static Sort ascending() {
	// return $wnd.d3.ascending;
	// };

	/**
	 * Compare two values for sorting.
	 * <p>
	 * This is the comparator function for reverse natural order.
	 *
	 * @return
	 */
	// public static Sort descending() {
	// return $wnd.d3.descending;
	// };

	/**
	 * Generate a range of <code>stop-1</code> numeric values, stored in an
	 * array, going from 0 to stop (excluded).
	 *
	 * @see D3#range(double, double, double)
	 * @param stop
	 *            the maximum value (excluded)
	 * @return the array
	 */
	// public static Array<Integer> range(double stop) {
	// return $wnd.d3.range(stop);
	// };

	/**
	 * Generate a range of numeric values, stored in an array, going from 0 to
	 * stop (exluded), separated by step (>0).
	 * <p>
	 * For instance, range(10, 3) would produce the array [0,3,6,9].
	 *
	 *
	 * @see D3#range(double, double, double)
	 * @param stop
	 *            the maximum value (excluded)
	 * @param step
	 *            the step between each value
	 * @return the array
	 */
	// public static Array<?> range(double stop, double step) {
	// return $wnd.d3.range(stop, step);
	// };

	/**
	 * Generate a range of numeric values.
	 * <p>
	 * Generates an array containing an arithmetic progression, similar to the
	 * Python built-in range. This method is often used to iterate over a
	 * sequence of numeric or integer values, such as the indexes into an array.
	 * Unlike the Python version, the arguments are not required to be integers,
	 * though the results are more predictable if they are due to floating point
	 * precision. If step is omitted, it defaults to 1. If start is omitted, it
	 * defaults to 0. The stop value is not included in the result. The full
	 * form returns an array of numbers [*start*, start + step, start + 2 *
	 * step, ...]. If step is positive, the last element is the largest start +
	 * i * step less than stop; if step is negative, the last element is the
	 * smallest start + i * step greater than stop. If the returned array would
	 * contain an infinite number of values, an error is thrown rather than
	 * causing an infinite loop.
	 *
	 * @param start
	 *            the first value-
	 * @param stop
	 *            the maximum value (excluded)
	 * @param step
	 *            the step between each value
	 * @return
	 */
	// public static final native JavaScriptObject range(double start,
	// double stop, double step) {
	// return $wnd.d3.range(start, stop, step);
	// };

}
