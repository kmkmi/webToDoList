
window.onload = function () {
  var today = new Date();
  var yyyy = today.getFullYear();
  var date = today.getDate();
  var mm = today.getMonth() + 1;
  var hh = today.getHours() + 1;


  if (hh < 10) {
    hh = "0" + hh;
  }
  if (mm < 10) {
    mm = "0" + mm;
  }
  if (hh == 24) {
    hh = "00";
    date += 1;
  }
  if (date < 10) {
    date = "0" + date;
  }


  today = yyyy + "-" + mm + "-" + date;


  document.getElementById("date").value = today;
  document.getElementById("date").setAttribute("min", today);

  today = "2100-12-31";
  document.getElementById("date").setAttribute("max", today);
  document.getElementById("time").value = hh + ":00";
}

function submitting() {

  var date = document.getElementById("date").value;
  var time = document.getElementById("time").value;



  var dl = date+ " " +time;

  document.getElementById("deadLine").value = dl;

  var isOn = document.getElementById("dl_check").checked;
  if(!isOn) {
    document.getElementById("deadLine").value = "9999-12-31 00:00";
  }
  return true;
}