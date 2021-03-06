package com.github.javafxd3.d3.democases.functionplotter;

import com.github.javafxd3.d3.AbstractDemoCase;
import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.DemoCase;
import com.github.javafxd3.d3.DemoFactory;
import com.github.javafxd3.d3.core.Selection;
import com.github.javafxd3.functionplot.FunctionPlot;
import com.github.javafxd3.functionplot.Options;

import javafx.scene.layout.VBox;

public class FunctionPlotDemo extends AbstractDemoCase {

	//#region CONSTRUCTORS
	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public FunctionPlotDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new FunctionPlotDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		Selection svg = getSvg() //
				.attr("width", 700) //
				.attr("height", 400);

		Options options = new Options(webEngine) //
				.setTarget("#svg") //
				.setDataExpression("[{fn: 'exp(-x^2)'}]");
		
		FunctionPlot functionPlot = new FunctionPlot(webEngine);
		functionPlot.apply(options);
		
	}

	@Override
	public void stop() {

	}

	//#end region

}
