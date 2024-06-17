document.addEventListener('DOMContentLoaded', function() {
    var startDatePicker = flatpickr("#startTime", {
        dateFormat: "Y-m-d",
        enableTime: true
    });
    var endDatePicker = flatpickr("#endTime", {
        dateFormat: "Y-m-d",
        enableTime: true
    });
});

$(document).ready(function(){
    $('#startTime').change(function(){
        syncEndDate();
    })

    function checkCompensationLeave(){
        var applicationType = $('#applicationType').val();
        if(applicationType === "Compensation Leave"){
            var startDate = $('#startTime').val();
            syncEndDate();
        }
    }

    function syncEndDate() {
        var applicationType = $('#applicationType').val();
        if (applicationType === "Compensation Leave") {
            var startTime = $('#startTime').val();
            $('#endTime').val(startTime);
        }
    }
    checkCompensationLeave();
});

