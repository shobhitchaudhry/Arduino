var name; //note: haven't done anything to retrive this value
var age; //question 2
var game; //question 3
var shopping; //question 4
var sports; //question 5
var drive; //question 6
var caffeine; //question 7



$('.dot-nav a').on('click', function() {

  var scrollAnchor = $(this).attr('data-scroll'),
    scrollPoint = $('section[data-anchor="' + scrollAnchor + '"]').offset().top - 28;

  $('.main').animate({
    scrollTop: scrollPoint
  }, 500);

  return false;

})

$(window).scroll(function() {
  var windscroll = $(window).scrollTop();
  if (windscroll >= 100) {
        //console.log('windscroll: '  + windscroll);
    $('.dot-nav').addClass('fixed');
    $('.main section').each(function(i) {
        var test =$(this).position().top;
        //console.log('test: '  + test);
      if ($(this).position().top <= windscroll) {
        //console.log("scroll topppp");
        $('.dot-nav a.active').removeClass('active');
        $('.dot-nav a').eq(i).addClass('active');
      }
    });

  } else {
    $('dot-nav').removeClass('fixed');
    $('dot-nav a.active').removeClass('active');
    $('dot-nav a:first').addClass('active');
  }

}).scroll();


//section size
$(function(){
	 var fullHeight = $('section');
    var windowH = $(window).height();
    var wrapperH = fullHeight.height();
    if(windowH > wrapperH) {                            
        fullHeight.css({'min-height':($(window).height())+'px'});
    }                                                                               
    $(window).resize(function(){
        var windowH = $(window).height();
        var wrapperH = fullHeight.height();
        var differenceH = windowH - wrapperH;
        var newH = wrapperH + differenceH;
        //var truecontentH = $('#truecontent').height();
        //if(windowH > truecontentH) {
            fullHeight.css('min-height', (newH)+'px');
        //}

    })          
});

