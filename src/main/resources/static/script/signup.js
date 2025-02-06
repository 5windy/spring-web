document.addEventListener("DOMContentLoaded", e => {
    const form = document.querySelector("form");

    const inputUsername = form.querySelector("#username");
    const errorUsername = document.getElementById("error-msg-username");
    let isValidUsername = false;

    const inputPassword = form.querySelector("#password");
    let isValidPassword = false;

    const inputNickname = form.querySelector("#nickname");
    const errorNickname = document.getElementById("error-msg-nickname");
    let isValidNickname = false;

    inputUsername.addEventListener("change", async e => {
        const username = e.target.value;

        const response = await fetch(`/users/valid/username?value=${username}`);
        if(response.ok) {
            errorUsername.style.display = "block";
            isValidUsername = false;
        } else {
            errorUsername.style.display = "none";
            isValidUsername = true;
        }
    });

    inputPassword.addEventListener("change", e => {
        isValidPassword = true;
    })

    inputNickname.addEventListener("change", async e => {
        const nickname = e.target.value;

        const response = await fetch(`/users/valid/nickname?value=${nickname}`);
        if(response.ok) {
            errorNickname.style.display = "block";
            isValidNickname = false;
        } else {
            errorNickname.style.display = "none";
            isValidNickname = true;
        }
    });

    form.addEventListener("submit", async e => {
        e.preventDefault();

        if(isValidUsername && isValidPassword && isValidNickname) {
            // form.submit();
            // fetch 메소드로 API 호출
            const myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            const raw = JSON.stringify({
                "username": inputUsername.value,
                "password": inputPassword.value
            });

            const requestOptions = {
                method: "POST",
                headers: myHeaders,
                body: raw,
                redirect: "follow"
            };

            const response = await fetch("http://localhost:8080/users/signup", requestOptions);
            // const data = await response.json();
            // console.log("data : " + data.message);

            if(response.ok) {
                location.href = "/users/signin";
            } else {

            }

        } else {
            alert("회원가입 양식을 모두 채워주세요.");
        }
    });
})