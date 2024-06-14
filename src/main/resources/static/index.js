$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "api/soccerplayers",
    success: function (data) {
      for (var i = 0; i < data.length; i++) {
        var row =
          "<tr id='" +
          data[i].id +
          "'><td>" +
          (i + 1) +
          "</td><td>" +
          data[i].name +
          "</td><td>" +
          data[i].number +
          "</td><td>" +
          data[i].position +
          "</td><td>" +
          data[i].teamName +
          "</td><td><button class='btn btn-danger deleteBtn' data-id='" +
          data[i].id +
          "'>Delete</button></td></tr>";

        $("#playerTable").append(row);
      }
    },
  });

  // add new player
  $("#addPlayer").click(function () {
    var name = $("#name").val();
    var number = $("#number").val();
    var position = $("#position").val();
    var teamName = $("#teamName").val();
    $.ajax({
      type: "POST",
      url: "api/soccerplayers",
      data: JSON.stringify({
        name: name,
        number: number,
        position: position,
        teamName: teamName,
      }),
      contentType: "application/json",
      success: function (data) {
        // get the last row number #
        var rowCount = $("#playerTable tr").length;
        var row =
          "<tr id='" +
          data.id +
          "'><td>" +
          (rowCount + 1) +
          "</td><td>" +
          data.name +
          "</td><td>" +
          data.number +
          "</td><td>" +
          data.position +
          "</td><td>" +
          data.teamName +
          "</td><td><button class='btn btn-danger deleteBtn' data-id='" +
          data.id +
          "'>Delete</button></td></tr>";
        $("#playerTable").append(row);

        // clear input fields
        $("#name").val("");
        $("#number").val("");
        $("#position").val("");
        $("#teamName").val("");

        // close the modal id registrationModal
        $("#registrationModal").modal("toggle");
      },
    });
  });

  // delete player using event delegation
  $("#playerTable").on("click", ".deleteBtn", function () {
    var id = $(this).data("id");
    $.ajax({
      type: "DELETE",
      url: "api/soccerplayers/" + id,
      success: function () {
        // find the row and remove it
        $("#" + id).remove();

        // update the row number
        var rowCount = $("#playerTable tr").length;
        var row = $("#playerTable tr");
        for (var i = 0; i < rowCount; i++) {
          row[i].cells[0].innerHTML = i + 1;
        }
      },
    });
  });
});
