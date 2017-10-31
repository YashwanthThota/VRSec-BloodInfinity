<%@page import="bi.Configurations"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Configurations.projectTitle%></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="css/tooplate_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/coda-slider.css" type="text/css" charset="utf-8" />

<script src="js/jquery-1.2.6.js" type="text/javascript"></script>
<script src="js/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
<script src="js/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="js/coda-slider.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	
<div id="slider">
    <div id="tooplate_wrapper">
    	
        <div id="tooplate_sidebar">
        	
            <div id="header">
            	<h1><a href="#"><%=Configurations.projectTitle%></a></h1>
            </div>

            <div id="menu">
                <ul class="navigation">
                	<li><a href="#home">Server IP : <%=Configurations.getMyIp()%></a></li> 
                        <li><a href="#home" >Home</a></li>
                        <li><a href="feeds.jsp" class="last">FeedBacks</a></li>
                </ul>
            </div>
			
		</div> <!-- end of sidebar -->  
    
        <div id="content">
       	  	<div class="scroll">
        	  <div class="scrollContainer">
                <div class="panel" id="home">
				
                	<h2>Blood-Infinity</h2>   
                    <p><em>Blood-Infinity is a Mobile App helpline which connects needy patients with blood donors in India. So if you need blood, its a good place to turn to. It's simple. It's effective. It's Free.</em></p>	           
                    <img src="images/tooplate_image_01.jpg" alt="Image 01" class="image_wrapper image_fl" />             
                    <p>You can also <a rel="nofollow" href="" target="_blank">Sign up</a> as a blood Donor and help build a Community that cares.</p>
                    
                    <div class="cleaner h20"></div> 
					
               		<h3>Our Services</h3>
                    <img src="images/pic2.png" alt="Image 02" class="image_wrapper image_fr" />
                    <p>Login,Registration,Search...</p>
            
                </div> <!-- end of home -->				
        	   
      	  	</div> <!-- end of scroll -->
		</div>
		
        <div class="cleaner"></div>
	</div>
    </div> <!-- end of content -->
	
    <div id="footer">
        Copyright © 2015 <a href="#"><%=Configurations.projectTitle%></a><br />        		
    </div>

</div>
</body>
</html>