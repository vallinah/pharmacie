import Accordion from "./accordion.js";

let accordions = document.querySelectorAll(".acc");

accordions.forEach(el => {
    new Accordion(el);
})