export default class Crud {
    constructor() {}

    #toFormdata(formulaire) {
        let fomdata = new FormData(formulaire);
        let parseUrl = new URLSearchParams(fomdata);
        return parseUrl.toString();
    }

    #getAction(formulaire) {
        return formulaire.getAttribute("action");
    }

    #sendAjax(url, formulaire, methode = "GET") {
        let xhr = new XMLHttpRequest();
        xhr.open(methode, url);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                switch (xhr.status) {
                    case 500:
                        console.log("misy erreur");
                        break;
                    default:
                        console.log("vita");
                        break;
                }
            }
        }
        xhr.send(this.#toFormdata(formulaire));
    }

    doGet(formulaire) {
        this.#sendAjax(this.#getAction(formulaire), formulaire);
    }

    doPost(formulaire) {
       this.#sendAjax(this.#getAction(formulaire), formulaire, "POST")
    }
    
    doDelete(lien) {
        let xhr = new XMLHttpRequest();
        xhr.open("DELETE", lien.href);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                switch (xhr.status == 200) {
                    case 500:
                        console.log("misy olana");
                        break;
                    default:
                        console.log("voafafa");
                        break;
                }
            }
        }
        xhr.send();
    }
}