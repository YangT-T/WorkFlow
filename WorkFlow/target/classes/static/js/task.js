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
                    for (let i = 0; i < app.tasklist.length; i++) {
                        app.tasklist[i].createTime=new Date(app.tasklist[i].createTime);
                    }
                });
        },
        completeTask:function (taskId) {
            let newWindow=window.open('/bpmnPage/taskFlow?id='+taskId,'_blank')
        },
        deleteTask:function (taskId) {
            axios.get('/task/deleteTask?taskId='+taskId)
                .then(function(result){
                    console.log(result);
                    app.tasklist = result.data;
                    this.findAll();
                });
        },
        getHistory:function (){
            axios.get('/history').then(function(result){
                location.href='/historyPage';
            });
        }
    }
});