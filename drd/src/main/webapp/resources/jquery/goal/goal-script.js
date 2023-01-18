const stackedText = {
    id:'stackedText',
    afterDatasetsDraw(chart, args, options){
        const {ctx, chartArea:{top, bottom, left, right, width, height}} = chart;
        
        ctx.save();
        const fontHeight = 50;
        const halvefontHeight = fontHeight - 10;
        ctx.font = `bolder ${fontHeight}px Arial`;
        ctx.fillStyle = 'rgba(225,26,104,1)';
        ctx.textAlign = 'center';
        ctx.fillText(chart.data.datasets[0].data[0] + 'g', width / 2, height / 2 + top);
        console.log(chart.data.datasets[0].data[0]);
        
        ctx.restore();
        ctx.fillStyle = `${fontHeight}px Arial`;
        ctx.textAlign = 'center';
        ctx.fillText(`이번주 목표량 ${goal[0]}g`, width/2,  height / 2 + top + 40);
        ctx.restore();
	}
}