$(document).ready(function () {
    $("#top-ten-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/subscribers/getTenBestByTurnover/9",
        success: function (data) {
            var tbody = $("#top-ten-container-container"),
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