function addReview() {
    var title = document.getElementById('title').value;
    var numberOfStars = document.getElementById('numberOfStars').value;
    var review = document.getElementById('review').value;

    var feedback = {
        numberOfStars: numberOfStars,
        title: title,
        date: new Date(),
        review: review,
        bikeId: window.location.href.substring(window.location.href.lastIndexOf('/') + 1),
        reviewerId: +window.location.href.substring(window.location.href.lastIndexOf('/') + 2)
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/feedback');
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status === 200) {
            var successMessage = document.getElementById('success-message');
            successMessage.style.display = 'block';
        } else {
            console.error('Error adding review data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };

    var jsonData = JSON.stringify(feedback);

    xhr.send(jsonData);
}