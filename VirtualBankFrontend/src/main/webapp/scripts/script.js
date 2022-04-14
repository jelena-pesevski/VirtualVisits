/**
 * 
 */

function change(){
	var canPaySwitch=document.getElementById("can-pay-switch");
	
	var object={
		canPay:canPaySwitch.checked
	};
	
	var request=new XMLHttpRequest();
	request.onreadystatechange=function(){
		if((request.readyState==4) && (request.status==200)){
			var lbl=document.getElementById("can-pay-lbl");
			var str="Online paying is "+ (canPaySwitch.checked?"enabled":"disabled");
			lbl.innerHTML=str;
		}else if(request.readyState==4){
			alert("Option can't be changed");
			//restore old value
			if(canPaySwitch.checked){
				canPaySwitch=false;
			}else{
				canPaySwitch=true;
			}
		}
	}
	request.open("POST", "?action=change", true);
	request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	request.send(JSON.stringify(object));
}

function refreshTransactions(){
	 var request = new XMLHttpRequest();
	 request.onreadystatechange = function () {
	 	if (request.readyState == 4 && request.status == 200) {
			var transactions=JSON.parse(request.responseText);

			var tBody=document.getElementById("tbody");
			tBody.innerHTML="";
			
			for(let i in transactions){
				var tr = document.createElement('tr');
       
                var td1 = document.createElement('td');
                td1.innerText = transactions[i].dateTime;
                
                var td2 = document.createElement('td');
                td2.innerText = transactions[i].cashAmount;
                
                tr.appendChild(td1);
                tr.appendChild(td2);
                tBody.appendChild(tr);
			}		
	    }     
	  };
	  request.open("POST", "?action=transactions", true);
	  request.send(null);
}

function init(canPay){
	var canPaySwitch=document.getElementById("can-pay-switch");
	
	canPaySwitch.checked=canPay;	
}