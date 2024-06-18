window.onload = function(){
    document.getElementById("editButton").addEventListener("click", function(){
        var applicationId = document.getElementById("applicationId").textContent.trim();
        window.location.href = "/staffDashboard/editApplication?applicationId=" + applicationId;
    });
}
