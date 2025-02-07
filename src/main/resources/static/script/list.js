document.addEventListener("DOMContentLoaded", async e => {
    const listContainer = document.getElementById("list-container");
    const navPaging = document.getElementById("nav-paging");

    const response = await fetch(`/boards/all`);
    const result = await response.json();

    listContainer.innerHTML = "\n" +
        "            <div id=\"list-header\" class=\"list-element\">\n" +
        "                <div class=\"title\">제목</div>\n" +
        "                <div class=\"author\">작성자</div>\n" +
        "                <div class=\"reg-date\">작성일</div>\n" +
        "                <div class=\"mod-date\">수정일</div>\n" +
        "            </div>";

    result.boards.forEach(board => {
        const code = board.code;
        const title = board.title;
        const author = board.author;
        const content = board.content;
        const regDate = board.regDate;
        const modDate = board.modDate;

        listContainer.innerHTML += `
            <div id="board-${code}" class="list-element">
                <div class="title">${title}</div>
                <div class="author">${author}</div>
                <div class="reg-date">${regDate.split('T')[0]}</div>
                <div class="mod-date">${modDate.split('T')[0]}</div>
            </div>
        `;
    });

    navPaging.innerHTML = "";
    for(let i=0; i<result.totalPages; i++) {
        navPaging.innerHTML += `
            // 페이지 버튼 그리기
        `;
    }

});