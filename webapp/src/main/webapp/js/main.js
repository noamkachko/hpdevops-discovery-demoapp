/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 24/11/14
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
'use strict';
(function() {

	function loadBands(callback) {
		var xhr = new XMLHttpRequest(), bands;
		xhr.open('get', '/api/bands');
		xhr.onerror = function() {
			callback('failed to get the bands data');
		};
		xhr.onload = function() {
			try {
				bands = JSON.parse(xhr.responseText);
				callback(null, bands);
			} catch(e) {
				callback(e);
			}
		};
		xhr.send();
	}

	function renderBands(bands) {
		var template, listElement, tmpElement;
		template = document.getElementById('bandStripTemplate');
		listElement = document.getElementById('bandsList');
		bands.forEach(function(band) {
			template.content.querySelector('.bandLogo').src = band.logo;
			template.content.querySelector('.bandTitle').textContent = band.name.toUpperCase();
			template.content.querySelector('.bandSong').src = band.song;
			tmpElement = document.importNode(template.content, true);
			listElement.appendChild(tmpElement);
		});
	}

	loadBands(function(error, bands) {
		if(error) {
			console.error(error);
		} else {
			renderBands(bands);
		}
	});
})();