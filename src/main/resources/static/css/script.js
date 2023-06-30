/**
 * 
 */

 const search=()=>{
	 
	 let query=$("#search-input").val();
	 console.log(query);
	 
	 if(query==''){
		 $(".search-result").hide();
	 }
	 
	 else{
		 $(".search-result").show();
		 
		 let url=`http://localhost:8080/search/${query}`;
		 
		 fetch(url).then((response)=>{
			 return response.json();
		 })
		 .then((data)=>{
			 console.log(data)
		 });
		 
	 }
 }