/**
 * 
 */

function getPredictions() {
	console.log("called");
	var formData = $("#contact").serializeArray();
	console.log(formData);
	$.ajax({
        url: "http://localhost:8080/dynamic/entry-point/createPlayer",
        type: 'POST',
        dataType: 'json',
        data: formData,
        contentType: 'application/json',
        mimeType: 'application/json',
 
        success: function (data) {
			console.log(data.playerFirstName);
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}



//var ws = new WebSocket("ws://localhost:8080/");
//
//ws.onopen = function() {
//	// alert("Opened!");
//	ws.send("Hello Server");
//};
//
//ws.onmessage = function(evt) {
//	if (evt.data.includes("results")) {
//		level1results = evt.data.substring(evt.data.lastIndexOf("[") + 1,
//				evt.data.indexOf(","));
//		level2results = evt.data.substring(evt.data.indexOf(",") + 1, evt.data
//				.lastIndexOf(","));
//		level3results = evt.data.substring(evt.data.lastIndexOf(",") + 1,
//				evt.data.lastIndexOf("]"));
//		showResults(level1results, level2results, level3results);
//	}
//
//	else if (evt.data.includes("predictions")) {
//		console.log(evt.data);
//		level1prediction = evt.data.substring(evt.data.lastIndexOf("[") + 1,
//				evt.data.indexOf(","));
//		level2prediction = evt.data.substring(evt.data.indexOf(",") + 1,
//				evt.data.lastIndexOf(","));
//		level3prediction = evt.data.substring(evt.data.lastIndexOf(",") + 1,
//				evt.data.lastIndexOf("]"));
//		showPredictions(level1prediction, level1prediction, level1prediction);
//	}
//
//};
//
//ws.onclose = function() {
//	// alert("Closed!");
//};
//
//ws.onerror = function(err) {
//	alert("Error: " + err);
//};

//function myFunction() { // Declare a function
//	ws.send("Hello info");
//	var test = $('form').serialize();
//	var newTest = "details:" + test;
//	ws.send(newTest);
//	ws.send("firstprediction:");
//}
//
//function addPlayer() { // Declare a function
//	var test = $('form').serialize();
//	var newTest = "details:" + test;
//	ws.send(newTest);
//	document.getElementById("contact").reset();
//}
//
//function startGame() { // Declare a function
//	ws.send("startgame:");
//	document.getElementById("start-game").disabled = true;
//	document.getElementById("start-game").value = "Playing"
//}
