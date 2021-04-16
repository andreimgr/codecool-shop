document.getElementById('supplier').onchange = function() {
    localStorage.setItem('selectedtem', document.getElementById('supplier').value);
};

if (localStorage.getItem('selectedtem')) {
    document.getElementById('supplier').options[localStorage.getItem('selectedtem')].selected = true;
}


document.getElementById('category').onchange = function() {
    localStorage.setItem('selectedtem', document.getElementById('category').value);
};

if (localStorage.getItem('selectedtem')) {
    document.getElementById('category').options[localStorage.getItem('selectedtem')].selected = true;
}