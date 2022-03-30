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

function init(canPay){
	var canPaySwitch=document.getElementById("can-pay-switch");
	
	canPaySwitch.checked=canPay;	
}