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
        doTask:function (taskId) {
            axios.put('/flow/completeTask/'+taskId)
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