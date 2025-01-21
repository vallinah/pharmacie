export default class Accordion {

    #bodyHandler;

    /**
     * @param {HTMLElement} parent
     */
    constructor(parent) {
        this.parent  = parent;
        this.#inConstructeur();

        this.#bodyHandler = ev => {
            if (!this.parent.contains(ev.target)) {
                this.parent.classList.remove("active");
                document.body.removeEventListener("click", this.#bodyHandler);
                this.body.style.height = "0px";
            }
        }
    }

    #inConstructeur() {
        this.#init();
        this.#handler();
    }

    #init() {
        this.head = this.parent.querySelector(".acc_ttr");
        this.body = this.parent.querySelector(".acc_body");
    }

    #handler() {
        this.head.addEventListener("click" , () => {
            if (this.parent.classList.contains("active")) {
                this.parent.classList.remove("active");
                this.body.style.height = "0px";
            } else {
                this.parent.classList.add("active");
                document.body.addEventListener('click', this.#bodyHandler);
                this.body.style.height = this.body.scrollHeight + "px";
            }
        })
    }
}