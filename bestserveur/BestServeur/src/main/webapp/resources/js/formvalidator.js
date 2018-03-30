$(document).ready(function(){
	var nombat=$('#nombat');
	var nomzone=$('#nomzone');
	var nomsortie=$('#nomsortie');
	
	var email=$('#email');
	var password=$('#password');
	var confirmpassword=$('#confirmpassword');
	
	nombat.keyup(function(){
		var regexnombat = new RegExp("^[A-Za-z]+[0-9]*$");
	    if(regexnombat.test($(this).val())){ 
	         $(this).css({ 
	         borderColor : 'green',
	         color : 'green'
	         });	
	         $('#valider').prop('disabled', false);
	     }else{
	    	 $(this).css({
		            borderColor : 'red',
		            color : 'red'
		        });
	    	 $('#valider').prop('disabled', true);
	    }
	});
	
	nomzone.keyup(function(){
		var regexnombat = new RegExp("^[A-Za-z]+[0-9]*$");
	    if(regexnombat.test($(this).val())){ 
	         $(this).css({ 
	         borderColor : 'green',
	         color : 'green'
	         });
	         $('#valider').prop('disabled', false);
	     }else{
	    	 $(this).css({
		            borderColor : 'red',
		            color : 'red'
		        });
	    	 $('#valider').prop('disabled', true);
	    }
	});
	
	nomsortie.keyup(function(){
		var regexnombat = new RegExp("^[A-Za-z]+[0-9]*$");
	    if(regexnombat.test($(this).val())){ 
	         $(this).css({ 
	         borderColor : 'green',
	         color : 'green'
	         });
	         $('#valider').prop('disabled', false);
	     }else{
	    	 $(this).css({
		            borderColor : 'red',
		            color : 'red'
		        });
	    	 $('#valider').prop('disabled', true);
		    }
		});
	password.keyup(function(){
		var regexnombat = new RegExp("^[A-Za-z]+[0-9]*$");
	    if(regexnombat.test($(this).val())){ 
	         $(this).css({ 
	         borderColor : 'green',
	         color : 'green'
	         });
	         $('#valider').prop('disabled', false);
	     }else{
	    	 $(this).css({
		            borderColor : 'red',
		            color : 'red'
		        });
	    	 $('#valider').prop('disabled', true);
		    }
		});
	

	confirmpassword.keyup(function(){
		var value = password.val();
	    if(value===$(this).val()){ 
	         $(this).css({ 
	         borderColor : 'green',
	         color : 'green'
	         });
	         $('#valider').prop('disabled', false);
	     }else{
	    	 $(this).css({
		            borderColor : 'red',
		            color : 'red'
		        });
	    	 $('#valider').prop('disabled', true);
		    }
		});
		
	window.setTimeout(function() {
        $(".alert").fadeTo(500, 0).slideUp(500, function(){
            $(this).remove(); 
        });
    }, 4000);

});

