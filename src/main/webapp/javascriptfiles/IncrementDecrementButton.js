/**
 * 
 */

 var x = 0;

document.getElementsByTagName('span').innerHTML = x;

function button1() {
  document.getElementsByTagName('span').innerHTML = ++x;
}

function button2() {
if (x > 1) {
    document.getElementsByTagName('span').innerHTML = --x;
  }
}