$('.q2Click').click(function(e){
    console.log ("colorchanging")
    $('.q2Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

$('.q3Click').click(function(e){
    console.log ("colorchanging")
    $('.q3Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

$('.q4Click').click(function(e){
    console.log ("colorchanging")
    $('.q4Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

$('.q5Click').click(function(e){
    console.log ("colorchanging")
    $('.q5Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

$('.q6Click').click(function(e){
    console.log ("colorchanging")
    $('.q6Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

$('.q7Click').click(function(e){
    console.log ("colorchanging")
    $('.q7Click').css("background", "#348b26");
    $(this).css("background", "#0f4b3c"); //the chosen answer
});

//get function
window.params = function(){
    var params = {};
    var param_array = window.location.href.split('?')[1].split('&');
    for(var i in param_array){
        x = param_array[i].split('=');
        params[x[0]] = x[1];
    }
    return params;
}();

//hides the player of players bar in form.html
if(window.params.player == '1') {
    console.log("1player");
   
   hideP2();
   hideP3();
   hideP4();

   $("#number-players").width("160px");

}

if(window.params.player == 2) {
    console.log("2players");
    
    hideP3();
    hideP4();

    $("#number-players").width("322px");
}

if(window.params.player == '3') {
    console.log("3players");
    hideP4();

    $("#number-players").width("484px");
}

if(window.params.player == '4') {
    console.log("4players");
    //doStuff();
}

function hideP2() {
    //document.getElementById("p4").display = "none";
    $("#p2").hide();
}

function hideP3() {
    //document.getElementById("p4").display = "none";
    $("#p3").hide();
}

function hideP4() {
    //document.getElementById("p4").display = "none";
    $("#p4").hide();
}


//function for form 
 function formSub() {
    console.log("hello")
    if(window.params.player == 1) {
        //make form submit
        console.log("submit");
        window.location = "confirm.html";
    } 
    //2 players form
    else if (window.params.player == 2 && window.params.form == 1){
        //go to next page
        window.location = "form1.html?player=2&form=2";
    } 
    else if (window.params.player == 2 && window.params.form == 2) {
        //submit data and go to tutorial page
        window.location = "confirm.html";
    }
    // 3 players form
    else if (window.params.player == 3 && window.params.form == 1) {
        window.location = "form1.html?player=3&form=2";
    }
    else if (window.params.player == 3 && window.params.form == 2) {
        window.location = "form1.html?player=3&form=3";
    }
    else if (window.params.player == 3 && window.params.form == 3) {
        //submit data and go to tutorial page
        window.location = "confirm.html";
    }
    // 4 players form
    else if (window.params.player == 4 && window.params.form == 1) {
        window.location = "form1.html?player=4&form=2";
    }
    else if (window.params.player == 4 && window.params.form == 2) {
        window.location = "form1.html?player=4&form=3";
    }
    else if (window.params.player == 4 && window.params.form == 3) {
        window.location = "form1.html?player=4&form=4";
    }
    else if (window.params.player == 4 && window.params.form == 4) {
        //submit data and go to tutorial page
        window.location = "confirm.html";
    }
 }



 //change background color of which player
    //2players
 if(window.params.player == 2 && window.params.form == 2) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#348b26");

 }

    //3players
if(window.params.player == 3 && window.params.form == 2) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#348b26");
    $("#p3").css("background-color","#F0AB00");

}

if(window.params.player == 3 && window.params.form == 3) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#F0AB00");
    $("#p3").css("background-color","#348b26");

}

    //4players
if(window.params.player == 4 && window.params.form == 2) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#348b26");
    $("#p3").css("background-color","#F0AB00");

}

if(window.params.player == 4 && window.params.form == 3) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#F0AB00");
    $("#p3").css("background-color","#348b26");
}

if(window.params.player == 4 && window.params.form == 4) {
    console.log("changecolor");
    $("#p1").css("background-color","#F0AB00");
    $("#p2").css("background-color","#F0AB00");
    $("#p3").css("background-color","#F0AB00");
    $("#p4").css("background-color","#348b26");
}


//when users click on the arrow it will scroll to the next question
function arrow1 () {
        $('html,body').animate({
        scrollTop: $("#two").offset().top},
        'slow');
} 

function arrow2 () {
        $('html,body').animate({
        scrollTop: $("#three").offset().top},
        'slow');
}   

function arrow3 () {
        $('html,body').animate({
        scrollTop: $("#four").offset().top},
        'slow');
}  

function arrow4 () {
        $('html,body').animate({
        scrollTop: $("#five").offset().top},
        'slow');
}  

function arrow5 () {
        $('html,body').animate({
        scrollTop: $("#six").offset().top},
        'slow');
}  

function arrow6 () {
        $('html,body').animate({
        scrollTop: $("#seven").offset().top},
        'slow');
}  


//Hide functions for players score (depends on number of players)
function hideP1Score (){
    $("#player1").hide();
}   

function hideP2Score (){
    $("#player2").hide();
}   

function hideP3Score (){
    $("#player3").hide();
}   

function hideP4Score (){
    $("#player4").hide();
}   



//hide play button and show game in progress

function hidepButton(){
    $("#pButton").hide();
    $("#gameProText").show();
}


//functions to retrieve values from each answer in the questionnaire
    //question 2
// $("#f").click(function() {
//   myFunc($(this));
// })

$("input").click(function() {
    //gets data of Q2 answer
    if($(this).hasClass("q2Click")){

        age=$(this).attr("name");
        console.log(age);
    }

    //get data of Q3 answer
    if($(this).hasClass("q3Click")){

        game=$(this).attr("name");
        console.log(game);
    }

    //get data of Q4 answer
    if($(this).hasClass("q4Click")){

        shopping=$(this).attr("name");
        console.log(shopping);
    }

     //get data of Q5 answer
    if($(this).hasClass("q5Click")){

        sports=$(this).attr("name");
        console.log(sports);
    }

     //get data of Q6 answer
    if($(this).hasClass("q6Click")){

        drive=$(this).attr("name");
        console.log(drive);
    }

     //get data of Q7 answer
    if($(this).hasClass("q7Click")){

        caffeine=$(this).attr("name");
        console.log(caffeine);
    }
});

//form validation
function validateForm() {
    if (age == null) {
        alert("Please fill out all questions");
        return false;
    } else if (game == null) {
        alert("Please fill out all questions");
        return false;
    } else if (shopping == null) {
        alert("Please fill out all questions");
        return false;
    } else if (sports == null) {
        alert("Please fill out all questions");
        return false;
    } else if (drive == null) {
        alert("Please fill out all questions");
        return false;
    } else if (caffeine == null) {
        alert("Please fill out all questions");
        return false;
    } else {
        formSub();
    }
}

//when users click on submit
function clickSubmit() {

}

