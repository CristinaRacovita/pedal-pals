function goToMyBikes() {
    var userId = +getUserId();
    window.location.href = "http://localhost:8082/bikes/user/" + userId;
}

function getUserId() {
    const name = 'userId';
    var keyName = name + "=";
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        if (cookie.indexOf(keyName) === 0) {
            return cookie.substring(keyName.length, cookie.length);
        }
    }
    return null;
}

function goToMyRentalBikes() {
    var userId = +getUserId();
    window.location.href = "http://localhost:8083/rentals/" + userId;
}