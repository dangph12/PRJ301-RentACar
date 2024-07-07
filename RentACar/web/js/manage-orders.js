$("#confirmReceivedCarModal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  var orderUID = button.data("orderuid"); // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this);

  var checkedValues = [];
  var checkbox = $('table tbody input[type="checkbox"]');
  checkbox.each(function () {
    if (this.checked) {
      var value = $(this).val();
      checkedValues.push(value);
    }
  });

  if (orderUID != null) {
    modal.find('input[name="orderUID"]').val(orderUID);
  } else {
    modal.find('input[name="orderUID"]').val(checkedValues);
  }
});

$("#confirmReturnedCarModal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  var orderUID = button.data("orderuid"); // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this);

  var checkedValues = [];
  var checkbox = $('table tbody input[type="checkbox"]');
  checkbox.each(function () {
    if (this.checked) {
      var value = $(this).val();
      checkedValues.push(value);
    }
  });

  if (orderUID != null) {
    modal.find('input[name="orderUID"]').val(orderUID);
  } else {
    modal.find('input[name="orderUID"]').val(checkedValues);
  }
});

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
