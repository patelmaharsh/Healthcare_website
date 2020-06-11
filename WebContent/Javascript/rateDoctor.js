function rating()
{
	if(document.getElementById('star-5').checked) {
		document.getElementById('val').value='5';
	}
	else if(document.getElementById('star-4').checked) {
		document.getElementById('val').value='4';
	}
	else if(document.getElementById('star-3').checked) {
		document.getElementById('val').value='3';
	}
	else if(document.getElementById('star-2').checked) {
		document.getElementById('val').value='2';
	}
	else if(document.getElementById('star-1').checked) {
		document.getElementById('val').value='1';
	}
}