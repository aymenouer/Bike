(function(exports){

var o = {};

    o.scanQRCode_ = function(callback) {
        callback.error(new Error("Not implemented yet"));
    };

    o.scanBarCode_ = function(callback) {
        callback.error(new Error("Not implemented yet"));
    };

    o.isSupported_ = function(callback) {
        callback.complete(false);
    };

exports.com_codename1_ext_codescan_NativeCodeScanner= o;

})(cn1_get_native_interfaces());
