/*
*	주간기록조회 막대그래프: 주간 운동시간(유산소, 무산소, 목표)
*/
 // 막대그래프 setup 
    const dataBar = {
      labels: dateList,
      datasets: [{
        label: '무산소',
        data: fitnessList,
        backgroundColor: [
        'rgba(54, 162, 235, 0.2)'
         /* 'rgba(255, 26, 104, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(0, 0, 0, 0.2)'*/
        ],
        borderColor: [
        'rgba(54, 162, 235, 0.8)',
          /*'rgba(255, 26, 104, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
          'rgba(0, 0, 0, 1)'*/
        ],
        borderWidth: 1,
        order:2,
        stack:'Stack 0'
      },
      {
          label: '유산소',
          data: cardioList,
          backgroundColor: [
          'rgba(255, 206, 86, 0.2)',
          /*  'rgba(255, 26, 104, 0.2)',*/
            
          ],
          borderColor: [
          'rgba(255, 206, 86, 0.8)',
            /*'rgba(255, 26, 104, 1)',*/
           
          ],
          borderWidth: 1,
          order:2,
          stack:'Stack 0'
        },
        {
            label: '목표시간',
            data: timeList,
            backgroundColor:'rgba(75, 192, 192, 0.2)',
            borderColor:'rgba(75, 192, 192, 0.8)',
            tension: 0.4,
            type:'line',
            order:1
          }]
    };
    
   
   
    //막대그래프 config 
    const configBar = {
      type: 'bar',
      data:dataBar,
      options: {
      	plugins:{
    		  tooltip:{
    			  enabled:true
    		  }
    	  },
        scales: {
         x:{
        		stacked:true,
        		offset:true,
				/*type:'time',
				time:{
					unit:'week',//yyyy-mm-dd//yyyy-mm
					 displayFormats:{
						//week:'yyyy-MM'
					}
				}
				*/
        },
          y: {
            beginAtZero: true,
            stacked:true,
            title:{
            	display:true,
            	text:'운동시간(분)'
            }
          }
        }
      }
    };

    //막대그래프 render init block
    const myChartBar = new Chart(
      document.getElementById('myChartBar'),
      configBar
    );
 /*
 *	도넛그래프 안 프로틴, 칼로리 데이터
 */  
const stackedText = {
	id:'stackedText',
	afterDatasetsDraw(chart, args, options){
		const{ctx, chartArea:{top, bottom, left, right, width, height}} = chart;
		/*console.log(chart);
		console.log(chart.data.datasets[0].data[1]);
		console.log(chart.data.datasets[0].data[0]);*/
		ctx.save();
		const fontHeight = 50;
		const halvefontHeight = fontHeight - 10;
		ctx.font = `bolder ${fontHeight}px Arial`;
		ctx.fillStyle = 'rgba(225,26,104,1)';
		ctx.textAlign = 'center';
		ctx.fillText(chart.data.datasets[0].data[0] + 'g', width / 2, height / 2 + top);
		//console.log(chart.data.datasets[0].data[0]);
		
		ctx.restore();
		ctx.fillStyle = `${fontHeight}px Arial`;
		ctx.textAlign = 'center';
		ctx.fillText(`이번주 목표량 ${goal[0]}g`, width/2,  height / 2 + top + 40);
		ctx.restore();
		
		
	}
}
const stackedText2 = {
	id:'stackedText2',
	afterDatasetsDraw(chart, args, options){
		const{ctx, chartArea:{top, bottom, left, right, width, height}} = chart;
		console.log(chart);
		/*console.log(chart.data.datasets[0].data[1]);
		console.log(chart.data.datasets[0].data[0]);*/
		ctx.save();
		const fontHeight = 50;
		const halvefontHeight = fontHeight - 10;
		ctx.font = `bolder ${fontHeight}px Arial`;
		ctx.fillStyle = 'rgba(225,26,104,1)';
		ctx.textAlign = 'center';
		ctx.fillText(chart.data.datasets[0].data[0] + 'cal', width / 2, height / 2 + top);
		/*console.log(chart.data.datasets[0].data[0]);*/
		
		ctx.restore();
		ctx.fillStyle = `${fontHeight}px Arial`;
		ctx.textAlign = 'center';
		ctx.fillText(`이번주 목표량 ${goal[1]}cal`, width/2,  height / 2 + top + 40);
		ctx.restore();
		
		
	}
}
//단백질 도넛그래프 config
const configDoughnut = {
            	      type: 'doughnut',
            	      data: {
            	    	  labels: [
            	    		    '섭취단백질',
            	    		    '잔여'
            	    		 
            	    		  ],
            	    		  datasets: [{
            	    		    label: 'My First Dataset',
            	    		    data:  protein,
            	    		    backgroundColor: [
            	    		      'rgb(255, 99, 132)',
            	    		      'transparent'
            	    		      /*'rgb(54, 162, 235)'
            	    		      'rgb(255, 205, 86)'*/
            	    		    ],
            	    		    hoverOffset: 4,
            	    		    cutout:'90%',
            	    		    borderRadius:20
            	    		  }]
            	    	},
            	      options:{
            	     	plugins:{
            	     		legend:{
            	      			display:false,
            	      		}
            	     	},
            	      	tooltip:{
            	      		enabled:false
            	      	}
            	      },
            	      plugins:
            	    	  [stackedText]
   		 };
   		 console.log(protein);
 //도넛그래프 render init block
    const myChartDoughnut =  new Chart(
       document.getElementById('myChartDoughnut'),
      configDoughnut
    );
//칼로리 도넛그래프    
  const configDoughnut2 =  {
          	      type: 'doughnut',
          	      data: {
          	    	  labels: [
          	    		    '섭취칼로리',
          	    		    '잔여량'
          	    		 
          	    		  ],
          	    		  datasets: [{
          	    		    label: 'My First Dataset',
          	    		    data:  calory,
          	    		    backgroundColor: [
          	    		      'rgb(255, 99, 132)',
          	    		      'transparent'
          	    		      /*'rgb(54, 162, 235)'
          	    		      'rgb(255, 205, 86)'*/
          	    		    ],
          	    		    hoverOffset: 4,
          	    		    cutout:'90%',
          	    		    borderRadius:20
          	    		  }]
          	    	},
          	      options:{
          	     	plugins:{
          	     		legend:{
          	      			display:false,
          	      		}
          	     	},
          	      	tooltip:{
          	      		enabled:false
          	      	}
          	      },
          	      plugins:
          	    	  [stackedText2]
 		 };
 		 
const myChartDoughnut2 = new Chart(
      document.getElementById('myChartDoughnut2'),
 		 configDoughnut2
 		 );
    