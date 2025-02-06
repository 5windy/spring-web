document.addEventListener("DOMContentLoaded", e => {
    const form = document.querySelector("form");
    const inputUsername = form.querySelector("#username");

    inputUsername.addEventListener("change", e => {
        const username = e.target.value;
        console.log('username : ', username);
    })

    form.addEventListener("submit", e => {
        e.preventDefault();



        // form.submit();
    })
})