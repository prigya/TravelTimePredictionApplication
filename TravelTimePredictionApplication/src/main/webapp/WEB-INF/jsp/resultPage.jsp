<html>
<body>

<title>Welcome to Travel Time Predictor</title>

<head>
<style>
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
</style>

<h1 style="background-color:MediumSeaGreen;text-align:center;">Welcome to Travel Time Predictor</h1></head>


<table>
   <tr>
       <td>Route ID :  </td>
       <td>${routeid}</td>
		
   </tr>
   
  
   <tr>
       <td>Time of travel :</td>
       <td>${timestamp}</td>  
   </tr>
   <tr></tr>
   <tr></tr>
   
   <tr>
       <td></td>
	   <td></td>
       <td><h2>${pred}<h2></td>
   </tr>
   <tr>
       <td></td>
	   <td></td>
       <td><h2>${weather}&deg;C</h2></td>
   </tr>
   <tr>
       <td></td>
	   <td></td>
       <td><h2>${accuracy}</h2></td>
   </tr>

   </table>
</body>
</html> 