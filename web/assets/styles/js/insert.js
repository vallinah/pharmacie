import Crud from "./CRD.js";
let crd = new Crud();
let form = document.querySelector("form");
form.addEventListener('submit', (e) => {
    e.preventDefault();
    crd.doGet(form);
});