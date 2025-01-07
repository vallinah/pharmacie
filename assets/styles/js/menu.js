import Accordion from "./accordion.js";
const menu = document.querySelector("menu");

const menu_control = document.getElementById("menu_control");
menu_control.addEventListener('click', () => {
    menu.classList.toggle("hide");
})

let accordions = document.querySelectorAll(".acc");

accordions.forEach(el => {
    new Accordion(el);
})