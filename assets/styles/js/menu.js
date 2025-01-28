import Accordion from "./accordion.js";

let menu = document.querySelector("menu");
let activeMenu = document.querySelector(".bi-list");
activeMenu.addEventListener('click', () => {
    menu.classList.toggle("active");
})

let accordions = document.querySelectorAll(".acc");

accordions.forEach(el => {
    new Accordion(el);
})