let thisPage = 1;
let limit = 9;
let list = document.querySelectorAll(".container .item");

function loadItem() {
  let beginGet = limit * (thisPage - 1);
  let endGet = limit * thisPage - 1;
  list.forEach((item, key) => {
    if (key >= beginGet && key <= endGet) {
      item.style.display = "block";
    } else {
      item.style.display = "none";
    }
  });
  listPage();
}

loadItem();

function listPage() {
  let count = Math.ceil(list.length / limit);
  document.querySelector(".listPage").innerHTML = "";

  if (thisPage != 1) {
    let prev = document.createElement("li");
    prev.innerText = "PREV";
    prev.setAttribute("onclick", "changePage(" + (thisPage - 1) + ")");
    document.querySelector(".listPage").appendChild(prev);
  }

  for (i = 1; i <= count; i++) {
    let newPage = document.createElement("li");
    newPage.innerText = i;
    if (i == thisPage) {
      newPage.classList.add("active");
    }
    newPage.setAttribute("onclick", "changePage(" + i + ")");
    document.querySelector(".listPage").appendChild(newPage);
  }

  if (thisPage != count) {
    let next = document.createElement("li");
    next.innerText = "NEXT";
    next.setAttribute("onclick", "changePage(" + (thisPage + 1) + ")");
    document.querySelector(".listPage").appendChild(next);
  }
}
function changePage(i) {
  thisPage = i;
  loadItem();
  window.scrollTo(0, 0);
}

// search content
var search = document.getElementById("search");
search.onkeyup = (e) => {
  const text = e.target.value;
  const items = document.querySelectorAll(".item");
  for (let i = 0; i < items.length; i++) {
    const matchText = items[i].querySelector(".title").innerText;
    console.log(text);
    console.log(matchText);
    if (matchText && matchText.toLowerCase().indexOf(text.toLowerCase()) > -1) {
      items[i].style.display = "block";
    } else {
      items[i].style.display = "none";
    }
  }
};
