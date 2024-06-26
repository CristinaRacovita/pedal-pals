function goToMyBikes() {
    var userId = +getUserData('userId');
    window.location.href = "/bikes/user/" + userId;
}

function getUserData(name) {
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
    var userId = +getUserData('userId');
    window.location.href = "http://localhost:8083/rentals/" + userId;
}

function goToNotifications() {
    var userId = +getUserData('userId');
    window.location.href = "http://localhost:8085/notifications/" + userId;
}