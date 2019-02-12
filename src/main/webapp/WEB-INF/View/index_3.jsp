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

        <title>Startmin - Bootstrap Admin Theme</title>

        <link href="/css/bootstrap.min.css" rel="stylesheet">

        <link href="/css/metisMenu.min.css" rel="stylesheet">

        <link href="/css/startmin.css" rel="stylesheet">
        <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
        </style>
    </head>
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">Startmin</a>
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
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell fa-fw"></i> <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-comment fa-fw"></i> New Comment
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> Message Sent
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-tasks fa-fw"></i> New Task
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> secondtruth <b class="caret"></b>
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
                            <h1 class="page-header">Services</h1>
                            <div class="table-responsive">          
                                  <table class="table table-bordered table-striped" id="tab1">
                                    <thead>
                                      <tr>
                                      <tr>
                                        <th>Service Name</th>
                                        <th>Installed Status</th>
                                        <th>State</th>
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
                        
                        <!-- Modal -->
                                      <div class="modal fade modal-tall" id="myModal" role="dialog">
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
                    </div>
                    
                    <div id="div1"> </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="/js/jquery.min.js"></script>
        
         <!-- Bootstrap Core JavaScript -->
        <script src="/js/bootstrap.min.js"></script>
        
           <!-- Metis Menu Plugin JavaScript -->
        <script src="/js/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="/js/startmin.js"></script>
                
        
        <script>

// var data = ${ServiceList};
 var jsondata = ${ServiceList};
    
            var data= jsondata.data;
            
            console.log(data);
 
            var i, j, s, t;
         
            for(i=0; i<data.length; i++)
                {
                    j=i.toString();
                    if(data[i].runningStatus.toUpperCase()=="RUNNING")
                        {
                         t="Stop";   
                        }
                    else if(data[i].runningStatus.toUpperCase()=="STOPPED")
                        {
                         t="Start";   
                        }
                    else{
                        t="Install";
                    }
                    
                    s='<tr id='+j+'><td>'+data[i].serviceName+'</td>'
                    +'<td>'+data[i].installedStatus+'</td>'
                    +'<td>'+data[i].runningStatus+'</td>'
                    +'<td>'+data[i].requiredVersion+'</td>'
                    +'<td>'+data[i].installedVersion+'</td>'
                    +'<td><button class="btn btn-default btn-block">'
                    +t+'</button></td></tr>';
//                    s='<tr id='+j+'><td>'+data[i].serviceName+'</td><td>'+data[i].version+'</td><td>'+data[i].runningStatus+'</td><td>'+data[i].installedStatus+'</td><td><button class="btn btn-default btn-block">'+t+'</button></td></tr>';
            $("#tab1").append(s);
                }
                 
                $("td:has(button)").on("click", function(){
                 var x=$(this).parent().attr('id');
                    x=Number(x);
                var table=document.getElementById("tab1");
                 var sr= table.rows[x+2].cells[0].innerHTML;
                 var st= table.rows[x+2].cells[2].innerHTML;
                 $("#p1").text("Service name: "+sr);
                 $("#p2").text("Status: "+st);
                 $("#myModal").modal();
                });
            
            </script>
        
    </body>
</html>
