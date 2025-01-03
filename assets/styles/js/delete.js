import Crud from "./CRD.js";

let crd = new Crud();

document.querySelectorAll(".action a:nth-child(2)")
.forEach(el => {
    el.addEventListener('click', e => {
        e.preventDefault();
        crd.doDelete(el, () => {
            let prt = el.parentElement;
            for (let a = 0; a < 2; a++) {
                prt = prt.parentElement;
            }
            prt.remove()
        });
    })
})