var app = new Vue({
    el:"#app",
    data:{
        tasklist:[]
    },
    created:function () {
        //调用方法
        this.findAll();
    },
    methods:{
        findAll:function(){
            axios.get('/task/getAll')
                .then(function(result){
                    console.log(result);
                    app.tasklist = result.data;
                });
        },
        completeTask:function (taskId) {
            axios.get('/task/completeTask?taskId='+taskId)
                .then(function(result){
                    console.log(result);
                    app.tasklist = result.data;
                });
        },
        getHistory:function (){
            axios.get('/history').then(function(result){
                location.href='/historyPage';
            });
        }
    }
});