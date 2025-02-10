
// <!-- <==============HEADER==============> -->

// let searh = document.getElementById("search");
// let search_container = document.querySelector(".search")
// searh.addEventListener('click', () => {
//     search_container.classList.toggle('active')
// })

// <!-- <==============MENU==============> -->

const menu = document.querySelector("menu");
if (menu) {
    const menu_active = document.querySelector(".active_menu");
    const menu_close = document.querySelector(".close_menu")
    menu_active?.addEventListener("click", () => {
        if (menu.classList.contains("active")) {
            menu.classList.remove("active")
        } else {
            menu.querySelectorAll(".active").forEach(el => {
                el.dispatchEvent(new CustomEvent("close"))
                el.classList.remove("active")
            })
            menu.classList.add("active");
        }
    })

    menu_close?.addEventListener('click', () => {
        menu.querySelectorAll(".active").forEach(el => {
            el.dispatchEvent(new CustomEvent("close"))
            el.classList.remove("active")
        })
        menu.classList.add("active")
    })
}


// ! <!-- <------------MODAL------------> -->
let modal = document.querySelector(".modal")

/**
 * @param {HTMLElement} el 
 */
export function activeModal(el) {
    let mapping = el.dataset.active;
    modal.querySelectorAll('.modal_md').forEach(el => {
        if (mapping == el.dataset.mapping) {
            el.classList.add('show');
        } else {
            el.classList.remove("show");
        }
    })
    modal.classList.add("active");
}

if (modal) {
    // Active modal
    document.querySelectorAll(".modal_active").forEach(el => {
        el.addEventListener('click', () => {
            activeModal(el)
        })
    })

    // Close modal
    document.querySelectorAll(".modal_close").forEach(el => {
        el.addEventListener('click', () => {
            modal.classList.remove("active")
        })
    })
}

// ! <!-- <------------ACCORDION------------> -->

class Accordion {

    /** @type {HTMLDivElement} */
    #header;
    /** @type {HTMLDivElement} */
    #body;
    /** @type {NodeList} */
    #child_acc;
    #bodyHeight = 0;

    /**
     * @param {Event} e 
     */
    #handlerBody = e => {
        if (!this.parent.contains(e.target)) {
            this.desactive();
        }
    }

    /**
     * @param {HTMLDivElement} parent
     */
    constructor(parent, conf = {
        body_handler: true,
    }) {
        this.conf = conf;
        if (parent) {
            this.parent = parent;
            this.#init();
        }
    }

    #init() {
        this.#header = this.parent.querySelector(".acc_head");
        this.#body = this.parent.querySelector(".acc_body");
        this.#child_acc = this.parent.querySelectorAll(".acc_body > .acc");
        this.#child_acc = [...this.#child_acc].filter((e) => {
            return e.parentNode == this.#body;
        })
        if (this.parent.classList.contains("active")) {
            this.active();
        }
        this.#event();
    }

    #event() {
        this.parent.addEventListener("close", () => {
            this.desactive();
        })
        this.#child_acc.forEach(el => {            
            el.addEventListener("size", ({detail}) => {
                let { height, active } = detail;
                if (active) {
                    this.changeBodyHeight = this.#bodyHeight + height;
                    this.parent.dispatchEvent(new CustomEvent('size', {
                        detail: {
                            height: height,
                            active: true
                        },
                    }))
                } else {
                    this.changeBodyHeight = this.#bodyHeight - height;
                    this.parent.dispatchEvent(new CustomEvent('size', {
                        detail: {
                            height: height,
                            active: false
                        },
                    }))
                }
            })
        })
        this.#header.addEventListener('click', e => {
            if (this.parent.classList.contains('active')) {
                this.desactive();
            } else {
                this.active();
            }
        })
    }

    active() {
        if (!this.conf.body_handler) {
           let hafa = this.parent.parentElement.querySelector(".acc.active");
           hafa?.dispatchEvent(new CustomEvent("close"));
        }
        this.parent.classList.add("active");
        this.changeBodyHeight = this.#body.scrollHeight;  
        this.parent.dispatchEvent(new CustomEvent('size', {
            detail: {
                height: this.#bodyHeight,
                active: true
            },
        }))
        if (this.conf.body_handler) {
            addEventListener('click', this.#handlerBody, true);
        }
    }

    desactive() {
        this.parent.classList.remove("active");
        this.parent.dispatchEvent(new CustomEvent('size', {
            detail: {
                height: this.#bodyHeight,
                active: false
            },
        }))
        this.changeBodyHeight = 0;
        if (this.conf.body_handler) {
            removeEventListener('click', this.#handlerBody, true);
        } else {
            this.#child_acc.forEach(el => {
                el.dispatchEvent(new CustomEvent("close"));                
            })
        }
    }

    /**
     * @param {any} value
     */
    set changeBodyHeight(value) {
        this.#body.style.height  = value + "px";
        this.#bodyHeight = value;
    }
}

let listAcc = document.querySelectorAll(".acc");

listAcc.forEach(el => {
    new Accordion(el, {
        body_handler: false
    })
})

let currentPath = window.location.href;

for (let acc of listAcc) {
    let acc_body = acc.querySelectorAll(".acc_body > li");    
    for (let li of acc_body) {        
        if (li.querySelector("a").href == currentPath) {
            li.classList.add("active");
            break;
        }
    }
}

// ! <!-- <------------UPLOAD------------> -->

let form_upload = document.querySelector(".form-upload");

if (form_upload) {
    let input_file = form_upload.querySelector("input[type='file']");
    let file_image = form_upload.querySelector(".file-img");

    /**
     * @param {[File]} array_img 
     */
    let updateFile = (array_img) => {
        let data_transfert = new DataTransfer();
        array_img.forEach(file => {
            data_transfert.items.add(file);
        })
        input_file.files = data_transfert.files;
    }

    let removeFile = (index) => {
        let file_list = [...input_file.files];
        file_list.splice(index, 1);
        updateFile(file_list);
    }

    let addFileInDom = () => {
        let files = [...input_file.files];
        file_image.innerHTML = "";
        files.forEach((el, index) => {
            const reader = new FileReader();
            reader.readAsDataURL(el);
            reader.onload = () => {
                let div = document.createElement('div');
                div.className = 'list-img'
                div.innerHTML = `
                <span class="image-remove" data-index='${index}'>X</span>
                <img src="${reader.result}">`;
                let rmv_btn = div.querySelector(".image-remove");

                rmv_btn.addEventListener('click', () => {
                    let index = rmv_btn.dataset.index;
                    removeFile(index);
                    rmv_btn.parentElement.remove();
                })
                file_image.appendChild(div);
            }
        })
    }
    
    input_file.addEventListener('change', addFileInDom)
}

const textarea = document.querySelectorAll("textarea");

textarea.forEach(el => {
    const adjustHeight = () => {
        el.style.height = 'auto';
        el.style.height = el.scrollHeight + 'px';
    };
    
    el.addEventListener('input', adjustHeight);
    adjustHeight();
})

// <!-- <==============MAIN==============> -->