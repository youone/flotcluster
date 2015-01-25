/**
 * 
 */

//function clustrifyPlot(plot) {
//	//console.log(plot);
//	plot.hooks.processRawData.push(clustrifySeries);//], drawSeries: [drawClusters]}}); 
//	plot.hooks.drawSeries.push(drawClusters);//], drawSeries: [drawClusters]}}); 
//	plot.setupGrid();
//	plot.draw();
//}
//
function clustrifyData(plot, series, data, datapoints) {
	console.log(data);
	series.data = [[100,200],[300,400]];
}

function clustrifySeries(plot, series, datapoints) {
	
	series.data = [[50,300],[700,800]];
	//console.log(series);
	//var xAxis = plot.getAxes().xaxis;
	//console.log(plot.getData());
}

function drawHook(plot, canvascontext, series) {
	console.log(plot.rawDataSeries);
}

myPlugin = {
		
		init: function(plot) {
			plot.hooks.drawSeries.push(drawHook);
			plot.rawDataSeries = {};
			plot.addRawDataSeries = this.addRawDataSeries;
			plot.updateSeries = this.updateSeries;
		},
		
		updateSeries: function(){
			plotData = [];
			for (iSerie in this.rawDataSeries) {
				plotData.push(this.rawDataSeries[iSerie]);
			}
			this.setData(plotData);
		},
		
		addRawDataSeries: function(series){
			if ("name" in series) this.rawDataSeries[series.name] = series;
			this.updateSeries();
		},

		addHook: function(hookFunction){
			console.log("adding hook");
			this.hooks.drawSeries.push(hookFunction);
		},

		addSeries: function(data){
			},
		
		options: {
			extraOption: "extra option"
		},
		
}


$(function() {

//	var d3 = [];
//	for (var ipoint = 0; ipoint < 10000; ipoint ++) {
//		
//		d3.push([Math.random() , Math.random()]);
//	}

	$.plot.plugins.push(myPlugin);

	var thePlot = null;

	thePlot = $.plot("#placeholder", []);

	series2 = {
			name: "series2",
			data : [[100,200],[300,400]],
			lines : {
				show : true
				},
			};
	thePlot.addRawDataSeries(series2);

	$.getJSON("http://192.168.0.13/flotcluster/php/flotcluster.php", function(data){
//		console.log(data);
		
		series1 = {
				name: "series1",
				data: data,
				cluster:{nxbins: 10, nybins: 10},
				points : {
					show : true
					},
				};

		thePlot.addRawDataSeries(series1);
		thePlot.setupGrid();
		thePlot.draw();

		
	
	});
	

});