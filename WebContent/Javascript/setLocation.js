function updateMarkerPosition(latLng) {
    document.getElementById('x').value = latLng.lat();
    document.getElementById('y').value = latLng.lng();
  }
  
  function initialize() {
    var latLng = new google.maps.LatLng(-34.397, 150.644);
    var map = new google.maps.Map(document.getElementById('mapCanvas'), {
      zoom: 8,
      center: latLng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var marker = new google.maps.Marker({
      position: latLng,
      title: 'Point A',
      map: map,
      draggable: true
    });
  
    updateMarkerPosition(latLng);
  
    google.maps.event.addListener(marker, 'dragend', function() {
      updateMarkerPosition(marker.getPosition());
    });
  }
  google.maps.event.addDomListener(window, 'load', initialize);