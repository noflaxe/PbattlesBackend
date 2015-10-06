<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Masturbate TV">

    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <title>Kitty Battles TV</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/site.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet" />
    <link href="css/chat.css" rel="stylesheet" />
    <script src="js/modernizr.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.scrollTo-1.4.2-min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/socket.io/0.9.6/socket.io.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .videoContainer {
            position: relative;
            width: 200px;
            height: 150px;
        }
        .videoContainer video {
            position: absolute;
            width: 100%;
            height: 100%;
        }
        .volume_bar {
            position: absolute;
            width: 5px;
            height: 0px;
            right: 0px;
            bottom: 0px;
            background-color: #12acef;
        }
    </style>
</head>

<body data-target=".navbar-custom">

<div id="setmodal" data-backdrop="static" class="modal fade" role="dialog" aria-hidden="true" aria-labelledby="setLabel">
    <div class="modal-dialog modal-dialog-center">
        <div class="modal-content">
            <div class="modal-body">
                Please, provide an access to your camera and mic, overwise KittyBattles wont work !!!!
            </div>
        </div>
    </div>
</div>

<!-- HEADER BLOCK -->
<div class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="#page-top">
                <i class="fa fa-users"></i> <span class="light">Kitty</span> Battles.TV
            </a>
        </div>

        <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
            <ul class="nav navbar-nav">
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
    </div>
</div>
<!--END HEADER BLOCK -->

<div class="container page-container">
    <div class="row" style=""></div>
    <div class="row">
        <div class="col-md-7 col-sm-6 col-xs-12">
            <div class="videos">
                <div class="videoContainer">
                    <video id="localVideo" style="height: 150px;" oncontextmenu="return false;"></video>
                    <div id="localVolume" class="volume_bar"></div>
                </div>
                <div id="remoteVideos"></div>
            </div>
        </div>
        <div class="col-md-5 col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Group chat</div>
                <div class="panel-body">
                    <ul id="chat">
                        <li class="clearfix">
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">  Start Chatting here!</strong>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="input-group">
                        <form>
                            <input id="text-field" type="text" class="form-control" placeholder="Enter message..."/>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" name="send">Send</button>
                                    </span>
                        </form>
                        <script>

                            var socket = io.connect('http://localhost:2014');
                            var room = '${room.name}';
                            socket.emit('join', room);
                            var login = '${login}';

                            socket.on('message', function (message){
                                var message_template = '<li class="clearfix"><div class="chat-body clearfix"><div class="header">'+
                                        '<strong class="primary-font">'+message.from+'</strong></div><p>'+message.text+'</p></div></li>';

                                $('#chat').append(message_template);
                                $("#chat").scrollTo('100%');
                            });

                            $('button[name="send"]').on("click", function(e){
                                e.preventDefault();
                                var message = $('#text-field').val();
                                if(message.length != 0){
                                    $('#text-field').val('');
                                    socket.emit('message', {text: message,from: login});
                                }
                            });

                            $('#text-field').keyup(function(e){
                                e.preventDefault();
                                if(e.keyCode == 13){
                                    var message = $('#text-field').val();
                                    if(message.length != 0){
                                        $('#text-field').val('');
                                        socket.emit('message', {text: message,from: login});
                                    }
                                }
                            });
                        </script>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="row"></div>
</div>
<script src="js/simplewebrtc.bundle.js"></script>
<script>
    // grab the room from the URL
     var room = '${room.name}';

//    $('#setmodal').modal('show');

    // create our webrtc connection
    var webrtc = new SimpleWebRTC({
        // the id/element dom element that will hold "our" video
        localVideoEl: 'localVideo',
        // the id/element dom element that will hold remote videos
        remoteVideosEl: 'remoteVideos',
        // immediately ask for camera access
        autoRequestMedia: true,
        debug: false,
        detectSpeakingEvents: true,
        autoAdjustMic: false
    });

    webrtc.on('readyToCall', function () {
        if (room) webrtc.joinRoom(room);
        $('#setmodal').modal('hide');
    });

    function showVolume(el, volume) {
        if (!el) return;
        if (volume < -45) { // vary between -45 and -20
            el.style.height = '0px';
        } else if (volume > -20) {
            el.style.height = '100%';
        } else {
            el.style.height = '' + Math.floor((volume + 100) * 100 / 25 - 220) + '%';
        }
    }
    webrtc.on('channelMessage', function (peer, label, data) {
        if (data.type == 'volume') {
            showVolume(document.getElementById('volume_' + peer.id), data.volume);
        }
    });
    webrtc.on('videoAdded', function (video, peer) {
        console.log('video added', peer);
        var remotes = document.getElementById('remotes');
        if (remotes) {
            var d = document.createElement('div');
            d.className = 'videoContainer';
            d.id = 'container_' + webrtc.getDomId(peer);
            d.appendChild(video);
            var vol = document.createElement('div');
            vol.id = 'volume_' + peer.id;
            vol.className = 'volume_bar';
            video.onclick = function () {
                video.style.width = video.videoWidth + 'px';
                video.style.height = video.videoHeight + 'px';
            };
            d.appendChild(vol);
            remotes.appendChild(d);
        }
    });
    webrtc.on('videoRemoved', function (video, peer) {
        console.log('video removed ', peer);
        var remotes = document.getElementById('remotes');
        var el = document.getElementById('container_' + webrtc.getDomId(peer));
        if (remotes && el) {
            remotes.removeChild(el);
        }
    });
    webrtc.on('volumeChange', function (volume, treshold) {
        //console.log('own volume', volume);
        showVolume(document.getElementById('localVolume'), volume);
    });
</script>
</body>
</html>
