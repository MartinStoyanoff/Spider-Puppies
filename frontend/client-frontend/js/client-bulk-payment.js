$(document).ready(function () {
    $("#bulk-container").empty();
    var clientId = localStorage.getItem("clientId")
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/invoice/findAllPendingByClientId/" + clientId,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $('<td><input' + " value=" + data["id"] + ' type="checkbox" class="form-check-input" checked="checked">').appendTo(tr);
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
    var clientId = localStorage.getItem("clientId");
    var payment = $.ajax({
        crossOrigin: true,
        type: 'PUT',
        url: "http://localhost:8080/api/invoice/payByIdList",
        headers: {
            "id": clientId,
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(allVals),
        success: function (data) {
            console.log(data)
            $("#bulk-container").empty();
            var invoices = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/invoice/findAllPendingByClientId/"+clientId,
                success: function (data) {
                    $('#bulk-container').empty();
                    console.log("Invoices Paid Successfully");

                },
                error: function () {
                    console.log("Unsuccessful request");

                }

            })

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