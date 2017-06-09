/**
 * 
 */
var ws = new WebSocket("ws://localhost:8080/");
var level1prediction,level2prediction,level3prediction,level1results,level2results,level3results;

ws.onopen = function() {
 //   alert("Opened!");
    ws.send("Hello Server");
};

ws.onmessage = function (evt) {
	if(evt.data.includes("results")){
		level1results=evt.data.substring(evt.data.lastIndexOf("[")+1,evt.data.indexOf(","));
		level2results=evt.data.substring(evt.data.indexOf(",")+1,evt.data.lastIndexOf(","));
		level3results=evt.data.substring(evt.data.lastIndexOf(",")+1,evt.data.lastIndexOf("]"));
		showResults(level1results,level2results,level3results);
	}
	
	else if(evt.data.includes("predictions")){
		console.log(evt.data);
		level1prediction=evt.data.substring(evt.data.lastIndexOf("[")+1,evt.data.indexOf(","));
		level2prediction=evt.data.substring(evt.data.indexOf(",")+1,evt.data.lastIndexOf(","));
		level3prediction=evt.data.substring(evt.data.lastIndexOf(",")+1,evt.data.lastIndexOf("]"));
		showPredictions(level1prediction,level1prediction,level1prediction);
	}
	
};

ws.onclose = function() {
   // alert("Closed!");
};

ws.onerror = function(err) {
    alert("Error: " + err);
};

function myFunction() { // Declare a function
	 ws.send("Hello info");
	 var test = $('form').serialize();
	 var newTest="details:"+test;
	 ws.send(newTest);
	 ws.send("firstprediction:");
}

function addPlayer() { // Declare a function
	 var test = $('form').serialize();
	 var newTest="details:"+test;
	 ws.send(newTest);
}

function startGame() { // Declare a function
		 ws.send("startgame:");
}

function showResults(level1, level2, level3) { // Declare a function
	//	alert(data);
	document.getElementById("results-level1").innerHTML = level1;
	document.getElementById("results-level2").innerHTML = level2;
	document.getElementById("results-level3").innerHTML = level3;
	$("#results").show();
	$('html, body').animate({
		scrollTop : $('#predictions').offset().top + 'px'
	}, 'fast');
}
