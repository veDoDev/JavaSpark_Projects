<%@ page contentType = "text/html;charset=UTF-8"	%>
<%@ page import = "java.io.*"	%>
<%@ page import = "java.net.*"	%>
<%@ page import = "java.json.*"	%>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.JSONObject" %>

<html>
	<head>
		<title>
			Currency Converter
		</title>

		<link rel = "stylesheet" type = "text/css" href = "mystyle.css">
		<script src = "myjs.js"></script>
	</head>

	<body>
		<h1> Currecny Converter </h1>

		<form onsubmit="return check()">
    			<input 
        			type="number"
        			step="0.01"
        			name="inpCurr"
			        placeholder="Enter the amt in ₹"
			        id="id_input_currency"
			        required
		    	/>
    
    			<div class="radio-group">
			        <label><input type="radio" name="currChoice" value="USD" /> USD</label>
        			<label><input type="radio" name="currChoice" value="GBP" /> GBP</label>
			        <label><input type="radio" name="currChoice" value="SGD" /> SGD</label>
			        <label><input type="radio" name="currChoice" value="AUD" /> AUD</label>
			</div>

    			<input type="submit" name="btnConvert" value="Convert" />
		</form>


		<h2 id = "id_msg"></h2>

		<%
			if(request.getParameter("btnConvert") != null)
			{
				
				String convertToCurrency = request.getParameter("currChoice");

				try{
					String apiURL = 
						"https://api.exchangerate-api.com/v4/latest/" + convertToCurrency;


					URL url = new URL(apiURL);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();

					con.setRequestMethod("GET");

					InputStreamReader isr = new InputStreamReader(con.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					String jsonStr = "";
					String line = br.readLine();

					while(line != null)
					{
						jsonStr += line;
						line = br.readLine();
					}

					JSONObject jsonDict = new JSONObject(jsonStr);
					JSONObject rates = jsonDict.getJSONObject("rates");
					double convertedCurrencyRate = rates.getDouble("INR");

					double inputCurrency = Double.parseDouble(request.getParameter("inpCurr"));
					double outputAmount = inputCurrency / convertedCurrencyRate;
					
					String msg = 
			"₹" + inputCurrency + " = " + String.format("%.3f", outputAmount) + " " + convertToCurrency;

		%>
					<h2><%= msg %></h2>
		<%
				
				}catch(Exception e){
					out.println("Issue : " + e);
				}

				

			}
		%>

	</body>

</html>