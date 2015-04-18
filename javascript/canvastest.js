
function convertCanvasToImage(canvas) {
	var image = new Image();
	image.src = canvas.toDataURL("image/png");
	return image;
}

saveImage = function(){
	var canvas = $("#myCanvas").get(0);
	//console.log(canvas.toDataURL("image/png"));
	theJavaFunction1(canvas.toDataURL("image/png"));
	//return canvas.toDataURL("image/png");
}

$(document).ready( function(){
	var canvas = $("#myCanvas").get(0);
	var ctx = canvas.getContext("2d");
	ctx.beginPath();
	ctx.arc(95,50,40,0,2*Math.PI);
	ctx.stroke();

	$("#saveimage").click(saveImage);
	//console.log(c.toDataURL("image/png"));
	//document.getElementById("placeholder").appendChild(convertCanvasToImage(c));
	
});


