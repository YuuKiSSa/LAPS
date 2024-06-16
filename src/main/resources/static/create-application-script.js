document.addEventListener('DOMContentLoaded', function() {
    var startDatePicker = flatpickr("#startDate", {
        dateFormat: "Y-m-d",
        enableTime: true
    });
    var endDatePicker = flatpickr("#endDate", {
        dateFormat: "Y-m-d",
        enableTime: true
    });

    $("#applicationType").change(function(){
        var selectedValue = $(this).val();
        if (selectedValue === "Compensation"){
            startDatePicker.set("dateFormat", "Y-m-d H:i");
            endDatePicker.set("dateFormat", "Y-m-d H:i");
        }else{
            startDatePicker.set("dateFormat", "Y-m-d");
            endDatePicker.set("dateFormat", "Y-m-d");
        }
    });
});

$(document).ready(function(){
    $("#applicationType").change(function(){
       var selectedValue = $(this).val();
       if (selectedValue === "Compensation Leave"){
           $("#select").html("<label>Select Time:</label>\n" +
               "<select id='selectTime' name='selectTime'>\n" +
               "<option value='Morning'>Morning</option>\n" +
               "<option value='Afternoon'>Afternoon</option>\n" +
               "</select>");
       }else{
           $("#select").empty();
       }
    });
});
