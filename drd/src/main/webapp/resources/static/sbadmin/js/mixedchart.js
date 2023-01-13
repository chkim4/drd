//import eachWeekOfInterval from 'date-fns/eachWeekOfInterval'
    //import { format, formatDistance, formatRelative, subDays } from 'date-fns'

    //format(new Date(), "'Today is a' eeee")
    //=> "Today is a Wednesday"

    //formatDistance(subDays(new Date(), 3), new Date(), { addSuffix: true })
    //=> "3 days ago"

    //formatRelative(subDays(new Date(), 3), new Date())
    //=> "last Friday at 7:26 p.m."
    /* const dateFns = require("date-fns"); */

    var date1 = new Date("2021-10-20");
    console.log(date1);

    /* var date2 = add(date1, { days: 1 });
    console.log(date2);  */
    const labels = dateFns.eachDay(
    				new Date(2021, 08, 25),
    				new Date(2021, 08, 31)
    );
   	const dates = ['2021-08-25', '2021-08-26', '2021-08-27', '2021-08-28', '2021-08-29', '2021-08-30', '2021-08-31']
   	const datapoints = [1,2,3,4,5,6,7]
    // setup 
    const data = {
      labels: labels,
      datasets: [{
        label: '무산소',
        data: datapoints,
        backgroundColor: [
          'rgba(255, 26, 104, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(0, 0, 0, 0.2)'
        ],
        borderColor: [
          'rgba(255, 26, 104, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
          'rgba(0, 0, 0, 1)'
        ],
        borderWidth: 1,
        order:2,
        stack:'Stack 0'
      },
      {
          label: '유산소',
          data: [18, 12, 6, 9, 12, 3, 9],
          backgroundColor: [
            'rgba(255, 26, 104, 0.2)',
            
          ],
          borderColor: [
            'rgba(255, 26, 104, 1)',
           
          ],
          borderWidth: 1,
          order:2,
          stack:'Stack 0'
        },/*
        {
            label: 'Weekly Sales',
            data: datapoints,
            backgroundColor: [
              'rgba(255, 26, 104, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(0, 0, 0, 0.2)'
            ],
            borderColor: [
              'rgba(255, 26, 104, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
              'rgba(0, 0, 0, 1)'
            ],
            borderWidth: 1,
            order:2,
            stack:'Stack 1'
          },
          {
              label: 'Weekly Sales',
              data: [18, 12, 6, 9, 12, 3, 9],
              backgroundColor: [
                'rgba(255, 26, 104, 0.2)',
                
              ],
              borderColor: [
                'rgba(255, 26, 104, 1)',
               
              ],
              borderWidth: 1,
              order:2,
              stack:'Stack 1'
            },*/
        {
            label: '목표시간',
            data: [16, 16, 16, 16, 16, 16, 16],
            backgroundColor:'rgba(255, 26, 104, 0.2)',
            borderColor:'rgba(255, 26, 104, 1)',
            tension: 0.4,
            type:'line',
            order:1
          }]
    };

    // config 
    const config = {
      type: 'bar',
      data,
      options: {
    	  plugins:{
    		  tooltip:{
    			  enabled:false
    		  }
    	  },
        scales: {
         x:{
        		stacked:true,
        		offset:true,
				type:'time',
				time:{
					unit:'day',//yyyy-mm-dd//yyyy-mm
					/* displayFormats:{
						month:'yyyy-MM'
					} */
				}
				
        },
          y: {
            beginAtZero: true,
            stacked:true
          }
        }
      }
    };

    // render init block
    const myChart = new Chart(
      document.getElementById('myChart'),
      config
    );
     function filterData(){
    	const dates2 = [...dates];
    	console.log(dates2);
    	const startdate = document.getElementById('startdate');
    	const enddate = document.getElementById('enddate');
    	//get index number in array
    	const indexstartdate = dates2.indexOf(startdate.value);
    	const indexenddate = dates2.indexOf(enddate.value);
    	console.log(indexstartdate);
    	//slice the array showing selected section / slice
    	const filterDate = dates2.slice(indexstartdate, indexenddate + 1);
    	//replace the labels
    	myChart.config.data.labels = filterDate;
    	myChart.update();
    }
    