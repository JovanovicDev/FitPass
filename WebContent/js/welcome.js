Vue.component("welcome",{
	data(){
        return{
			loggedUser : {},
        }
    },
	template:
	`	<div>
			<navbar-guest></navbar-guest>
			<div class="d-flex justify-content-center align-items-center" style="height: calc(100vh - 56px);">
			  <div>
			     <img src="images/fitpass.png"/>
			  </div>
			</div>
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
