$("#sign-in-button").on("click", function (e) {

    e.preventDefault();

    var userName = $("#user-name").val();
    var password = $("#password").val();

    var userDetails = [userName, password];

    var adminLogin = $.ajax({
        crossOrigin: true,
        type: 'POST',
        url: "http://localhost:8080/api/login/admin",
        contentType: "application/json",
        data: JSON.stringify(userDetails),
        success: function (data) {
            var adminId = data["id"];
            var userName = data["userName"];
            var role = data["role"];
            var token = data["token"];

            if (role.includes("First")) {
                localStorage.setItem("adminId", adminId);
                localStorage.setItem("userName", userName);
                localStorage.setItem("role", role);
                localStorage.setItem("token", token);
                window.location.href = "../admin-frontend/admin-change-password.html";
            } else {
                localStorage.setItem("adminId", adminId);
                localStorage.setItem("userName", userName);
                localStorage.setItem("role", role);
                localStorage.setItem("token", token);

                window.location.href = "../admin-frontend/admin-home.html";
            }
        }
        , error: function (data) {
            alert("Wrong Username or Password");
            window.location.href = "../admin-frontend/admin-log-in.html";
        }
    })
});

