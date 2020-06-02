(function(exports){

var o = {};

    var handleDrop = undefined;
    var handleDragOver = undefined;
    var handleDragLeave = undefined;

    o.startGlobalDropListener_ = function(callback) {
        try {
            //public static void fireDropEvent(final int x, final int y, final String filePath) {
            
            // On retina displays we may need to scale the coordinates that we are sending
            var t = window.cn1UnscaleCoord || function(x){return x;};
            var self = this;
            var fireDropEvent = self.$GLOBAL$.com_codename1_datatransfer_DropTarget.fireDropEvent__int_int_java_lang_String$async;
            var addTempFile = function(blob) {
                window.cn1TmpFiles = window.cn1TmpFiles || []; 
                return window.cn1TmpFiles.push(blob)-1;
            };
            //console.log("Adding drop listener");
            handleDrop = handleDrop || function(evt) {
                //console.log("Handling drop");
                evt.stopPropagation();
                evt.preventDefault();
                var files = evt.dataTransfer.files;
                if (files.length === 0) {
                    return;
                }
                var index = addTempFile(files[0]);
                var tmpFilePath = "tmp://"+index;
                //console.log("File path "+tmpFilePath);
                //console.log($rt_str(tmpFilePath));
                fireDropEvent(t(evt.clientX), t(evt.clientY), tmpFilePath);
            };
            
            
            handleDragOver = handleDragOver || function(evt) {
                evt.stopPropagation();
                evt.preventDefault();
            };
            
            handleDragLeave = handleDragLeave || function (evt) {
                evt.stopPropagation();
                evt.preventDefault();
            };
            
            jQuery('#codenameone-canvas').parent().get(0).addEventListener('drop', handleDrop, false);
            jQuery('#codenameone-canvas').parent().get(0).addEventListener('dragover', handleDragOver, false);
            jQuery('#codenameone-canvas').parent().get(0).addEventListener('dragleave', handleDragLeave, false);
            //console.log("Add added listener to canvas");
            //console.log(jQuery('#codenameone-canvas').get(0));
        } finally {
            callback.complete();
        }
    };

    o.stopGlobalDropListener_ = function(callback) {
        try {
            if (handleDrop !== null) {
                jQuery('#codenameone-canvas').parent().get(0).removeEventListener('drop', handleDrop);
                jQuery('#codenameone-canvas').parent().get(0).removeEventListener('dragover', handleDragOver);
                jQuery('#codenameone-canvas').parent().get(0).removeEventListener('dragleave', handleDragLeave);
                handleDrop = null;
                
            }
        } finally {
            callback.complete();
        }
    };

    o.isSupported_ = function(callback) {
        callback.complete(true);
    };

exports.com_codename1_datatransfer_NativeDragAndDrop= o;

})(cn1_get_native_interfaces());
