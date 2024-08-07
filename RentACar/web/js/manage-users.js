$(document).ready(function () {
  // Activate tooltip
  $('[data-toggle="tooltip"]').tooltip();
  // Select/Deselect checkboxes
  var checkbox = $('table tbody input[type="checkbox"]');
  $("#selectAll").click(function () {
    if (this.checked) {
      checkbox.each(function () {
        this.checked = true;
      });
    } else {
      checkbox.each(function () {
        this.checked = false;
      });
    }
  });

  checkbox.click(function () {
    if (!this.checked) {
      $("#selectAll").prop("checked", false);
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
  var button = $(event.relatedTarget); 
  var userUID = button.data("useruid"); 
  var modal = $(this);

  var checkedValues = [];
  var checkbox = $('table tbody input[type="checkbox"]');
  checkbox.each(function () {
    if (this.checked) {
      var value = $(this).val();
      checkedValues.push(value);
    }
  });

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