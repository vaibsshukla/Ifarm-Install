;(function () {
var setFormValue = function(result){
    $.each(result, function(key, data){
        $('input[name="'+key+'"]').val(data)
    })
};

var get_ifarm_config = function(){
    
            $.ajax({
            type  :   "get" ,
            contentType: "application/json",
            url  :   "http://localhost:8080/getConfigs",
            dataType :   "json",
            statusCode : {
            	   400: function(){
            	   },
            	   202: function(){
            	   },
            	   201: function(){
            	   }
            },
            success : function(result){
                console.log(result.data);
                setFormValue(result.data)
            },
            error : function(e){
            },
            complete : function(e, xhr, settings){
            }
         	   
         }); 

}

var save_ifarm_config = function(){
    $("#ifarm-config-submit").on('click',function(event){    
        var formRawData = new FormData(document.getElementById('ifarm-config-form'));
        var formDataJson = {}
        formRawData.forEach(function(value, key){
           formDataJson[key] = value; 
        });
        
        $.ajax({
            type  :   "post" ,
            contentType: "application/json",
            url  :   "http://localhost:8080/saveConfigs",
            data :	JSON.stringify(formDataJson),
            dataType :   "json",
            statusCode : {
            	   400: function(){
            	   },
            	   202: function(){
            	   },
            	   201: function(){
            	   }
            },
            success : function(result){
        		},
            error : function(e){
            },
            complete : function(e, xhr, settings){
            }
         	   
         }); 

        event.preventDefault();
    });
};


var save_ambari_credentials_validation = function(event){

	var hostname=$("#hostname").val();
    var port=$("#port").val();
    var re= "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    var regexp = new RegExp(re);
    console.log(regexp.test(hostname));

    $(".error").remove();
    if(!$.isNumeric(port))
    {
        $("#port").after('<span class="error">Invalid port</span>');
        return false;
    }
    else if(port<80||port>65335)
    {
        $("#port").after('<span class="error">Invalid port</span>');
        return false;
    }
    else if(!regexp.test(hostname))
    {
        $("#hostname").after('<span class="error">Invalid hostname</span>'); 
        return false;
    }  
    else
    {
        $(".error").remove();
        return true;
    }

};

var save_ambari_credentials = function(){
	$(document).on('click',"#save_ambari_button",function(event){    
		event.preventDefault();
    	console.log("Hello");
    	/* validate ambari credentials details */
    	if(save_ambari_credentials_validation() != true){
    		return;
    	}
    	

    	var formRawData = new FormData(document.getElementById('formAmbari'));
        var formDataJson = {}
        formRawData.forEach(function(value, key){
           formDataJson[key] = value; 
        });
        
        $.ajax({
            type  :   "post" ,
            contentType: "application/json",
            url  :   "http://localhost:8080/saveAmbariDetail",
            data :	JSON.stringify(formDataJson),
            dataType :   "json",
            statusCode : {
            	   400: function(){
            	   },
            	   202: function(){
            	   },
            	   201: function(){
            	   }
            },
            success : function(result){
            	console.log(result)
        		},
            error : function(e){
            },
            complete : function(e, xhr, settings){
            }
         	   
         }); 

        event.preventDefault();
    });
	
};

$(function(){
        save_ifarm_config();
        get_ifarm_config();
        save_ambari_credentials();
	});
}());
