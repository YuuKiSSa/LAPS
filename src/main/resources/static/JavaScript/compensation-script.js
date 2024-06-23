document.addEventListener('DOMContentLoaded', function() {
    var startDatePicker = flatpickr("#startTime", {
        dateFormat: "Y-m-d H:i",
        enableTime: true
    });
    var endDatePicker = flatpickr("#endTime", {
        dateFormat: "Y-m-d H:i",
        enableTime: true
    });
});
$(document).ready(function(){
    document.addEventListener("DOMContentLoaded", function() {
        var applicationForm = document.getElementById("applicationForm");
        var applicationId = document.getElementById("applicationId");

        if (applicationId === null) {
            applicationForm.action = "/staffDashboard/createApplication";
        } else {
            applicationForm.action = "/staffDashboard/editApplication";
        }
    });
});
