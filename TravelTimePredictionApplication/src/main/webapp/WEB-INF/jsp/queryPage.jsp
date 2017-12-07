<html>


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
<body>
<form method="post">
<table>
   <tr>
       <td>Enter Route ID :  </td>
       <td>
		<select id="routeid" name="routeid">
		  <option disabled selected value> -- select an option -- </option>
		  <option value="01">Andheri(East) to Accenture Office(Airoli), Mumbai</option>
		  <option value="02">Bayadarahalli to Accenture Office(Pritech Road),Bangalore</option>
		  <option value="03">Thane(West) to Accenture Office(Airoli),Mumbai</option>
		  <option value="04">Wakad to Accenure Office Hinjawadi,Pune</option>
		</select>  
   </tr>
   
   <!--
   <tr>
       <td>Enter weatherType:  </td>
       <td>
		<select id="weathercode">
		  <option disabled selected value> -- select an option -- </option>
		  <option value="711">Smoke</option>
		  <option value="800">Clear sky</option>
		  <option value="701">Mist</option>
		  <option value="721">Haze</option>
		  <option value="802">Scattered clouds</option>
		  <option value="500">Light rain</option>
		</select>
	   </td>
   </tr>
   -->
   
   <tr>
       <td>Enter time of travel :</td>
       <td><input type="datetime-local" step="1" id="timestamp" name="timestamp" size="10"/>  
   </tr>
   <tr></tr>
   <tr></tr>
   <tr> 
		<td></td><td><input type="submit" class="button" value="Get Details" /></td>
   </tr>
  

   </table>
   </form>
</body>
</html> 