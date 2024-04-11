function goToMyRentalBikes() {
    var userId = document.getElementsByName('header')[0].id;
    window.location.href = "http://localhost:8083/" + userId + "/my-bikes";
}