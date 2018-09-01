$(document).ready(function () {
    $("#top-ten-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/subscribers/getTenBest/9",
        success: function (data) {
            console.log(data);
            var tbody = $("#top-ten-container"),
                props = ["phone", "name", "avgPerMonth", "allTimeTurnover"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
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
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/invoice/getLastTenPaid/9",
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["id","subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $.each(props, function (i, prop) {
                    $('<td>').html(data[prop]).appendTo(tr);
                });
                tbody.append(tr);
            });

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
})