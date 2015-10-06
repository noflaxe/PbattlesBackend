<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Masturbate TV">
    <meta name="author" content="Dominic Freeman">

    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <title>Kitty Battles TV</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/index.css" rel="stylesheet" />
    <link href="css/site.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet" />
    <!-- <link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" /> -->
    <link href="css/ladda.min.css" rel="stylesheet" />
    <link href="css/ladda-themeless.min.css" rel="stylesheet" />
    <link href="css/socialbuttons.css" rel="stylesheet" />

    <script src="js/modernizr.min.js"></script>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="#page-top">
                <i class="fa fa-users"></i> <span class="light">Kitty</span> Battles TV
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
            <ul class="nav navbar-nav">
                <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li class="page-scroll">
                    <a href="#about">About</a>
                </li>
                <li class="page-scroll">
                    <a href="#contact">Contact</a>
                </li>
                <li class="page-scroll">
                    <a href="#help">Help</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<section class="intro">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <h1 class="brand-heading">Kitty Battles TV</h1>
                <p class="intro-text"><font color="#fffff731">${warning}</font></p>
                <p class="intro-text"><font color="#1bff23">${message}</font></p>
                <p class="intro-text">A free, premium quality, staggering battles of your kitties online via video chat.</p>
                <c:choose>
                    <c:when test="${empty cookie.account.value}">
                        <a href="room" class="btn btn-default btn-lg">Enter anonymously</a>
                        <a href="#" data-toggle="modal" data-target="#log-in" class="btn btn-default btn-lg page-scroll">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="room" class="btn btn-default btn-lg">Enter as ${cookie.account.value}</a>
                        <a href="logout" class="btn btn-default btn-lg page-scroll">Logout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>

<!--ABOUT SECTION-->
<section id="about" class="container about-section text-center ">
    <div class="col-lg-8 col-lg-offset-2">
        <h2>About Kitty Battles.TV</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa delectus eos officiis quod temporibus
            vero!</p>
        <p>Alias debitis ea est exercitationem explicabo incidunt itaque nulla omnis rem reprehenderit! Doloremque,
            mollitia quo? A aut culpa dolores ea enim facilis fugit ipsam officiis omnis provident, soluta, ut veniam?</p>
        <a href="https://github.com/Appius/PBattles.TV" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github project</span></a>
    </div>
</section>


<!--CONTACT SECTION-->
<section id="contact" class="container contact-section text-center">
        <div class="col-lg-8 col-lg-offset-2">
            <h2>Contact us</h2>
            <p>Feel free to email us to provide some feedback on our video chat, give us suggestions, or to just say hello!</p>

            <div class="col-md-4 col-md-offset-4">
                <p>joseph.bloodhell@gmail.com</p>
            </div>            
        </div>
</section>


<!--HELP SECTION-->
<section id="help" class="container help-section text-center">
    <div class="col-lg-8 col-lg-offset-2">
        <h2>Help</h2>
        <div class="row" style="margin-bottom: 40px;">
            <div class="col-lg-3">
                Orem ipsum dolor sit amet, consectetur adipisicing elit. Culpa delectus eos officiis quod temporibus
                vero! Lorem ipsum dolor sit amet, consectetur adipisicing elit. <br/>Accusamus ad aliquid beatae cum dicta dolor
                ea eaque eius enim.
            </div>
            <div class="col-lg-3">
                Ipsa ipsam libero maiores molestiae nam, nobis nulla numquam perferendis perspiciatis quae qui quidem
                quisquam quod reiciendis similique sint sit soluta sunt tempora ut.<br/>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus ad aliquid beatae cum dicta dolor
                ea eaque eius enim esse explicabo fugiat h<br/>arum illo illum in ipsa ipsam libero maiores molestiae nam,
                nobis!
            </div>
            <div class="col-lg-3">
                Orem ipsum dolor sit amet, consectetur adipisicing elit. Culpa delectus eos officiis quod temporibus
                vero! Lorem ipsum dolor sit amet, consectetur adipisicing elit. <br/>Accusamus ad aliquid beatae cum dicta dolor
                ea eaque eius enim esse explicabo <br/>fugiat harum illo illum. Orem ipsum dolor sit amet, consectetur adipisicing
                elit. <br/>Culpa delectus eos officiis quod temporibus vero! Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Accusamus ad aliquid beatae cum dicta <br/>dolor ea eaque eius enim esse explicabo fugiat harum illo illum.
            </div>
            <div class="col-lg-3">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. <br/>Accusamus ad aliquid beatae cum dicta dolor
                ea eaque eius enim esse explicabo fugiat harum illo illum in.<br/>
            </div>
        </div>

        <p>Found bug? Any suggestions? Email us about it.</p>
        <p>joseph.bloodhell@gmail.com</p>
    </div>
