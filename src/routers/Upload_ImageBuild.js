import React from 'react';
import '../css/Body.css';
import Dropzone from 'react-dropzone';
import request from 'superagent';
import axios from 'axios';
import $ from "jquery";

class Upload_File extends React.Component {
	
	uploadAction() {  
		$.ajax({
			url : "http://localhost:8080/test6/testAction",
			dataType : "json",
			type : "POST",
			data : $('#boyru').serializeArray(),
			success:function(data){
				alert("perfect!" + data.KEY);
			},
			error:function(request, status, error){
				alert("oops!" + request + " ////// " + status +" /////" + error);
			}
		})  
	}	

	render(){
  	return(
  		<div className="body_main">
              <div className="body_head">
              Upload_Filedddas
              </div>
              <div className="body_head2">
				     	      
              	<form id="boyru">
	                 <input type="text" id="test" name="test" value="gg"/>
	                 <input type="text" id="test2" name="test2" value="gg2"/>
	                 <input type="button" value="upload" onClick={this.uploadAction.bind(this)}></input>
                 </form>
              </div>
            </div>
  	);
  }
};

export default Upload_File;