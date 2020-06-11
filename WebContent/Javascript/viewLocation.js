function initMap() {
    var x=document.getElementById('x').value;
    var y=document.getElementById('y').value;
    var x1=parseFloat(x);
    var y1=parseFloat(y);
    console.log(x1);
    var test= {lat: x1, lng: y1}; 
    var map = new google.maps.Map(document.getElementById('map'), { 
      zoom: 4, 
      center: test 
    }); 
    var marker = new google.maps.Marker({ 
      position: test, 
      map: map 
    }); 
  } 