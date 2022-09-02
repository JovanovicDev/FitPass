const Welcome = {template: '<welcome></welcome>'}
const Login = {template: '<login></login>'}
const Registration = {template: '<registration></registration>'}
const Navbar = {template: '<navbar></navbar>'}
const NavbarGuest = {template: '<navbar-guest></navbar-guest>'}
const Home = {template: '<home></home>'}
const Profile = {template: '<profile></profile>'}
const ProfileSidebar = {template: '<profile-sidebar></profile-sidebar>'}
const UsersView = {template: '<users-view></users-view>'}
const SportFacilitiesView = {template: '<sport-facilities-view></sport-facilities-view>'}
const SportFacilityView = {template: '<sport-facility-view></sport-facility-view>'}
const AddUser = {template: '<add-user></add-user>'}
const AddSportFacility = {template: '<add-sport-facility></add-sport-facility>'}

const router = new VueRouter({
	mode:'hash',
	routes:[
		{path:'/', component:Welcome},
		{path:'/login', name:'login', component:Login},
		{path:'/registration', component:Registration},
		{path:'/home', component:Home},
		{path:'/profile', component:Profile},
		{path:'/users', component:UsersView},
		{path:'/sport-facilities', component:SportFacilitiesView},
		{path:'/sport-facility', component:SportFacilityView},
		{path:'/add-user', component:AddUser},
		{path:'/add-sport-facility', component:AddSportFacility}
	]
});

var app = new Vue({
	router,
	el: '#welcome'
});
