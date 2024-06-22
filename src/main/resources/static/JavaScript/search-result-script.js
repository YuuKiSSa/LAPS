function enableEdit(button) {
    var inputFields = button.parentNode.parentNode.querySelectorAll('input');
    inputFields.forEach(function(input) {
        if (input.name !== 'userId') { // Ensure userId remains readonly
            input.removeAttribute('readonly');
        }
    });
}