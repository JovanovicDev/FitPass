Vue.component("welcome",{
	data(){
        return{
			loggedUser : {},
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
