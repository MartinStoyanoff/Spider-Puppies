/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

$("#select-none-button").on("click", function () {
    $("#bulk-container input").removeAttr('checked');
})
$("#select-all-button").on("click", function () {
    $("#bulk-container input").attr("checked", "checked");
})


$('#myDropdown').on('click', 'a', function () {
    var phone = $(this).text();
    $("#myDropdown").toggle();
    $("#bulk-container").empty();

    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin/manage/subscribers/getSubscriberDuePaymentsByPhone/" + phone,
        success: function (data) {
            console.log(data);
            var tbody = $("#bulk-container"),
                props = ["subscriberId", "subscriberPhone", "serviceType", "subscriptionPlan", "price", "currency"];
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


$("#proceed").on("click", function () {
    var dateRange = $("#daterange").val();
    var dates = dateRange.split(" - ");
    var startDate = dates[0];
    var endDate = dates[1];

    console.log(startDate);
    console.log(endDate);
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'POST',
        url: "http://localhost:8080/admin/manage/subscribers/getAllSubscribersInBillingPeriod",
        contentType: "application/json",
        data: JSON.stringify(dates),
        success: function (data) {
            console.log(data);
            var tbody = $("#myDropdown");
            for (var num in data) {
                tbody.append('<a href="#" class="phones">' + data[num] + '</a>');
            }
            ;

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
});

$("#generate-payment-button").on("click", function () {
    var allVals = [];
    var invoiceInfo = {}
    $('#bulk-container tr').each(function (i, tr) {

        var subscriberId = $('#bulk-container tr td:first-child').html();
        var currency = $('#bulk-container tr td:last-child').html();
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
            $("#myDropdown a").remove();//TODO check how to delete only subscriber number
        },
        error: function () {
            $('#bulk-container').empty();

            $('#bulk-container').append("<div>" + "Unsuccessful generation" + "</div>");

            console.log("Unsuccessful request");

        }

    })


})