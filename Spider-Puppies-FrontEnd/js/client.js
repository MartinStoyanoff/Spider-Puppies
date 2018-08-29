$(document).ready(function () {
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/invoice/findAllPendingByClientId/9",
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $('<input' + " value=" + data["id"] + ' type="checkbox" checked="checked">').appendTo(tr);
                $.each(props, function (i, prop) {
                    $('<td>').html(data[prop]).appendTo(tr);
                });
                tbody.append(tr);
            });

        },
        error: function () {
            console.log("Unsuccessfull request");

        }

    })
})



$("#select-none-button").on("click", function () {
    $("#bulk-container input").removeAttr('checked');
})
$("#select-all-button").on("click", function () {
    $("#bulk-container input").attr("checked", "checked");
})

$("#payment-button").on("click", function payInvoiceByIdList() {
    var allVals = [];
    $('#bulk-container :checked').each(function () {
        allVals.push($(this).val());
    });
    var payment = $.ajax({
        crossOrigin: true,
        type: 'PUT',
        url: "http://localhost:8080/aclient/payInvoiceByIdList",
        contentType: "application/json",
        data: JSON.stringify(allVals),
        success: function (data) {
            console.log(data)
            $("#bulk-container").empty();
            var invoices = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/aclient/invoice/findAllPendingByClientId/9",
                success: function (data) {
                    var tbody = $("#bulk-container"),
                        props = ["subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
                    $.each(data, function (i, data) {
                        var tr = $('<tr>');
                        $('<input' + " value=" + data["id"] + ' type="checkbox" checked="checked">').appendTo(tr);
                        $.each(props, function (i, prop) {
                            $('<td>').html(data[prop]).appendTo(tr);
                        });
                        tbody.append(tr);
                    });

                },
                error: function () {
                    console.log("Unsuccessfull request");

                }

            })

        },
        error: function () {
            console.log("Unsuccessfull request");

        }

    })


})