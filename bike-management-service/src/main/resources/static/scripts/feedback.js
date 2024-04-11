function goToFeedback() {
    var userId = document.getElementsByName('header')[0].id;
    var bikeId = document.getElementsByName('bike-card')[0].id;

    window.location.href = "http://localhost:8081/" + userId + "/rating/" + bikeId;
}