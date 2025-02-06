document.addEventListener("DOMContentLoaded", e => {
    const logoutButton = document.getElementById("logout");
    logoutButton.addEventListener("click", async e => {
        const response = await fetch("/users/signout");

        if(response.ok) {
            location.href = "/";
        }
    })
})