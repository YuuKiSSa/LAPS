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