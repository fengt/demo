jQuery.validator.setDefaults({
    	  errorClass:"help-block has-error",
    	  highlight:function(element,errorClass){
    		  $(element).parent().addClass(errorClass);
    	  },
    	  unhighlight:function(element,errorClass){
    		  $(element).parent().removeClass(errorClass);
    	  }
    	});