</section>

<footer>
    <div class="text-center">
        Copyright © 2014 KPI PTI | Version: 1.0.0.1
    </div>
</footer>

<!--LOGIN|REG SECTION-->
<div class="modal fade" id="log-in" data-keyboard="false" data-target=".bs-example-modal-lg">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Login / registration</h4>
            </div>
            <div class="modal-body">
                <div id="login-section" class="col-lg-6" >

                        <form:form action="login" commandName="loginInfo" class="form-horizontal" method="post">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input class="form-control" data-val="true" data-val-required="The login field is required." id="username" name="login" placeholder="Login" value="" type="text">
                            </div>
                            <span class="help-block"><span class="field-validation-valid" data-valmsg-for="Email" data-valmsg-replace="true"></span></span>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input class="form-control" data-val="true" data-val-required="The Password field is required." id="Password" name="password" placeholder="Password" type="password">
                            </div>
                            <span class="help-block"><span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span></span>
                            <div style="padding: 0 10px;">
                                <a  onclick="document.forms[0].submit();return false" id="form-login-submit" class="btn btn-renren btn-sm pull-right ladda-button" data-style="expand-left" data-size="xs">
                                    <span class="ladda-label">Login</span>
                                </a>

                                <!-- <input value="Login" class="btn btn-renren btn-sm pull-right" type="submit"> -->

                                <%--<div class="clearfix"></div>--%>
                            </div>

                        </form:form>


                </div>
                <div id="registration-section" class="col-lg-6" style="padding-right:20px; border-left: 2px solid #ccc;">
                    <form:form action="register" commandName="registrationInfo" class="form-horizontal" method="post">
                       <%-- <form:errors path="*" />--%>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input class="form-control" data-val="true" data-val-required="The Username field is required." path="login" id="username-reg" name="login" placeholder="Login" value="" type="text">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-male"></i></span>
                            <input class="form-control" data-val="true" data-val-required="The Display Name Password field is required." path="name" id="DisplayName-reg" name="name" placeholder="Display Name" type="text">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input class="form-control" data-val="true" data-val-required="The Password field is required." path="password" id="Password-reg" name="Password" placeholder="Password" type="password">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input class="form-control" data-val="true" data-val-required="The Confirm Password field is required." path="passwordRepeat" id="ConfirmPassword-reg" name="passwordRepeat" placeholder="Confirm Password" type="password">
                        </div>
                        <span class="help-block"><span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span></span>

                       <%-- <img src="http://www.androidnova.org/wp-content/uploads/2012/09/setwidth700-recaptcha-example.gif" width="300px"/>--%>
                        <!--HERE:       https://www.google.com/recaptcha-->

                        <div style="padding: 10px;">
                            <%--KILL HARDCODE LATER--%>
                            <a onclick="document.forms[1].submit();" class="btn btn-xing btn-lg ladda-button pull-right" data-style="expand-left" data-size="xs">
                                <span class="ladda-label">Register</span>
                            </a>

                            <div class="clearfix"></div>
                        </div>
                    </form:form>

                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-2.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<!-- <script src="js/jquery.mCustomScrollbar.concat.min.js"></script> -->
<script src="js/spin.min.js"></script>
<script src="js/ladda.min.js"></script>
<script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/3.3.0/build/yui/yui-min.js"></script>
<script src="js/cookie.js"></script>
<script src="js/twitterLogin.js"></script>
<script src="js/googleLogin.js"></script>
<script src="js/fbLogin.js"></script>

<script src="js/site.js"></script>

<script>
    $('#form-reg-submit').click(function(e){
        e.preventDefault();
        var l = Ladda.create(this);
        l.start();

        /*   POST REQUEST HERE   */
        /*$.post("your-url", 
            { data : data },
          function(response){
            console.log(response);
          }, "json")
        .always(function() { l.stop(); });*/

        setTimeout(function(){
            l.stop();
        }, 2000);

        return false;
    });

    $('#form-login-submit').click(function(e){
        e.preventDefault();
        var l = Ladda.create(this);
        l.start();

        /*   POST REQUEST HERE   */
        /*$.post("your-url", 
            { data : data },
          function(response){
            console.log(response);
          }, "json")
        .always(function() { l.stop(); });*/

        setTimeout(function(){
            l.stop();
        }, 2000);

        return false;
    });

</script>

</body>

</html>