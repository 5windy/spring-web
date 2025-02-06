document.addEventListener("DOMContentLoaded", e => {
    const form = document.querySelector("form");

    const inputUsername = form.querySelector("#username");
    const inputPassword = form.querySelector("#password");

    form.addEventListener("submit", async e => {
        e.preventDefault();

        const username = inputUsername.value;
        const password = inputPassword.value;

        if(username !== "" && password !== "") {
            const myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            const response = await fetch("/users/signin", {
                method: "POST",
                headers: myHeaders,
                body: JSON.stringify({
                    "username" : username,
                    "password" : password
                })
            });

            if(response.ok) {
                location.href = "/boards"
            } else {
                alert("회원정보를 확인하세요.");
            }
        }
    })
})