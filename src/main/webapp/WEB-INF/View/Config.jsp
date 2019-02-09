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

        <link href="/css/metisMenu.min.css" rel="stylesheet">

        <link href="/css/startmin.css" rel="stylesheet">

        <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
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
                            <h1 class="page-header">List of Required Services</h1>
                            <div class="table-responsive">          
                                  <table class="table table-bordered table-striped">
                                    <thead>
                                      <tr>
                                        <th>Service Name</th>
                                        <th>Running Status</th>
                                        <th>Installed Status</th>
                                        <th>Required Version</th>
                                        <th>Installed Version</th>
                                        <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tab1">
                                    </tbody>
                                  </table>
                                </div>
                            
                        </div>
                        <!-- /.col-lg-12 -->
                        
                        
                        <div class="loader" style="display: none;"></div>
                        
                        <!-- Modal -->
                                      <div class="modal fade modal-tall" id="myModal1" role="dialog">
                                        <div class="modal-dialog">

                                          <!-- Modal content-->
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                                              <h4 class="modal-title">Action!</h4>
                                            </div>
                                            <div class="modal-body">
                                            <p id="p1"></p>
                                            <p id="p2"></p>
                                            </div>
                                            <div class="modal-footer">
                                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                          </div>

                                        </div>
                                      </div>
                            <!--/Modal-->
                        
                    
                            <div class="col-lg-12">
                                <div class="btn-group btn-group-lg btn-group-justified">
                                    <div class="btn-group">
                                        <button type="button" id="check" class="btn btn-primary">Check Services</button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" id="cred" class="btn btn-primary">Provide Ambari Credentials</button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary">Deploy ifarm</button>
                                    </div>
                                </div>
                                <br><br>
                            </div>
                        
                         <!-- Modal -->
                                      <div class="modal fade modal-tall" id="myModal2" role="dialog">
                                        <div class="modal-dialog">

                                          <!-- Modal content-->
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                                              <h4 class="modal-title">Ambari Credentials</h4>
                                            </div>
                                            <div id=modalBody class="modal-body">
                                           
                                            </div>
                                            <div class="modal-footer">
                                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                          </div>

                                        </div>
                                      </div>
                            <!--/Modal-->
                          
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <script src="/js/jquery.min.js"></script>        
        <script src="/js/bootstrap.min.js"></script>        
        <script src="/js/metisMenu.min.js"></script>
        <script src="/js/startmin.js"></script>
 
        <script src="/js/code.js"></script>
        <script src="/js/errormsg.js"></script>
        
        <script>
            
        var jsondata = ${ServiceList};
/*  var jsondata= {
                "status": 200,
                "data": [{
                    "type": "HDP",
                    "serviceName": "HDFS",
                    "runningStatus": "Started",
                    "installedStatus": "Installed",
                    "requiredVersion": "2.6.0",
                    "installedVersion": "2.7.1"
                }, {
                    "type": "HDP",
                    "serviceName": "NIFI",
                    "runningStatus": "Stopped",
                    "installedStatus": "Installed",
                    "requiredVersion": "1.5.0",
                    "installedVersion": "1.7.0"
                }, {
                    "type": "HDP",
                    "serviceName": "Zookeeper",
                    "runningStatus": "Started",
                    "installedStatus": "Installed",
                    "requiredVersion": "1.4.0",
                    "installedVersion": "1.2.0"
                }, {
                    "type": "HDP",
                    "serviceName": "Kafka",
                    "runningStatus": "Stopped",
                    "installedStatus": "Installed",
                    "requiredVersion": "1.0.0",
                    "installedVersion": "1.0.0"
                }, {
                    "type": "HDP",
                    "serviceName": "Spark2",
                    "runningStatus": "Running",
                    "installedStatus": "Installed",
                    "requiredVersion": "2.3.0",
                    "installedVersion": "2.1.1"
                }, {
                    "type": "HDP",
                    "serviceName": "solr",
                    "runningStatus": "Unknown",
                    "installedStatus": "Unknown",
                    "requiredVersion": "5.5",
                    "installedVersion": "Unknown"
                }, {
                    "type": "NON-HDP",
                    "serviceName": "Mysql",
                    "runningStatus": "Unknown",
                    "installedStatus": "Unknown",
                    "requiredVersion": "8.0",
                    "installedVersion": "Unknown"
                }, {
                    "type": "NON-HDP",
                    "serviceName": "Mongo",
                    "runningStatus": "Unknown",
                    "installedStatus": "Unknown",
                    "requiredVersion": "3.5",
                    "installedVersion": "Unknown"
                }, {
                    "type": "NON-HDP",
                    "serviceName": "Neo4J",
                    "runningStatus": "Unknown",
                    "installedStatus": "Unknown",
                    "requiredVersion": "3.5",
                    "installedVersion": "Unknown"
                }, {
                    "type": "NON-HDP",
                    "serviceName": "PackServer",
                    "runningStatus": "Unknown",
                    "installedStatus": "Unknown",
                    "requiredVersion": "1.0",
                    "installedVersion": "Unknown"
                }],
                "message": "Success"
            } */
            
            getData(jsondata);

               $(document).ready(function(){
                   
                    $("#check").click(function(){
                         $(".loader").show();
                        $("#tab1").empty();
                        $.ajax({
                        type: "post",
                        url: "http://localhost:8080/checkAllServices",
                        dataType : "json",
                        success: function(result){
                        	getData(result);
                        },
                        statusCode : {
                            500: function(){
                            errorsforstatuscode(result);   
                           }
                        }
                        });
                        
                        setTimeout(function(){
                        $(".loader").hide();
                        },8000);
                    });
                   
                    $("#cred").click(function(){
                    $("#modalBody").load("modalform.html");
                    $("#myModal2").modal();
                    });
            });
            </script>
        
    </body>
</html>
