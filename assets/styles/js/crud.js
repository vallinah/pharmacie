const textarea = document.querySelector("textarea");

if (textarea) {
    const adjustHeight = () => {
        textarea.style.height = 'auto';
        textarea.style.height = textarea.scrollHeight + 'px'; 
    };
    
    textarea.addEventListener('input', adjustHeight);
    adjustHeight();
}