function approve(sentRequestId, sentApprovalStatus) {
    var approval = {
        requestId: sentRequestId,
        approvalStatus: sentApprovalStatus,
        details: document.getElementById(sentRequestId).value
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/approve');
    xhr.setRequestHeader('Content-Type', 'application/json');
    var jsonData = JSON.stringify(approval);
    xhr.send(jsonData);

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('success-approval-decline ' + sentRequestId);
            console.log("It worked!")
            var successMessage = document.getElementById('success-approval-decline ' + sentRequestId);
            successMessage.style.display = 'flex';
        } else {
            console.error('Error updating bike data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };
}


function addFeedback(bikeId) {
    window.location.href = "http://localhost:8081/new-review/" + bikeId;
}


function cancelRequest(requestId) {
    var request = {
        status: "cancelled"
    };

    var xhr = new XMLHttpRequest();
    xhr.open('PATCH', '/requests/' + requestId);
    xhr.setRequestHeader('Content-Type', 'application/json');
    var jsonData = JSON.stringify(request);
    xhr.send(jsonData);

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log("It worked!")
            var successMessage = document.getElementById('success-cancel ' + requestId);
            successMessage.style.display = 'flex';
        } else {
            console.error('Error canceling request:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };
}

