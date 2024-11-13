const worklist = /*[[${worklist}]]*/"model";
const date = new Date();
let day = date.getDate();
let month = date.getMonth()+1;
let year = date.getFullYear();
const elementThrough = document.getElementById('remindThrough')
const elementToday = document.getElementById('remindToday')
console.log("q");
if(day < 10){
	day = "0" + day;
}
if(month < 10){
	month = "0" + month;
}
dateCons = year + "-" + month + "-" + day;

for(var i = 0 ; i < worklist.length ; i++){
	if(worklist[i].state == "未完了"){/* for文*2 */
		if(dateCons > worklist[i].deadline){
			elementThrough.classList.add('uk-alert');
			elementThrough.classList.add('uk-alert-warning');
			elementThrough.innerHTML = "＜期日が過ぎたタスクがあります＞";
			console.log(worklist[i])
			break;
		}
	}
}
for(var i = 0 ; i < worklist.length ; i++){
	if(worklist[i].state == "未完了"){/* for文*2 */
		if(dateCons == worklist[i].deadline){
			elementToday.classList.add('uk-alert');
			elementToday.classList.add('uk-alert-warning');
			elementToday.innerHTML = "＜期日が今日のタスクがあります＞";	
			console.log(worklist[i])
			break;
		}
	}
}
var options = {
  valueNames: ['state','deadline','priority','updateDate']
};
var taskList = new List('task', options);
