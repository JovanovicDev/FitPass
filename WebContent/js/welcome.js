Vue.component("welcome",{
	data(){
        return{
            searchInput: '',
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
