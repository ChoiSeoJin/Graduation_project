import React from 'react';
import '../css/Header.css';

class Header extends React.Component {

    render(){    	
        return (
       
        	<div className="hello">   
 				<div className="div_logo">
 				<a href="">
 				<img className="logoimage"src={require('../images/logo.jpg')} />
 				</a>
 				<span className="category_name">
 					 KAMLAB
 				</span>
 				
 				</div>
 				
 				<ul>
				  <li><a href="joblist">JOB LIST</a></li>
				  <li><a href="jobcreate">JOB CREATE</a></li>
				  <li className="dropdown">
				    <a href="javascript:void(0)" className="dropbtn">UPLOAD</a>
				    <div className="dropdown-content">
				      <a href="/fileUpload/file">File</a>
				      <a href="/fileUpload/component">Component</a>
				      <a href="/fileUpload/script">Script</a>
				    </div>
				  </li>
				</ul>
				
	        </div>
	        		
			
        );
    }
}


export default Header;