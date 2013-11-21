/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var fusionFormatDateTime = "DD/MM/YYYY HH:mm:ss";
var fusionFormatDate = "DD/MM/YYYY";


function formatDateTime(val, row) {

  var now = new Date(val);

return moment(now).format(fusionFormatDateTime);
}


function formatDate(val, row) {
// Create a date object with the current time
  var now = new Date(val);
 
return moment(now).format(fusionFormatDate);
}


function parseDate(val) {
 
    if (val == ''){
        return new Date();
    }

  if (isNaN(val)){
      return  moment(val, fusionFormatDate).toDate();
  }else{
      return  new Date(val);
  }

}

function parseDateTime(val) {
 
    if (val == ''){
        return new Date();
    }

  if (isNaN(val)){
      return  moment(val, fusionFormatDateTime).toDate();
  }else{
      return  new Date(val);
  }

}


function formatInputDateTime(now) {

return moment(now).format(fusionFormatDateTime);
}

function formatInputDate(now) {

return moment(now).format(fusionFormatDate);
}



