jQuery.noConflict();

zk.afterMount(function(){

	// Finds <pre> elements with the class "c" and snippet highlights the C
	// code within using a "typical" style from the selection of 39
	// with a transparent background with showing line numbers.
	jQuery("pre.c").snippet("c",
			{style:"typical",transparent:true,showNum:true});
});
