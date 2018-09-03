var inputText = [];

$("#reset-password").on("click", function () {
    $("#reset-password-form").toggle();
});

$(document).ready(function () {
    adminLoad();

});

function adminLoad() {
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin/manage/admins/findById/14",
        success: function (admin) {
            var tbody = $("#bulk-container")
            var userId = admin["id"];
            var username = admin["user"]["username"];
            var eMail = admin["eMail"];
            var tr = $('<tr>');

            $('<td>').html(username).appendTo(tr);
            $('<td>').html(eMail).appendTo(tr);

            var editButton = "<button id='edit-button' value=" + userId + "> Edit </button>";
            $('<td>').html(editButton).appendTo(tr);

            tbody.append(tr);

            var trPassword = $('<tr>');

            var oldPassword = '<input id="old-password" type="password" placeholder="Old password">';
            var newPassword = '<input id="new-password" type="password" placeholder="New password">'
            var resetButton = "<button id='reset-password-button' value=" + userId + "> Reset password</button>";

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
        var updateEmail = $(this.nextSibling.firstChild).val();
        inputText.push(updatedUsername);
        inputText.push(updateEmail);

    })
    var id = this.getAttribute("value");
    var username = inputText[0];
    var email = inputText[1];
    var enabled = 1;
    var firstLogin = 1;
    var user = {username: username, password: "null", enabled: enabled}
    var admin = {id: id, user: user, eMail: email, firstLogin: firstLogin}

    var updateAdmin = $.ajax({
        type: 'PUT',
        url: "http://localhost:8080/admin/manage/admins/update",
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function () {
            adminLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});
