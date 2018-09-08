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
    myFunction();
    $("#bulk-container").empty();

    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/subscribers/listDuePaymentsByPhone/" + phone,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#bulk-container"),
                props = ["subscriberId", "subscriberPhone", "serviceType", "subscriptionPlan", "price",];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $.each(props, function (i, prop) {
                    $('<td>').html(data[prop]).appendTo(tr);
                });
                var currency = '<select><option value="BGN">BGN</option> <option value="EUR">EUR</option><option value="USD">USD</option><option value="CHF">CHF</option> </select>'
                $('<td>').html(currency).appendTo(tr);
                tbody.append(tr);
            });
            var getDetailsAjas = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/api/subscribers/getSubscriberByPhone/"+phone,
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: function (data) {
                    $('#subscriber-info-container').removeAttr("style");
                    $('#invoice-history-table').removeAttr("style");
                    var fullName = data["firstName"] + " " + data["lastName"];
                    var pin = data["personalIdentificationNumber"];
                    var address = data["address"];
                    var firstActivation = data["firstServiceActivationDate"];
                    var nextBillingDate = data["billingDate"];
                    var totalPaidAmount = data["allTimeTurnover"].toFixed(2);

                    var personalDetailsBody = $('#personal-info-container');
                    var detailsHeader = "<h3>Subscriber Personal Details</h3>";
                    personalDetailsBody.append(detailsHeader);

                    var fullNameElement = "<p>Full Name</p>" + "<p>" + fullName + "</p>";
                    var div = "<div class='flex justify-content-between'>"+fullNameElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var pinElement = "<p>PIN</p>" + "<p>" + pin + "</p>";
                    div = "<div class='flex justify-content-between'>"+pinElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var addressElement = "<p>Address</p>" + "<p>" + address + "</p>";
                    div = "<div class='flex justify-content-between'>"+addressElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var firstActivationElement = "<p>First Activation Date</p>" + "<p>" + firstActivation + "</p>";
                    div = "<div class='flex justify-content-between'>"+firstActivationElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');

                    var nextBillingElement = "<p>Next Billing Date</p>" + "<p>" + nextBillingDate + "</p>";
                    div = "<div class='flex justify-content-between'>"+nextBillingElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');

                    var totalPaidElement = "<p>Total Paid Amount (BGN)</p>" + "<p>" + totalPaidAmount + "</p>";
                    div = "<div class='flex justify-content-between'>"+totalPaidElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    // ---------------------------------------

                    var telecomServicesList = data["telecomServices"];

                    var serviceProps = ["type", "subscriptionPlan", "price"];

                    var serviceContainer = $('#services-container');
                    $.each(telecomServicesList, function(i, telecomServicesList){
                        var tr = $('<tr>');
                        $.each(serviceProps, function (i, serviceProp) {
                            $('<td>').html(telecomServicesList[serviceProp]).appendTo(tr);
                        });
                        serviceContainer.append(tr);
                    });

                    // ---------------------------------------

                    var invoices = data["invoices"];
                    var invoiceHistoryTbody = $("#invoice-history-container"),
                        props = ["id","startDate","endDate","paymentDate", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];

                    $("#invoice-history-add-phone").text("Invoice history for "+phone);
                    for (var k = invoices.length-1; k >= 0; k--) {
                        var tr = $('<tr>');
                        var inv = invoices[k];
                        $.each(props, function (i, prop) {
                            if(prop==="paymentDate"&& inv[prop]===null) {
                                $('<td>').html("Pending").appendTo(tr);
                            }
                            else{
                                $('<td>').html(inv[prop]).appendTo(tr);

                            }
                        });
                        invoiceHistoryTbody.append(tr);

                    }

                },
                error: function () {
                    console.log("Unsuccessful request");
                }
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

    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'POST',
        url: "http://localhost:8080/api/subscribers/listAllPhoneNumbersInBillingPeriod",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(dates),
        success: function (data) {
            var tbody = $("#myDropdown");
            for (var num in data) {
                tbody.append('<a href="#" class="phones">' + data[num] + '</a>');
            }

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
});

$("#generate-payment-button").on("click", function () {
    var allVals = [];
    $('#bulk-container tr').each(function () {
        var subscriberId = $(this.firstChild).html();
        var currency = $(this.lastChild.firstChild).val();
        var invoiceInfo = {subscriberId: subscriberId, currency: currency};
        allVals.push(invoiceInfo);


    });
    var payment = $.ajax({
        crossOrigin: true,
        type: 'POST',
        url: "http://localhost:8080/api/invoices/bulkgenerate",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
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
});

$("#logout-button").on("click", function () {
    localStorage.clear();
    window.location.href = "../admin-frontend/admin-log-in.html";
});