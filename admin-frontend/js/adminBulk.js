$("#proceed").on("click", function () {
    var dateRange = $("#daterange").val();
    var dates = dateRange.split(" - ");
    var startDate = dates[0];
    var endDate = dates[1];
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'POST',
        url: "http://localhost:8080/admin/manage/subscribers/listAllDuePayments",
        contentType: "application/json",
        data: JSON.stringify(dates),
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["subscriberPhone", "serviceType", "subscriptionPlan", "price"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $('<input' + " value=" + data["subscriberId"] + ' type="checkbox" class="form-check-input" checked="checked">').appendTo(tr);
                $.each(props, function (i, prop) {
                    $('<td>').html(data[prop]).appendTo(tr);
                });
                $('<td class="currency"value =' + data["currency"] + ' >').html(data["currency"]).appendTo(tr);
                tbody.append(tr);
            });

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
});

$("#select-none").on("click", function () {
    $("#bulk-container input").removeAttr('checked');
});
$("#select-all").on("click", function () {
    $("#bulk-container input").attr("checked", "checked");
});

$("#generate-payment-button").on("click", function generateBulkPayment() {
    var allVals = [];
    $('#bulk-container :checked').each(function () {
        var subscriberId = $(this).val();
        var currency = $(this.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling).text();

        var invoiceInfo = {subscriberId: subscriberId, currency: currency};

        allVals.push(invoiceInfo);

    });
    var payment = $.ajax({
        crossOrigin: true,
        type: 'POST',
        url: "http://localhost:8080/admin/manage/invoices/bulkgenerate",
        contentType: "application/json",
        data: JSON.stringify(allVals),
        success: function (data) {
            $('#bulk-container').empty();
            console.log("Invoices Generated");
        },
        error: function () {
            $('#bulk-container').empty();

            $('#bulk-container').append("<div>"+"Unsuccessful generation"+"</div>");

            console.log("Unsuccessful request");

        }

    })


})