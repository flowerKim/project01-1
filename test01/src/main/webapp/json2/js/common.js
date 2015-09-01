var contextRoot = '/web02';

window.moduleMap = {};
window.moduleLoadEvent = {};

function define(moduleName, func) {
	window.moduleMap[moduleName] = func();
}

function requirejs(moduleName, cb) {
	window.moduleLoadEvent[moduleName] = cb;
	if (window.moduleMap[moduleName] == undefined) {
		$('<script>')
		.attr('src', contextRoot 
				+ '/json2/sub/'
				+ moduleName + '.js')
				.attr('async', true)
				.attr('type', 'text/javascript')
				.appendTo('#content');
		
		$('<script>')
				.attr('async', true)
				.attr('type', 'text/javascript')
				.html('window.moduleLoadEvent["' + moduleName + '"]'
						+ '(window.moduleMap["' + moduleName + '"]);')
				.appendTo('#content');
	} else {
		cb(window.moduleMap[moduleName]);
	}
}