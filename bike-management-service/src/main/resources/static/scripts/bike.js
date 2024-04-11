function saveBike() {
    var bikeId = document.getElementsByName('bike-card')[0].id;
    var wheelSize = document.getElementById('wheelSize').value;
    var startRentingDate = document.getElementById('startRentingDate').value;
    var endRentingDate = document.getElementById('endRentingDate').value;
    var numberOfGears = document.getElementById('numberOfGears').value;
    var brand = document.getElementById('brand').value;
    var color = document.getElementById('color').value;
    var type = document.getElementById('type').value;
    var suitability = document.getElementById('suitability').value;

    var bike = {
        id: bikeId,
        wheelSize: wheelSize,
        startRentingDate: startRentingDate,
        endRentingDate: endRentingDate,
        numberOfGears: numberOfGears,
        brand: brand,
        color: color,
        type: type,
        suitability: suitability
    };

    var xhr = new XMLHttpRequest();
    xhr.open('PUT', '/bike');
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status === 200) {
            var successMessage = document.getElementById('success-message')
            successMessage.style.display = 'block'
        } else {
            console.error('Error updating bike data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };

    var jsonData = JSON.stringify(bike);

    xhr.send(jsonData);

}

function addBike() {
    var wheelSize = document.getElementById('wheelSize').value;
    var startRentingDate = document.getElementById('startRentingDate').value;
    var endRentingDate = document.getElementById('endRentingDate').value;
    var numberOfGears = document.getElementById('numberOfGears').value;
    var brand = document.getElementById('brand').value;
    var color = document.getElementById('color').value;
    var type = document.getElementById('type').value;
    var suitability = document.getElementById('suitability').value;
    var name = document.getElementById('name').value;

    var bike = {
        wheelSize: wheelSize,
        startRentingDate: startRentingDate,
        endRentingDate: endRentingDate,
        numberOfGears: numberOfGears,
        brand: brand,
        color: color,
        type: type,
        suitability: suitability,
        name: name,
        userId: +window.location.href.substring(window.location.href.lastIndexOf('/') + 1)
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/bike');
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status === 200) {
            uploadImage(xhr.response);
        } else {
            console.error('Error updating bike data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };

    var jsonData = JSON.stringify(bike);

    xhr.send(jsonData);
}

function uploadImage(bikeId) {
    var imageXhr = new XMLHttpRequest();
    imageXhr.open('POST', '/upload/' + bikeId);

    var formData = new FormData();
    formData.append('file', document.getElementById('image').files[0]);

    imageXhr.onload = function () {
        if (imageXhr.status === 200) {
            var successMessage = document.getElementById('success-message');
            successMessage.style.display = 'block';
        } else {
            console.error('Error uploading image:', imageXhr.statusText);
        }
    };

    imageXhr.onerror = function () {
        console.error('Image upload failed');
    };

    imageXhr.send(formData);
}

function deleteBike() {
    var bikeId = document.getElementsByName('bike-card')[0].id;

    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', '/bike/' + bikeId);

    xhr.onload = function () {
        if (xhr.status === 200) {
            var successMessage = document.getElementById('delete-message');
            successMessage.style.display = 'block';
        } else {
            console.error('Error updating bike data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };

    xhr.send(null);
}