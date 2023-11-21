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
            axios.get('/task/getHistory')
                .then(function(result){
                    console.log(result);
                    app.tasklist = result.data;
                    for (let i = 0; i < app.tasklist.length; i++) {
                        app.tasklist[i].startTime=new Date(app.tasklist[i].startTime);
                    }
                });
        },
        doTask:function (taskId) {
            axios.put('/flow/completeTask/'+taskId)
                .then(function(result){
                    console.log(result);
                });
        }
    }
});