$(document).ready(function () {
    $("#top-ten-container").empty();
    var clientId = localStorage.getItem("clientId");
    var getTenBest = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/subscribers/listTenBest",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#top-ten-container"),
                props = ["phone", "name", "avgPerMonth", "allTimeTurnover"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $.each(props, function (i, prop) {
                    if (prop === "avgPerMonth" || prop === "allTimeTurnover") {
                        var num = data[prop].toFixed(2);
                        $('<td>').html(num).appendTo(tr);
                    }

                    else
                        $('<td>').html(data[prop]).appendTo(tr);
                });
                tbody.append(tr);
            });

        },
        error: function () {
            console.log("Unsuccessfull request");

        }

    })
    var lastTePaid = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/invoices/listLastTenPaid",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["id", "subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
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
});

$("#logout-button").on("click", function () {
    localStorage.clear();
    window.location.href = "../client-frontend/client-log-in.html";
});