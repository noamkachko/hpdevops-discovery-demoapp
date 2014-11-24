/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 24/11/14
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
'use strict';
(function() {

	var bands;

	function loadBands() {
		var xhr = new XMLHttpRequest();
		xhr.open('get', '/api/bands');
		xhr.onerror = function() {
			console.error('failed to get the bands data');
		};
		xhr.onload = function() {
			try {
				bands = JSON.parse(xhr.responseText);
				console.log(bands);
			} catch(e) {
				console.error(e);
			}
		};
		xhr.send();
	}

	loadBands();
})();