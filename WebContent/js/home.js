Vue.component("home",{
	data(){
        return{
			loggedUser : null,
        }
    },
	template:
	`	<div>
			<navbar></navbar>
			
		</div>
	`
	,
	methods:{
		
	}
	,
	mounted(){
		this.loggedUser = JSON.parse(window.localStorage.getItem("loggedUser"));
	}
})
