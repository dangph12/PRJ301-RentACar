var checkedValues = [];
$(document).ready(function () {
  // Activate tooltip
  $('[data-toggle="tooltip"]').tooltip();
  // Select/Deselect checkboxes
  var checkbox = $('table tbody input[type="checkbox"]');
  $("#selectAll").click(function () {
    if (this.checked) {
      checkbox.each(function () {
        this.checked = true;
        var value = $(this).val();
        checkedValues.push(value);
      });
    } else {
      checkbox.each(function () {
        this.checked = false;
        var value = $(this).val();
        var index = checkedValues.indexOf(value);
        if (index > -1) {
          checkedValues.splice(index, 1);
        }
      });
    }
  });

  checkbox.click(function () {
    if (!this.checked) {
      $("#selectAll").prop("checked", false);
      var value = $(this).val();
      var index = checkedValues.indexOf(value);
      if (index > -1) {
        checkedValues.splice(index, 1);
      }
    } else {
      var value = $(this).val();
        checkedValues.push(value);
        if (checkbox.length == checkbox.filter(":checked").length) {
          $("#selectAll").prop("checked", true);
        }
    }
  });
});

var url = window.location;
// Will only work if string in href matches with location
$('ul.pagination a[href="' + url + '"]')
  .parent()
  .addClass("active");
// Will also work for relative and absolute hrefs
$("ul.pagination a")
  .filter(function () {
    return this.href == url;
  })
  .parent()
  .addClass("active");

$("#deleteUserModal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  var userUID = button.data("useruid"); // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this);

  console.log(checkedValues);
  console.log(userUID);

  if (userUID != null) {
    modal.find('input[name="userUID"]').val(userUID);
  } else {  
    modal.find('input[name="userUID"]').val(checkedValues);
  }
});

$("#editUserModal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal

  var userUID = button.data("useruid"); // Extract info from data-* attributes
  var fullName = button.data("fullname");
  var phone = button.data("phone");
  var email = button.data("email");
  var address = button.data("address");

  var modal = $(this);
  modal.find('input[name="userUID"]').val(userUID);
  modal.find('input[name="fullname"]').val(fullName);
  modal.find('input[name="phone"]').val(phone);
  modal.find('input[name="email"]').val(email);
  modal.find('textarea[name="address"]').val(address);
});
