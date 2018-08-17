var currentShow = $("#admin_start");

$("#add_invoice").on("click", function () {
    currentShow.hide();
    $("#form_invoice").show();
    currentShow = $("#form_invoice");
})
$("#add_subscriber").on("click", function () {
    currentShow.hide();
    $("#form_subscriber").show();
    currentShow = $("#form_subscriber");
})
$("#add_service").on("click", function () {
    currentShow.hide();
    $("#form_service").show();
    currentShow = $("#form_service");
})
