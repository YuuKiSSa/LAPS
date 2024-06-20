window.onload = function(){
    var applicationId = document.getElementById("applicationId").textContent.trim();
    var status = document.getElementById("status").textContent.trim();
    document.getElementById("editButton").addEventListener("click", function(){
        if (status === "Applied" || status === "Updated"){
            window.location.href = "/staffDashboard/editApplication?applicationId=" + applicationId;
        }else{
            alert("This application can not be modified now.");
        }

    });

    document.getElementById("cancelButton").addEventListener('click', function(){
        if (status === "Rejected"){
            alert("You can't modify this application, because it has been rejected.")
        }else if (status === "Cancel"){
            alert("This application has been canceled.");
        }else if (status === "Deleted"){
            alert("This application has been deleted.");
        }else{
            window.location.href = "/staffDashboard/cancelApplication?applicationId=" + applicationId;
        }

    });
}
