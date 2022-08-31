Vue.component("home",{
	data(){
        return{
			loggedUser : {},
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
