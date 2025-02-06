document.addEventListener("DOMContentLoaded", e => {
    const form = document.querySelector("form");
    const inputUsername = form.querySelector("#username");
    const errorUsername = document.getElementById("error-msg-username");
    const inputNickname = form.querySelector("#nickname");
    const errorNickname = document.getElementById("error-msg-nickname");

    inputUsername.addEventListener("change", async e => {
        const username = e.target.value;

        const response = await fetch(`/users/valid/username?value=${username}`);
        if(response.ok) {
            errorUsername.style.display = "block";
        } else {
            errorUsername.style.display = "none";
        }
    });

    inputNickname.addEventListener("change", async e => {
        const nickname = e.target.value;

        const response = await fetch(`/users/valid/nickname?value=${nickname}`);
        if(response.ok) {
            errorNickname.style.display = "block";
        } else {
            errorNickname.style.display = "none";
        }
    });

    form.addEventListener("submit", e => {
        e.preventDefault();



        // form.submit();
    })
})