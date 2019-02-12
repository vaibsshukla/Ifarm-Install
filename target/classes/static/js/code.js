
var getData=function(result){
          
        var i, j, s, t;
         
        var data=result.data;
        console.log(data); 
    
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
                    
                    var p=data[i].requiredVersion;
                    if(p.toUpperCase()!="UNKNOWN")
                        {
                            var arr = p.split(".");
                            p=arr[0]+"."+arr[1];
                        }
                    var q=data[i].installedVersion;
                    if(q.toUpperCase()!="UNKNOWN")
                        {
                            var arr = q.split(".");
                            q=arr[0]+"."+arr[1];
                        }
                    if(p.toUpperCase()!="UNKNOWN" && q.toUpperCase()!="UNKNOWN")
                        {
                            if(p<q)
                                {
                                    //red
                                     s='<tr id='+j+'><td>'+data[i].serviceName+'</td><td>'+data[i].runningStatus+'</td><td>'+data[i].installedStatus+'</td><td style="background-color: #ff3333;">'+p+'</td><td>'+q+'</td><td><button type="button" class="btn btn-default btn-block">'+t+'</button></td></tr>';
                                } 
                            else
                               {
                                   //green
                                    s='<tr id='+j+'><td>'+data[i].serviceName+'</td><td>'+data[i].runningStatus+'</td><td>'+data[i].installedStatus+'</td><td style="background-color:#33ff33;">'+p+'</td><td>'+q+'</td><td><button type="button" class="btn btn-default btn-block">'+t+'</button></td></tr>';
                               }
                        }
                    else
                        {
                            s='<tr id='+j+'><td>'+data[i].serviceName+'</td><td>'+data[i].runningStatus+'</td><td>'+data[i].installedStatus+'</td><td>'+p+'</td><td>'+q+'</td><td><button type="button" class="btn btn-default btn-block">'+t+'</button></td></tr>';
                        }
                    $("#tab1").append(s);
                }   
            
                $("td:has(button)").on("click", function(){
                     var x=$(this).parent().attr('id');
                        x=Number(x);
                    var table=document.getElementById("tab1");
                     var sr= table.rows[x].cells[0].innerHTML;
                     var st= table.rows[x].cells[1].innerHTML;
                     $("#p1").text("Service name: "+sr);
                     $("#p2").text("Status: "+st);
                     $("#myModal1").modal();
                    });

}