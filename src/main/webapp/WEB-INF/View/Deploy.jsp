<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>IFarm Ditributed Healthcare</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/metisMenu.min.css" rel="stylesheet">
        <link href="/css/startmin.css" rel="stylesheet">
        <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/main.css" >
        <link rel="stylesheet" href="/css/jquery.steps.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            input {
  padding: 10px;
  width: 90%;
  margin-bottom: 10px;
  height: 45px;
  font-size: 17px;
  border-radius: 5px;
}

.error {
    color: red;
}
            
input.error {
     display: inline-block;
}

            .modal-tall .modal-dialog {
              height: 80%;
            }
            .modal-tall .modal-content {
              height: 100%;
            }
            .modal-tall .modal-body {
              height: 80%;
            }
            .btn-primary{
                background-color: #181818;
                border-color: #ffffff;
            }
            
            .loader {
              border: 16px solid #3498db;
              border-radius: 50%;
              border-top: 16px solid blue;
              position: fixed;
              bottom: 50%;
              right: 40%;
              width: 120px;
              height: 120px;
              -webkit-animation: spin 700ms linear infinite; /* Safari */
              animation: spin 700ms linear infinite;
            }

            /* Safari */
            @-webkit-keyframes spin {
              0% { -webkit-transform: rotate(0deg); }
              100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
              0% { transform: rotate(0deg); }
              100% { transform: rotate(360deg); }
            }
            
        </style>
        
    </head>
    
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">IFarm</a>
                </div>

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <ul class="nav navbar-nav navbar-left navbar-top-links">
                    <li><a href="#"><i class="fa fa-home fa-fw"></i> Website</a></li>
                </ul>

                <ul class="nav navbar-right navbar-top-links">
                    <li class="dropdown navbar-inverse">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Config</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> Admin <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- /.navbar-top-links -->           
            </nav>
            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Required Configuration for Ifarm </h1>
                            
                            <div>
                                    <form id="example-advanced-form" action="#">
            
        <h3>HDFS</h3>
            <fieldset>
                <input type="text" placeholder="HDFS Name Node" class="required" name="hdfsNameNode">
                <input type="text" placeholder="Hadoop Config Location" class="required" name="hadoopConfigLoc"> 
                <input type="text" placeholder="HDFS Proxy User" name="hdfsProxyUser">
            </fieldset>  
        
         <h3>Kafka</h3>
                <fieldset>
                    <input type="text" placeholder="Kafka Broker Host" class="required" name="kafkaBrokerHost">
                     <input type="text" placeholder="Kafka Broker Port" class="required" name="kafkaBrokerPort">
                </fieldset>

                <h3>Solr</h3>
                <fieldset>
                    <input type="text" placeholder="Solr Host" class="required" name="solrHost">
                     <input type="text" placeholder="Solr Port" class="required" name="solrPort">
                     <input type="text" placeholder="Solr Username" name="solrUsername">
                     <input type="text" placeholder="Solr Password" name="solrPassword"> 
                </fieldset>

            <h3>MySQL</h3>
                <fieldset>
                    <input type="text" placeholder="MySQL Host" class="required" name="mysqlHost">
                     <input type="text" placeholder="MySQL Port" class="required" name="mysqlPort">
                     <input type="text" placeholder="MySQL Username" name="mysqlUsername"> 
                     <input type="text" placeholder="MySQL Driver Location" name="mysqlDriverLocation"> 
                     <input placeholder="MySQL Password" name="mysqlPassword"> 
                </fieldset>
                
            <h3>Mongo</h3>
                <fieldset>
                     <input type="text" placeholder="Mongo Hostname" class="required" name="mongoHostName">
                     <input type="text" placeholder="Mongo Port" class="required" name="mongoPort">
                     <input type="text" placeholder="Mongo Username" name="mongoUsername">
                     <input type="text" placeholder="Mongo Password" name="mongoPassword">             
                </fieldset>
                
            <h3>Spark2</h3>
                <fieldset>
                     <input type="text" placeholder="Spark2Host" class="required" name="sparkAppDir"> 
                </fieldset>
                
            <h3>Livy</h3>
                <fieldset>
                      <input type="text" placeholder="Livy Host" class="required" name="livyHost"> 
                     <input type="text" placeholder="Livy Port" class="required" name="livyPort">
                     <input type="text" placeholder="Livy Username" name="livyUsername">
                     <input type="text" placeholder="Livy Password" name="livyPassword"> 
                </fieldset>
                
            <h3>Neo4J</h3>
                <fieldset>
                     <input type="text" placeholder="Neo4J Host" class="required" name="neo4jHost"> 
                     <input type="text" placeholder="Neo4J Port" class="required" name="neo4jPort">
                     <input type="text" placeholder="Neo4J Username" name="neo4jUsername">
                     <input type="text" placeholder="Neo4J Password" name="neo4jPassword"> 
                </fieldset>
                
            <h3>IFarm</h3>
                <fieldset>
                     <input type="text" placeholder="IFarm Data Hostname" class="required" name="ifarmDataHost"> 
                     <input type="text" placeholder="IFarm Data Port" class="required" name="ifarmDataPort">
                </fieldset>

            <h3>PackServer</h3>
                <fieldset>
                     <input type="text" placeholder="PackServer Host" class="required" name="packserverHost"> 
                     <input type="text" placeholder="PackServer Port" class="required" name="packserverPort"> 
                </fieldset>
                
            <h3>Nifi</h3>
                <fieldset>
                     <input type="text" placeholder="Nifi Host" class="required" name="nifiHost">
                     <input type="text" placeholder="Nifi Port" class="required" name="nifiPort"> 
                     <input type="text" placeholder="Nifi Username" name="nifiUsername"> 
                     <input type="text" placeholder="Nifi Password" name="nifiPassword"> 
                </fieldset>
            
        </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script src="/js/jquery.min.js"></script>
        
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/metisMenu.min.js"></script>
        <script src="/js/startmin.js"></script>
 
        <script type="text/javascript" src="/js/action.js"></script>
        <script src="/js/modernizr-2.6.2.min.js"></script>
        <script src="/js/jquery.cookie-1.3.1.js"></script>
        <script src="/js/jquery.steps.js"></script>
        <script src="/js/jquery.validate.js"></script>
        
        <script>
    
        var form = $("#example-advanced-form").show();

        form.steps({
            headerTag: "h3",
            bodyTag: "fieldset",
            transitionEffect: "slideLeft",
            stepsOrientation: "vertical",
            onStepChanging: function (event, currentIndex, newIndex)
            {
                if (currentIndex > newIndex)
                {
                    return true;
                }
              if (currentIndex < newIndex)
                {
                    // To remove error styles
                    form.find(".body:eq(" + newIndex + ") label.error").remove();
                    form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
                } 
                form.validate().settings.ignore = ":disabled,:hidden";
                return form.valid();
            },
            onStepChanged: function (event, currentIndex, priorIndex)
            {
                if (currentIndex > newIndex)
                {
                    form.steps("next");
                }

                 if (currentIndex === 1 && priorIndex === 2)
                {
                    form.steps("previous");
                }

            },
            onFinishing: function (event, currentIndex)
            {
                form.validate().settings.ignore = ":disabled";
                return form.valid();
            },
            onFinished: function (event, currentIndex)
            {   
                var t=form.find(".body").find("input");
                var str='';
                for(var i=0; i<t.length; i++)
                {
                    var k=t.eq(i).attr('name');
                    var v=t.eq(i).val();
                    if(i==t.length-1)
                        {
                        str=str+'"'+k+'"'+' : '+'"'+v+'"';
                        break;
                        }
                   str=str+'"'+k+'"'+' : '+'"'+v+'"'+' , ';
                }
                str='{ '+str+' }';
                //console.log(str);

                var formRawData = new FormData(document.getElementById('example-advanced-form'));
                var formDataJson = {}
                formRawData.forEach(function(value, key){
                   formDataJson[key] = value; 
                });
                console.log(formDataJson)
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
              
            }
        }).validate({
            errorPlacement: function errorPlacement(error, element) { element.before(error); },
            rules: {
                }
            });  
    
    
</script>  

    </body>
</html>
