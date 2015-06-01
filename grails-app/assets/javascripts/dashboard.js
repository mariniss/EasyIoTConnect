/**
 *
 * @param sectionId
 */
function collapseTimerSection(sectionId, onlyClose) {
	var section = $('#'+sectionId);

	if(section.data('expanded') == true) {
		section.removeClass("in");
		section.css("height", "0px");
		section.data('expanded', false);
	}
	else {
		if(onlyClose == false) {
			section.addClass("in");
			section.css("height", "200px");
			section.data('expanded', true);
		}
	}
}


/**
 *
 * @param intputId
 */
function setTimeZoneOn(inputId) {
	var input = $('#'+inputId);

	var tz = jstz.determine();
	input.val(tz.name());
}