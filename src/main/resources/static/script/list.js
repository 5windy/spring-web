document.addEventListener("DOMContentLoaded", async e => {
    const listContainer = document.getElementById("list-container");
    const navPaging = document.getElementById("nav-paging");

    const response = await fetch(`/boards/all?page=`);
    const result = await response.json();

    loadListElement(result.boards, listContainer);
    loadPagingElement(result.totalPages, navPaging, listContainer);
});

async function loadPagingElement(totalPages, parents, element) {
    parents.innerHTML = "";

    // totalPages 크기만큼의 페이지 버튼 생성
    // ㄴ 각 버튼은 요청 페이지가 서로 다름
    for(let i=0; i<totalPages; i++) {
        const pageBtn = document.createElement("span");
        pageBtn.innerText = i + 1;
        pageBtn.addEventListener("click",  async e => {
            const response = await fetch(`/boards/all?page=${i + 1}`);
            const result = await response.json();

            // 목록의 엘리먼트 모두 삭제 -> 갱신
            loadListElement(result.boards, element);
        });
        parents.appendChild(pageBtn);
    }

}

function loadListElement(boards, parents) {
    parents.innerHTML = "\n" +
        "            <div id=\"list-header\" class=\"list-element\">\n" +
        "                <div class=\"title\">제목</div>\n" +
        "                <div class=\"author\">작성자</div>\n" +
        "                <div class=\"reg-date\">작성일</div>\n" +
        "                <div class=\"mod-date\">수정일</div>\n" +
        "            </div>";

    boards.forEach(board => {
        const code = board.code;
        const title = board.title;
        const author = board.author;
        const content = board.content;
        const regDate = board.regDate;
        const modDate = board.modDate;

        parents.innerHTML += `
            <div id="board-${code}" class="list-element">
                <div class="title">${title}</div>
                <div class="author">${author}</div>
                <div class="reg-date">${regDate.split('T')[0]}</div>
                <div class="mod-date">${modDate.split('T')[0]}</div>
            </div>
        `;
    });
}
