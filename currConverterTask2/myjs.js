function check()
{
	let inpCurr = document.getElementById("id_input_currency");
	let msg = decument.getElementById("id_msg");
	
	if(inpCurr.value.trim() === "")
	{
		alert("Pleas enter a valid amount!");
		msg.innerHTML = "";
		name.focus();
		return false;
	}

	return true;

}