//import React from 'react'
//import Axios from 'axios'
function get(props){

var axios = require('axios');
var data = 'from react';



var config = {
  method: 'get',
  url: 'http://192.168.0.115:5542',
  headers: { },
  data : data
};

axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});

}
return get




