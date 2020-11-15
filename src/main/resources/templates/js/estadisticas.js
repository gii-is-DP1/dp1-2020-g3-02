/**
 * 
 */

$(document).ready(function () {
	    // Create DataTable
	    var table = $('#estadisiticas').DataTable({
	        dom: '',
	        responsive: true,
	        
	    });
	        
	 
	    // Create the chart with initial data
	    var container = $('<div/>').insertBefore(table.table().container());
	 
	    var chart = Highcharts.chart(container[0], {
	        chart: {
	            type: 'column',
	        },
	        title: {
	            text: name(table),
	        },
	        xAxis: {
	            categories: [
	                '2020/07/01',
	                '2020/07/02',
	                '2020/07/05',
	                '2020/07/01',
	                '2020/07/02',
	                '2020/07/05'
	            ],
	            crosshair: true
	        },
	        yAxis: [{ // Primary yAxis
	            labels: {
	                format: '{value}',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            },
	            title: {
	                text: 'Estadísticas',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            },
	            opposite: true
	        }, { // Secondary yAxis
	        	max: 100,
	        	min: 0,
	            title: {
	                text: 'Porcentaje',
	                style: {
	                    color: Highcharts.getOptions().colors[0]
	                }
	            },
	            labels: {
	                format: '{value} %',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            }
	        }],
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:f}</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [
	            {	            	
	            	name: name1(table),
	            	data: chartData(table),
	            	yAxis: 0
	            	
	            },
	            {
	            	name: name2(table),
	            	data: chartData2(table),
	            	yAxis: 0
	            }, 
	            {
	                type: 'spline',
	                name: 'Porcentaje',
	                data: chartData3(table),
	                yAxis: 1,
	                tooltip: {
	                    valueSuffix: ' %'
	                },
	                marker: {
	                    lineWidth: 2,
	                    lineColor: Highcharts.getOptions().colors[3],
	                    fillColor: 'white'
	                }
	            }
	        ],
	    });
	    
	    table.on('draw', function () {
	        chart.series[0].setData(chartData(table));
	    });
	    
	});
		function name1(table){
			
			var coso =table.column(1).header();
			
			if($(coso).html() == 'S_A'){
				return 'Saques Acertados';
			}else{
				return 'Recepciones Acertadas';
			}
		}
		
		function name2(table){
			
			var coso =table.column(1).header();
			
			if($(coso).html() == 'S_A'){
				return 'Saques Totales';
			}else{
				return 'Recepciones Totales';
			}
		}
		
		function name(table){
			
			var coso =table.column(1).header();
			
			if($(coso).html() == 'S_A'){
				return 'SAQUES';
			}else{
				return 'RECEPCIONES';
			}
		}
		
	    function chartData(table) {
	        var counts = {};
	     	var names = [];
	     	var dato = [];
	        
	        // Count the number of entries for each position
	        table
	            .column(0, { search: 'applied' })
	            .data()
	            .each(function (val) {
	            	names.push( val);
	            });
	        
	        table
            .column(1, { search: 'applied' })
            .data()
            .each(function (val) {
            	dato.push( parseInt(val,10));
            });
	        
	         
	        
	        for (i = 0; i < names.length; i++){
	        	counts[names[i]] = dato[i];
	        }
	        
	     
	        // And map it to the format highcharts uses
	        return $.map(counts, function (val, key) {
	            return {
	                name: key,
	                y: val,
	            };
	        });
	    }
	    var max = 0;
	    function chartData2(table) {
	        var counts = {};
	     	var names = [];
	     	var dato = [];
	        
	        // Count the number of entries for each position
	        table
	            .column(0, { search: 'applied' })
	            .data()
	            .each(function (val) {
	            	names.push(val);
	            });
	        
	        table
            .column(2, { search: 'applied' })
            .data()
            .each(function (val) {
            	dato.push( parseInt(val,10));
            });
	        
	        for (i = 0; i < names.length; i++){
	        	counts[names[i]] = dato[i];
	        }
	        
	        max = Math.max(...dato);
	     
	        // And map it to the format highcharts uses
	        return $.map(counts, function (val, key) {
	            return {
	                name: key,
	                y: val,
	            };
	        });
	        
	        
	    }
	     
	    function chartData3(table) {
	        var counts = {};
	     	var names = [];
	     	var dato = [];
	        
	        // Count the number of entries for each position
	        table
	            .column(0, { search: 'applied' })
	            .data()
	            .each(function (val) {
	            	names.push( val);
	            });
	        
	        table
            .column(3, { search: 'applied' })
            .data()
            .each(function (val) {
            	dato.push( parseFloat(val));
            });
	        
	        for (i = 0; i < names.length; i++){
	        	counts[names[i]] = dato[i]*100;
	        }
	        
	       
	     
	        // And map it to the format highcharts uses
	        return $.map(counts, function (val, key) {
	            return {
	                name: key,
	                y: val,
	            };
	        });
	        
	        
	    }
	