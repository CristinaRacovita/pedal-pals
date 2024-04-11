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
            console.log("It worked!")
        } else {
            console.error('Error updating bike data:', xhr.statusText);
        }
    };

    xhr.onerror = function () {
        console.error('Request failed');
    };
}
