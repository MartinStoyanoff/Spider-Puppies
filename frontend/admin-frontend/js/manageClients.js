var inputText = [];

$("#create-client").on("click", function () {
    $("#add-client-form").toggle();
})
$(document).ready(function () {
    clientLoad();

});
$("#add-client").on("click", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var fullName = $("#full-name").val();
    var uic = $("#uic").val();
    var enabled = 1;

    var user = {username: username, password: password, enabled: enabled};
    var client = {user: user, fullName: fullName, unifiedIdentificationCode: uic};

    var addClient = $.ajax({
        crossOrigin: true,
        type: 'POST',
        url: "http://localhost:8080/admin/manage/clients/add",
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(client),
        success: function () {
            clientLoad();
            $("#username").val('');
            $("#password").val('');
            $("#full-name").val('');
            $("#uic").val('');
            $("#add-client-form").toggle();

        }
    })
});

function clientLoad() {
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin/manage/clients/listAll",
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#bulk-container")
            data.forEach(function (client) {

                var userId = client["id"];
                var username = client["user"]["username"];
                var full_name = client["fullName"];
                var uic = client["unifiedIdentificationCode"]
                var tr = $('<tr>');

                $('<td>').html(username).appendTo(tr);
                $('<td>').html(full_name).appendTo(tr);
                $('<td>').html(uic).appendTo(tr);


                var editButton = "<button class='btn btn-outline-success btn-border-green' id='edit-button' value=" + userId + "> Edit </button>";
                var deleteButton = "<button class='btn btn-outline-success btn-border-red' id='delete-button' value=" + userId + "> Delete </button>";
                $('<td>').html(editButton + deleteButton).appendTo(tr);

                tbody.append(tr);
            });
        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
}

$("#bulk-container").on("click", "#delete-button", function () {
    var id = this.getAttribute("value")
    var deleteUser = $.ajax({
        type: 'DELETE',
        url: "http://localhost:8080/admin/manage/clients/delete/" + id,
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        success: function () {
            clientLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});


$("#bulk-container").on("click", "#edit-button", function () {
    var id = this.getAttribute("value");
    $(this).text("Update");
    this.setAttribute("id", "update-button")
    $(this).parents("tr").find("td:not(:last-child)").each(function () {
        var text = $(this).text();

        if (text.indexOf("@") > 0) {
            $(this).html('<input type="email" class="form-control" value="' + $(this).text() + '">');

        }
        else {
            $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
        }
    });
});

$("#bulk-container").on("click", "#update-button", function () {
    $(this).parents("tr").find("td:not(:last-child)").each(function () {
        var updatedUsername = $(this.firstChild).val();
        var updateFullName = $(this.nextSibling.firstChild).val();
        console.log(updateFullName);
        var updateUIC = $(this.fir).siblings("#uic").val();
        console.log(updateUIC);
        inputText.push(updatedUsername);
        inputText.push(updateFullName);
        inputText.push(updateUIC);

    });

    var id = this.getAttribute("value");
    var username = inputText[0];
    var fullName = inputText[1];
    var unifiedIdentificationCode = inputText[4];
    var enabled = 1;
    var firstLogin = 1;
    var user = {username: username, password: "null", enabled: enabled}
    var client = {id: id, user: user, fullName: fullName, unifiedIdentificationCode:unifiedIdentificationCode}

    var updateAdmin = $.ajax({
        type: 'PUT',
        url: "http://localhost:8080/admin/manage/clients/update",
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(client),
        success: function () {
            inputText = [];
            clientLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});