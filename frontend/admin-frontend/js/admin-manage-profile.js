var inputText = [];

$(document).ready(function () {
    adminLoad();

});

function adminLoad() {
    var adminId = localStorage.getItem("adminId");
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/admins/findById/" + adminId,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (admin) {
            var tbody = $("#bulk-container");
            var userId = admin["id"];
            var username = admin["user"]["username"];
            var eMail = admin["eMail"];
            var tr = $('<tr>');

            $('<td>').html(username).appendTo(tr);
            $('<td>').html(eMail).appendTo(tr);

            var editButton = "<button class='btn btn-outline-success btn-border-green' id='edit-button' value=" + userId + "> Edit </button>";
            $('<td>').html(editButton).appendTo(tr);

            tbody.append(tr);

            var trPassword = $('<tr>');

            var oldPassword = '<input class="form-control" id="old-password" type="password" placeholder="Old password">';
            var newPassword = '<input class="form-control" id="new-password" type="password" placeholder="New password">'
            var resetButton = "<button class='btn btn-outline-success btn-border-red' id='change-password-button' value=" + userId + "> Change password</button>";

            $('<td>').html(oldPassword).appendTo(trPassword);
            $('<td>').html(newPassword).appendTo(trPassword);
            $('<td>').html(resetButton).appendTo(trPassword);
            tbody.append(trPassword);
        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
}

$("#bulk-container").on("click", "#edit-button", function () {
    var id = this.getAttribute("value");
    $(this).text("Update");
    this.setAttribute("id", "update-button");
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
        var updateEmail = $(this.nextSibling.firstChild).val();
        inputText.push(updatedUsername);
        inputText.push(updateEmail);

    });
    var id = this.getAttribute("value");
    var username = inputText[0];
    var email = inputText[1];
    var enabled = 1;
    var firstLogin = 1;
    var user = {username: username, password: "null", enabled: enabled}
    var admin = {id: id, user: user, eMail: email, firstLogin: firstLogin}

    var updateAdmin = $.ajax({
        type: 'PUT',
        url: "http://localhost:8080/api/admins/update",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function () {
            inputText = [];
            adminLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});
$("#bulk-container").on("click", "#change-password-button", function () {
    var adminId = $("#change-password-button").val();
    var oldPassword = $("#old-password").val();
    var newPassword = $("#new-password").val();
    var passwordUpdateInfo = [adminId, oldPassword, newPassword];

    var updateAdmin = $.ajax({
        type: 'PUT',
        url: "http://localhost:8080/api/admins/changePassword",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(passwordUpdateInfo),
        success: function () {
            window.location.href = "../admin-log-in.html";

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })

});

$("#logout-button").on("click", function () {
    localStorage.clear();
    window.location.href = "../admin-frontend/admin-log-in.html";
});