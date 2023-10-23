// get the handlers
const addForm = document.querySelector(".add");
const list = document.querySelector(".rants");
const search = document.querySelector(".search input");

// func to generate rant template
const generateRantTemplate = (rant) => {
  let html = `
    <li class="list-group-item d-flex justify-content-between align-items-center" >
        <span>${rant}</span>
        <i class="far fa-trash-alt delete"></i>
    </li>
    `;

  //   list.innerHTML += html;
  list.insertAdjacentHTML("afterbegin", html);
};

// funct to filter rants
const filterRants = (term) => {
  console.log(list.children);
  // get all rants from the list; convert the HTML collection to array
  // all rants that doesn't have the term will be filtered out
  Array.from(list.children)
    .filter((rant) => !rant.textContent.toLocaleLowerCase().includes(term))
    .forEach((rant) => rant.classList.add("filtered"));

  // do the opposite of the above, that is
  // remove the 'filtered' class no longer matching the term
  Array.from(list.children)
    .filter((rant) => rant.textContent.toLocaleLowerCase().includes(term))
    .forEach((rant) => rant.classList.remove("filtered"));
};

// create event listener for the add form
addForm.addEventListener("submit", (e) => {
  // prevent browser's default submit action
  e.preventDefault();

  // get the value from input form with name 'add'
  const rant = addForm.add.value.trim();

  // pass the rant to the func; check first
  if (rant.length) {
    generateRantTemplate(rant);
    // reset the input field
    addForm.reset();
  }
});

// create event listener for the delete functionality
list.addEventListener("click", (e) => {
  if (e.target.classList.contains("delete")) {
    e.target.parentElement.remove();
  }
});

// create event listener for search functionality
search.addEventListener("keyup", () => {
  const term = search.value.toLocaleLowerCase().trim();

  // pass the term to the func
  filterRants(term);
});
