Vue.component("welcome",{
	data(){
        return{
			loggedUser : null,
        }
    },
	template:
	`	<div>
			<navbar-guest></navbar-guest>
			
		</div>
	`
	,
	methods:{
		
	}
	,
	mounted(){
		window.localStorage.clear();
		this.loggedUser = JSON.parse(window.localStorage.getItem("loggedUser"));
	}
})