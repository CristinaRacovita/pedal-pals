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